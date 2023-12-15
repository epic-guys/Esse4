package org.epic_guys.esse4.API.services;

import org.epic_guys.esse4.models.ApiResource;
import org.epic_guys.esse4.models.Appello;
import org.epic_guys.esse4.models.AppelloLibretto;
import org.epic_guys.esse4.models.RigaLibretto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface LibrettoService extends ApiService {

    enum Condizioni {
        APPELLI_PRENOTABILI,
        APPELLI_PRENOTABILI_E_FUTURI
    }

    String BASE_URL = "libretto-service-v2";

    @GET(BASE_URL + "/libretti/{matId}/righe/")
    Call<List<RigaLibretto>> righeLibretto(@Path("matId") long idCarriera);


    @GET(BASE_URL + "/libretti/{matId}/appelli")
    Call<List<AppelloLibretto>> getAppelli(@Path("matId") long idCarriera);

    @GET(BASE_URL + "/libretti/{matId}/appelli")
    Call<List<AppelloLibretto>> getAppelli(
            @Path("matId") long idCarriera,
            @Query("q") Condizioni condizioni
    );

    @GET(BASE_URL + "/libretti/{matId}/righe/{adsceId}/appelli")
    Call<List<AppelloLibretto>> getAppelliPerRigaLibretto(
            @Path("matId") long idCarriera,
            @Path("adsceId") long idRigaLibretto,
            @Query("q") Condizioni condizioni
    );

    @GET(BASE_URL + "/libretti/{matId}/righe/{adsceId}/appelli")
    Call<List<AppelloLibretto>> getAppelliPerRigaLibretto(
            @Path("matId") long idCarriera,
            @Path("adsceId") long idRigaLibretto
    );
}
