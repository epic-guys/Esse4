package org.epic_guys.esse4.API.services;

import org.epic_guys.esse4.models.Appello;
import org.epic_guys.esse4.models.IscrizioneAppello;
import org.epic_guys.esse4.models.ParametriIscrizioneAppello;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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


    @DELETE(BASE_URL + "/appelli/{cdsId}/{adId}/{appId}/iscritti/{stuId}")
    Call<Void> deleteIscrizioneAppello(
            @Path("cdsId") long idCorsoDiStudio,
            @Path("adId") long idAttivitaDidattica,
            @Path("appId") long idAppello,
            @Path("stuId") long idStudente
    );

    enum FiltroPrenotazioni {
        BACHECA_ESITI
    }

    @GET(BASE_URL + "/prenotazioni/{matId}/")
    Call<List<IscrizioneAppello>> getPrenotazioni(@Path("matId") Long carrieraId);

    @GET(BASE_URL + "/prenotazioni/{matId}/") //Questo sarebbe quello da usare se capissi come far funzionare il filtro quindi per ora useremo quello sopra
    Call<List<IscrizioneAppello>> getPrenotazioni(
            @Path("matId") Long carrieraId,
            @Path("q") FiltroPrenotazioni filtro
    );
}
