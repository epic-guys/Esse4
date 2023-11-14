package org.epic_guys.esse4;

import org.epic_guys.esse4.API.services.AnagraficheService;
import org.epic_guys.esse4.models.Jwt;
import org.epic_guys.esse4.models.Persona;
import okhttp3.*;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class RetrofitTest {
    private OkHttpClient client;
    private Retrofit retrofit;

    public RetrofitTest() {
        client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    String auth = Credentials.basic("REDACTED", "REDACTED");
                    Request original = chain.request();
                    Request authenticated = original.newBuilder()
                            .addHeader("Authorization", auth)
                            .build();
                    return chain.proceed(authenticated);
                })
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://esse3.unive.it/e3rest/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Test
    public void testPersona() throws IOException {
        AnagraficheService esse3Api = retrofit.create(AnagraficheService.class);
        retrofit2.Call<List<Persona>> persone = esse3Api.getPersone();
        System.out.println(persone.request().url());
        retrofit2.Response<List<Persona>> response = persone.execute();
        System.out.println(response.raw());
        // for (Persona p : response.body()) {
        //    System.out.println(p.getNome() + " " + p.getCognome());
        // }
    }

    @Test
    public void testJwt() {
        Jwt jwt = new Jwt("eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImVzc2UzIn0.eyJzdWIiOiI4ODg4NTEiLCJwcm9maWxlIjoiU1RVREVOVEUiLCJmaXNjYWxDb2RlIjoiRlZSTFZTMDJSMDZEMzI1QyIsImlzcyI6ImVzc2UzIiwiZXhwIjoxNjk5OTgxMzQ5LCJpYXQiOjE2OTk5ODA0NDksInRlbmFudCI6IlVOSVZFIn0.skW3sEJ1CnG3fLmp8saCKKP621DOXFXX3pUymXiD4s6LP7zpl4pzVlZpTA6REP8on5zvplt03bHIOzLHYxqaejtlE8hv8IuoJn1iYP6pmsBCzCxetghYSjal6GUHMJI9iiuoLtXY1hBFCNyuuZTA6-fK1nXaP9xgHcGAzL2MuC4dMEaBjYV9wHds2z6-GsbNOaCYLX50FPlmEPQtnLfCxOeYqNy_pSTIDlZIzuWuPw5mT0vqK_5kTPwsYhFOnlqUKPVik62idPxt3uCT1NO4M0UXZLfDbFHPx88-8QLCQo7OLrvc1AWsLogHhIkMb7FmU8MzPNI3L08_WZOlt8Tk6Q");
        System.out.println(jwt.getPayload().getExpirationTime());
    }
}
