package org.epic_guys.esse4.API.services;

import org.epic_guys.esse4.models.Appello;
import org.epic_guys.esse4.models.ParametriIscrizioneAppello;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface CalendarioEsamiService extends ApiService {
    String BASE_URL = "calesa-service-v1";

    @GET(BASE_URL + "/appelli/{cdsId}/{adId}")
    Call<List<Appello>> getAppelli(@Path("cdsId") Long idCorsoDiStudio, @Path("adId") Long idAttivitaDidattica);

    @POST(BASE_URL + "/appelli/{cdsId}/{adId}/{appId}/iscritti")
    Call<Void> postIscrizioneAppello(
            @Path("cdsId") long idCorsoDiStudio,
            @Path("adId") long idAttivitaDidattica,
            @Path("appId") long idAppello,
            @Body ParametriIscrizioneAppello parametriIscrizioneAppello
    );
}
