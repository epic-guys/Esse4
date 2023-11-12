package models.API;

import android.graphics.Picture;
import android.util.Log;

import org.conscrypt.BuildConfig;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.PublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Parser;
import io.jsonwebtoken.security.Jwk;
import io.jsonwebtoken.security.Jwks;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class API {
    private static API instance;
    private OkHttpClient client;
    private JwtParser jwtParser;
    private Jws<Claims> jws;
    private Jwk<PublicKey> jwk;
    private boolean isLogged = false;
    public static final String BASE_URL = "https://esse3.unive.it/e3rest/api/";


    private API() {
        this.client = new OkHttpClient.Builder().build();
    }

    public static API getInstance(){
        return instance == null ? instance = new API() : instance;
    }

    @NotNull
    public static CompletableFuture<Boolean> saveJwk() {
        Request request = new Request.Builder()
                .url(BASE_URL + "jwt/jwk")
                .build();

        final CompletableFuture<Boolean> future = new CompletableFuture<>();
        API.getInstance().client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                future.complete(false);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Parser<Jwk<?>> parser = Jwks.parser().build();
                API.getInstance().jwk = (Jwk<PublicKey>) parser.parse(response.body().string());
                API.getInstance().jwtParser = Jwts.parser()
                        .verifyWith(API.getInstance().jwk.toKey())
                        .build();
                future.complete(true);
            }
        });

        return future;
    }

    @NotNull
    public static CompletableFuture<Boolean> login (String username, String password) {
        String auth = Credentials.basic(
                username,
                password
        );

        Request request = new Request.Builder()
                .url(BASE_URL + "login/jwt/new/")
                .addHeader("Authorization", auth)
                .build();


        final CompletableFuture<Boolean> future = new CompletableFuture<>();
        API.getInstance().client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                throw new RuntimeException("Could not perform login: " + (BuildConfig.DEBUG ? e.getMessage() : "Unknown error"));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.code() == 200) {
                    try {
                        if (!saveJwk().join())
                            throw new RuntimeException("Failed to save JWK");
                        
                        
                        //log data to console
                        Log.i("API_TAG", response.toString());

                        JSONObject json = new JSONObject(response.body().string());

                        API.getInstance().jws = API.getInstance().jwtParser.parseSignedClaims(json.getString("jwt"));


                        // Meglio mettere qui isLogged in modo che se si lancia un'eccezione resta false
                        API.getInstance().isLogged = true;
                    } catch (JSONException e) {
                        API.getInstance().isLogged = false;
                        Log.w("TAG_API", (BuildConfig.DEBUG ? "Could not parse response: " + e.getMessage() : "Unknown error"));
                        // throw new RuntimeException("Could not parse response: " + (BuildConfig.DEBUG ? e.getMessage(): "Unknown error"));
                    }
                } else {
                    API.getInstance().isLogged = false;
                }
                future.complete(API.getInstance().isLogged);
            }
        });

        return future;
    }

    public static CompletableFuture<Map<String, String>> getBasicData(){
        Request request = new Request.Builder()
                .url(BASE_URL + "login")
                .addHeader("Authorization", "Bearer " + API.getInstance().jws.toString())
                .build();

        final CompletableFuture<Boolean> future = new CompletableFuture<>();
        API.getInstance().client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.code() == 200) {
                    try {
                        //log data to console
                        Log.i("API_TAG", response.toString());
                        JSONObject json = new JSONObject(response.body().string());

                        //here I take the data I need, so:
                        // FirstName = { user{ firstName } }
                        // LastName = { user{ lastName } }
                        // Type of degree
                        // Student's type
                        // Year of study = {user { } } AnnoCorso
                        // Enrolment date
                        // Degree's Programme
                        // Study System
                        // Part-time

                    } catch (JSONException e) {
                        API.getInstance().isLogged = false;
                        Log.w("TAG_API", (BuildConfig.DEBUG ? "Could not parse response: " + e.getMessage() : "Unknown error"));
                        throw new RuntimeException("Could not parse response: " + (BuildConfig.DEBUG ? e.getMessage(): "Unknown error"));
                    }
                }
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                throw new RuntimeException("Could not fetch data: " + (BuildConfig.DEBUG ? e.getMessage() : "Server error"));
            }
        });
        return null;
    }

    public static CompletableFuture<Picture> getPhoto(){
        
    }

        public static boolean isValidJws() {
        // Controllo se la scadenza è dopo del momento attuale
        return API.getInstance()
                .jws.getPayload()
                .getExpiration()
                .after(Date.from(Instant.now()));
    }

    public static boolean refreshJws() {
        OkHttpClient client = getInstance().client;
        Request request = new Request.Builder()
                .build();

        throw new UnsupportedOperationException("TODO");
    }

}
