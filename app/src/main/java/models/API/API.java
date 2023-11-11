package models.API;

import android.widget.TextView;
import android.widget.Toast;

import org.conscrypt.BuildConfig;
import org.epic_guys.esse4.R;
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

        API.getInstance().client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    throw new RuntimeException("Could not perform login: " + (BuildConfig.DEBUG ? e.getMessage(): "Unknown error"));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                    if(response.code() == 200){
                        API.getInstance().isLogged = true;
                        try {
                            //log data to console
                            System.out.println(response);

                            JSONObject json = new JSONObject(response.body().string());
                            API.getInstance().jwt = json.getString("jwt");
                        }
                        catch (JSONException e){
                            throw new RuntimeException("Could not parse response: " + (BuildConfig.DEBUG ? e.getMessage(): "Unknown error"));
                        }
                    } else {
                        API.getInstance().isLogged = false;
                        return;
                    }
            }
        });

        //this is a race condition, so until the request is not completed, we should wait
        //TODO: find a better way to do this
        return API.getInstance().isLogged;
    }

    private static void newJWT(String username, String password){

    }

    public static String getBasicData(){
        return API.getInstance().jwt;
    }


}
