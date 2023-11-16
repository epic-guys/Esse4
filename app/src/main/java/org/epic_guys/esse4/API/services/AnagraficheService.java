package org.epic_guys.esse4.API.services;

import org.epic_guys.esse4.models.Persona;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface AnagraficheService extends ApiService {
    String BASE_URL = "anagrafica-service-v2";

    @GET(BASE_URL + "/persone")
    Call<List<Persona>> getPersone();
}
