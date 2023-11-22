package org.epic_guys.esse4.API;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Credentials;
import okhttp3.Request;

import org.conscrypt.BuildConfig;
import org.epic_guys.esse4.API.services.AnagraficheService;
import org.epic_guys.esse4.API.services.ApiService;
import org.epic_guys.esse4.API.services.JwtService;
import org.epic_guys.esse4.exceptions.ApiException;
import org.epic_guys.esse4.models.Jwt;
import org.epic_guys.esse4.models.Persona;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class API {
    private static API instance;
    private OkHttpClient client;
    private Retrofit retrofit;
    private Jwt jwt;
    private Persona loggedPersona;
    public static final String BASE_URL = "https://esse3.unive.it/e3rest/api/";

    private Jwt getJwt() {
        return jwt;
    }


    public static Persona getLoggedPersona() {
        return API.getInstance().loggedPersona;
    }

    private API() {
        this.client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    Jwt jwt = this.getJwt();
                    if (jwt != null) {
                        request = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + jwt)
                                .build();
                    }
                    return chain.proceed(request);
                })
                .build();

        this.retrofit = new Retrofit.Builder()
                .client(this.client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API.BASE_URL)
                .build();
    }

    public static API getInstance(){
        return instance == null ? instance = new API() : instance;
    }

    @NotNull
    public static CompletableFuture<Boolean> login (String username, String password) {
        final CompletableFuture<Boolean> future = new CompletableFuture<>();

        String auth = Credentials.basic(
                username,
                password
        );

        // We use a new one only this time, to use basic authentication
        OkHttpClient basicClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request authenticated = chain.request().newBuilder()
                            .addHeader("Authorization", auth)
                            .build();
                    return chain.proceed(authenticated);
                })
                .build();
        Retrofit basicRetrofit = new Retrofit.Builder()
                .client(basicClient)
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        basicRetrofit.create(JwtService.class)
                .newJwt()
                .enqueue(new retrofit2.Callback<Jwt>() {
            @Override
            public void onFailure(@NotNull Call<Jwt> call, @NotNull Throwable t) {
                // throw new RuntimeException("Could not perform login: " + (BuildConfig.DEBUG ? t.getMessage() : "Unknown error"));
                future.completeExceptionally(t);
            }

            @Override
            public void onResponse(@NotNull Call<Jwt> call, @NotNull Response<Jwt> response) {
                boolean success = response.code() == 200;
                if (success) {
                    API.getInstance().jwt = response.body();
                    Log.i("Api", BuildConfig.DEBUG ? response.toString() : "Login successful");
                } else {
                    Log.i("Api", BuildConfig.DEBUG ? response.toString() : "Login failed");
                }
                future.complete(success);
            }
        });


        return future;
    }

    public static CompletableFuture<Persona> getBasicData(){

        final CompletableFuture<Persona> future = new CompletableFuture<>();

        API.getService(AnagraficheService.class)
                .getPersone()
                .enqueue( new retrofit2.Callback<List<Persona>>() {
                    @Override
                    public void onResponse(@NotNull Call<List<Persona>> call, @NotNull Response<List<Persona>> response) {
                        if (response.code() == 200) {
                            Log.i("Api", BuildConfig.DEBUG ? response.toString() : "Anagrafiche Service Fetch successful");

                            //here I take the data I need, so:
                            // Matricola = { user{ userId } }
                            // FirstName = { user{ firstName } }
                            // LastName = { user{ lastName } }
                            // Type of degree
                            // Student's type
                            // Year of study = {user { trattiCarriera[ 0{ dettaglioTratto{ annoCorso } } ] } }
                            // Enrolment date
                            // NOT IN THIS CALL: Degree's Programme = "[" + {user { trattiCarriera[ 0{ dettaglioTratto{ cdsCod } } ] } } + "] - " + {user { trattiCarriera[ 0{ cdsDes } ] } }
                            // Study System
                            // NOT IN THIS CALL: Part-time = {user { trattiCarriera[ 0{ dettaglioTratto{ ptFlag } } ] } }
                            try {
                                Persona persona = response.body().get(0);
                                API.getInstance().loggedPersona = persona;
                                future.complete(persona);
                            } catch (NullPointerException e) {
                                future.completeExceptionally(e);
                            }
                        }
                    }

                    @Override @EverythingIsNonNull
                    public void onFailure(Call<List<Persona>> call, Throwable t) {
                        // throw new RuntimeException("Could not fetch data: " + (BuildConfig.DEBUG ? t.getMessage() : "Server error"));
                        future.completeExceptionally(t);
                    }
               });

        return future;
    }

    public static CompletableFuture<Bitmap> getPhoto() {
        final CompletableFuture<Bitmap> future = new CompletableFuture<>();
        Persona persona = API.getInstance().loggedPersona;

        Request request = new Request.Builder()
                .url(API.BASE_URL + "/anagrafica-service-v2/persone/" + /*persId*/ persona.getPersId() + "/foto").build();

        API.getInstance().client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                future.completeExceptionally(e);
            }
            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull okhttp3.Response response) {
                if (response.code() == 200) {
                    try {
                        byte[] imgBytes = response.body().bytes();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
                        future.complete(bitmap);
                    }catch (Exception e) {
                        future.completeExceptionally(e);
                    }


                    Log.i("Api", BuildConfig.DEBUG ? response.toString() : "Photo Fetch successful");
                }
            }
        });

        return future;
    }

    public static boolean isValidJwt() {
        return API.getInstance()
                .jwt.getPayload().getExpirationTime()
                .after(Date.from(Instant.now()));
    }

    /**
     * Refreshes the current JWT and stores it.
     */
    public static CompletableFuture<Void> refreshJwt() {
        final CompletableFuture<Void> future = new CompletableFuture<>();
        JwtService jwtService = API.getService(JwtService.class);
        Jwt jwt = API.getInstance().jwt;
        jwtService.refreshJwt(jwt.toString())
                .enqueue(new Callback<Jwt>() {
                    @Override
                    public void onResponse(@NotNull Call<Jwt> call, @NotNull Response<Jwt> response) {
                        if (response.isSuccessful()) {
                            API.getInstance().jwt = response.body();
                            future.complete(null);
                        }
                        else {
                            future.completeExceptionally(
                                    new ApiException("Response " + response.code() + " when refreshing JWT."));
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<Jwt> call, @NotNull Throwable t) {
                        future.completeExceptionally(t);
                    }
                });
        
        return future;
    }

    /**
     * Provides an instance of the specified service.
     * @param serviceClass Class of the service requested.
     * @return An instance of the service.
     * @param <T> Type of service requested. It must be a subclass of ApiService
     * @see ApiService
     */
    public static <T extends ApiService> T getService(Class<T> serviceClass) {
        return API.getInstance().retrofit.create(serviceClass);

    }
}
