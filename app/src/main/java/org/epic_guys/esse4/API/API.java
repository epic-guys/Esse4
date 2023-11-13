package org.epic_guys.esse4.API;

import android.graphics.Picture;
import android.util.Log;

import io.jsonwebtoken.*;
import org.epic_guys.esse4.API.services.AnagraficheService;
import org.epic_guys.esse4.API.services.ApiService;
import org.epic_guys.esse4.API.services.JwtService;
import org.epic_guys.esse4.models.Jwt;
import org.epic_guys.esse4.models.Persona;
import okhttp3.*;
import org.conscrypt.BuildConfig;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.PublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.jsonwebtoken.io.Parser;
import io.jsonwebtoken.security.Jwk;
import io.jsonwebtoken.security.Jwks;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    private static API instance;
    private OkHttpClient client;
    private Retrofit retrofit;
    private Jwt jwt;
    private boolean isLogged = false;
    public static final String BASE_URL = "https://esse3.unive.it/e3rest/api/";


    private API() {
        this.client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    if (jwt != null) {
                        request = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + jwt.getJwt())
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


    /*
    @NotNull
    public static CompletableFuture<Void> fetchJwk() {
        Request request = new Request.Builder()
                .url(BASE_URL + "jwt/jwk")
                .build();

        final CompletableFuture<Void> future = new CompletableFuture<>();
        API.getInstance().client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull okhttp3.Response response) throws IOException {
                Parser<Jwk<?>> parser = Jwks.parser().build();
                try {
                    JSONObject json = new JSONObject(response.body().string());
                    String jwk = json.getJSONArray("keys").getJSONObject(0).toString();
                API.getInstance().jwk = (Jwk<PublicKey>) parser.parse(jwk);
                API.getInstance().jwtParser = Jwts.parser()
                        .verifyWith(API.getInstance().jwk.toKey())
                        .build();
                future.complete(null);
                } catch (JSONException e) {
                    future.completeExceptionally(e);
                }
            }
        });

        return future;
    }
     */

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
                if (response.code() == 200) {
                    // API.fetchJwk().join();

                    //log data to console
                    Log.i("API_TAG", response.toString());
                    API.getInstance().jwt = response.body();
                    API.getInstance().isLogged = true;
                } else {
                    API.getInstance().isLogged = false;
                }
                future.complete(API.getInstance().isLogged);
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
                            //log data to console
                            Log.i("API_TAG", response.toString());

                            //here I take the data I need, so:
                            // Matricola = { user{ userId } }
                            // FirstName = { user{ firstName } }
                            // LastName = { user{ lastName } }
                            // Type of degree
                            // Student's type
                            // Year of study = {user { trattiCarriera[ 0{ dettaglioTratto{ annoCorso } } ] } }
                            // Enrolment date
                            // Degree's Programme = "[" + {user { trattiCarriera[ 0{ dettaglioTratto{ cdsCod } } ] } } + "] - " + {user { trattiCarriera[ 0{ cdsDes } ] } }
                            // Study System
                            // Part-time = {user { trattiCarriera[ 0{ dettaglioTratto{ ptFlag } } ] } }
                            try {
                                Persona p = response.body().get(0);
                                future.complete(p);
                            } catch (NullPointerException e) {
                                future.completeExceptionally(e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Persona>> call, Throwable t) {
                        // throw new RuntimeException("Could not fetch data: " + (BuildConfig.DEBUG ? t.getMessage() : "Server error"));
                        future.completeExceptionally(t);
                    }
               });

        return future;
    }

    public static CompletableFuture<Picture> getPhoto(){
        throw new UnsupportedOperationException();
    }

    /**
     * @return Whether the expiration date is before the current time.
     */
    public static boolean isValidJwt() {
        return API.getInstance()
                .jwt.getExpiration()
                .after(Date.from(Instant.now()));
    }

    public static boolean refreshJws() {
        OkHttpClient client = getInstance().client;
        Request request = new Request.Builder()
                .build();

        throw new UnsupportedOperationException("TODO");
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
