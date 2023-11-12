package models.API;

import android.util.Log;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Parser;
import io.jsonwebtoken.security.Jwk;
import io.jsonwebtoken.security.Jwks;
import org.conscrypt.BuildConfig;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.PublicKey;
import java.util.concurrent.CompletableFuture;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class API {
    private static API instance;
    private OkHttpClient client;

    private Jwt<?, ?> jwt;
    private Jwk<?> jwk;
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
                API.getInstance().jwk = parser.parse(response.body().string());
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
                throw new RuntimeException("Could not perform login: " + (BuildConfig.DEBUG ? e.getMessage(): "Unknown error"));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.code() == 200){
                    try {
                        //log data to console
                        Log.i("API_TAG", response.toString());

                        JSONObject json = new JSONObject(response.body().string());

                        boolean hasJwk = false;
                        if (API.getInstance().jwk == null) {
                            hasJwk = API.saveJwk().join();
                        }

                        if (!hasJwk)
                            throw new RuntimeException("Diocane");
                        JwtParser parser = Jwts.parser()
                                .verifyWith(API.getInstance().jwk.toKey())
                                .build();



                        try {
                            Jwt<?, ?> j = parser.parse(json.getString("jwt"));

                            // API.getInstance().jwt = json.getString("jwt");
                            API.getInstance().jwt = j;
                        } catch (Exception e) {
                            Log.w("TAG_API", "JWT not parsed", e);
                        }





                        // Meglio mettere qui isLogged in modo che se si lancia un'eccezione resta false
                        API.getInstance().isLogged = true;
                    }
                    catch (JSONException e){
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

    public static boolean isJwtValid() {
        // Claims claims = Jwts.claims().build();
        JwtParser parser = Jwts.parser().build();
        // Jwt<?, ?> jwt = parser.parse(API.getInstance().jwt);


        return false;
        // return claims.getExpiration().after(Date.from(Instant.now()));
    }

    public static boolean refreshJwt() {
        OkHttpClient client = getInstance().client;
        Request request = new Request.Builder()

                .build();

        throw new UnsupportedOperationException("TODO");
    }

    public static String getBasicData(){
        return API.getInstance().jwt.toString();
    }
}
