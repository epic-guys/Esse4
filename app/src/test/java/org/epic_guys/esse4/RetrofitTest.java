package org.epic_guys.esse4;

import models.API.Esse3Api;
import models.Persona;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class RetrofitTest {
    @Test
    public void testPersona() throws IOException {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    String auth = Credentials.basic("REDACTED", "REDACTED");
                    Request original = chain.request();
                    Request authenticated = original.newBuilder()
                            .addHeader("Authorization", auth)
                            .build();
                    return chain.proceed(authenticated);
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://esse3.unive.it/e3rest/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        Esse3Api esse3Api = retrofit.create(Esse3Api.class);
        retrofit2.Call<List<Persona>> persone = esse3Api.getPersone();
        System.out.println(persone.request().url());
        retrofit2.Response<List<Persona>> response = persone.execute();
        System.out.println(response.raw());
        for (Persona p : response.body()) {
            System.out.println(p.getNome() + " " + p.getCognome());
        }
    }
}
