package org.epic_guys.esse4.API;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.core.util.Pair;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Credentials;
import okhttp3.Request;

import org.conscrypt.BuildConfig;
import org.epic_guys.esse4.API.services.AnagraficheService;
import org.epic_guys.esse4.API.services.ApiService;
import org.epic_guys.esse4.API.services.JwtService;
import org.epic_guys.esse4.exceptions.ApiException;
import org.epic_guys.esse4.models.ApiError;
import org.epic_guys.esse4.models.Carriera;
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

public class API {
    private static API instance;
    private final OkHttpClient client;
    private final Retrofit retrofit;
    private Jwt jwt;
    private Persona loggedPersona;
    private Carriera carrieraStudente;
    // public static final String BASE_URL = "https://esse3.unive.it/e3rest/api/";
    public static final String BASE_URL = "https://unive.esse3.pp.cineca.it/e3rest/api/";


    private Jwt getJwt() {
        return jwt;
    }


    public static Persona getLoggedPersona() {
        return API.getInstance().loggedPersona;
    }
    public static Carriera getCarriera() {
        return API.getInstance().carrieraStudente;
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

    public static void logout(){
        API.instance = null;
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
                            Log.d("Api", BuildConfig.DEBUG ? response.toString() : "Login successful");
                        } else {
                            Log.d("Api", BuildConfig.DEBUG ? response.toString() : "Login failed");
                        }
                        future.complete(success);
                    }
                });


        return future;
    }

    public static CompletableFuture<Pair<Persona, Carriera>> getBasicData() {
        Call<List<Persona>> callPersone = API.getService(AnagraficheService.class).getPersone();
        Call<List<Carriera>> callCarriere = API.getService(AnagraficheService.class).getCarriere();

        return API.enqueueResource(callPersone).thenCombine(
                API.enqueueResource(callCarriere),
                (persone, carriere) -> {
                    API.getInstance().loggedPersona = persone.get(0);
                    API.getInstance().carrieraStudente = carriere.get(0);
                    return new Pair<>(persone.get(0), carriere.get(0));
                });
    }

    public static CompletableFuture<Bitmap> getPhoto() {
        final CompletableFuture<Bitmap> future = new CompletableFuture<>();
        Persona persona = API.getInstance().loggedPersona;

        Request request = new Request.Builder()
                .url(API.BASE_URL + "anagrafica-service-v2/persone/" + /*persId*/ persona.getPersId() + "/foto").build();

        API.getInstance().client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                future.completeExceptionally(e);
            }
            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull okhttp3.Response response) {
                if (response.code() == 200) {
                    try {
                        assert response.body() != null;
                        byte[] imgBytes = response.body().bytes();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
                        future.complete(bitmap);
                    }catch (Exception e) {
                        future.completeExceptionally(e);
                    }


                    Log.d("Api", BuildConfig.DEBUG ? response.toString() : "Photo Fetch successful");
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
    public static CompletableFuture<Void> refreshJwt() throws RuntimeException {
        JwtService jwtService = API.getService(JwtService.class);
        if(API.getInstance().jwt == null){
            throw new RuntimeException("JWT is null");
        };
        String jwt = API.getInstance().jwt.toString();
        Call<Jwt> jwtCall = jwtService.refreshJwt(jwt);
        return API.enqueueResource(jwtCall)
                .thenAccept(newJwt -> {
                    API.getInstance().jwt = newJwt;
                });
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

    /**
     * Enqueues a Retrofit call and returns a CompletableFuture that will be completed when the call
     * is completed.
     * @param call Retrofit call to be enqueued.
     * @return A CompletableFuture that will be completed when the call is completed.
     * @param <T> Type of the resource returned by the call.
     */
    public static <T> CompletableFuture<T> enqueueResource(Call<T> call) {
        final CompletableFuture<T> future = new CompletableFuture<>();

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NotNull Call<T> call, @NotNull Response<T> response) {
                if (response.isSuccessful()) {
                    future.complete(response.body());
                } else {
                    Exception exception;

                    try {
                        Gson gson = new Gson();
                        ApiError error = gson.fromJson(response.errorBody().string(), ApiError.class);
                        exception = new ApiException(error);
                    } catch (IOException | NullPointerException e) {
                        exception = e;
                    }

                    future.completeExceptionally(exception);
                }
            }

            @Override
            public void onFailure(@NotNull Call<T> call, @NotNull Throwable t) {
                future.completeExceptionally(t);
            }
        });

        return future;
    }
}
