package models.API;

import models.Persona;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface Esse3Api {


    @GET("anagrafica-service-v2/persone")
    Call<List<Persona>> getPersone();
}
