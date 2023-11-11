package models.API;

import android.util.Log;

import org.conscrypt.BuildConfig;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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

    private String jwt;
    private boolean isLogged = false;
    public static final String BASE_URL = "https://esse3.unive.it/e3rest/api/";

    private API(){
        this.client = new OkHttpClient.Builder().build();
    }

    public static API getInstance(){
        return instance == null ? instance = new API() : instance;
    }

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
                        System.out.println(response);

                        JSONObject json = new JSONObject(response.body().string());
                        API.getInstance().jwt = json.getString("jwt");
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

    private static void newJWT(String username, String password){

    }

    public static String getBasicData(){
        return API.getInstance().jwt;
    }


}
