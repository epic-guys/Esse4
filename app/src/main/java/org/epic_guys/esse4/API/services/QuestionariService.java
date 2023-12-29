package org.epic_guys.esse4.API.services;

import org.epic_guys.esse4.models.RigaLibrettoConStatoQuestionario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface QuestionariService extends ApiService {

    String BASE_URL = "questionari-service-v1";

    /**
     * Stati possibili di un questionario
     * <ul>
     *     <li>P recupera tutte le attività con questionari a prescindere dal loro stato</li>
     *     <li>C recupera tutte le attività con questionari ancora da compilare</li>
     * </ul>
     */
    enum StatoQuestionario {
        P,
        C
    }

    @GET(BASE_URL + "/questionari/libretto/{matId}")
    Call<List<RigaLibrettoConStatoQuestionario>> getQuestionari(
            @Path("matId") long idCarriera
    );

    @GET(BASE_URL + "/questionari/libretto/{matId}")
    Call<List<RigaLibrettoConStatoQuestionario>> getQuestionari(
            @Path("matId") long idCarriera,
            @Query("questFilter") StatoQuestionario statoQuestionario
    );

    @GET(BASE_URL + "/questionari/libretto/{matId}/righe/{adsceId}")
    Call<RigaLibrettoConStatoQuestionario> getQuestionario(
            @Path("matId") long idCarriera,
            @Path("adsceId") long idRigaLibretto
    );

    @GET(BASE_URL + "/questionari/libretto/{matId}/righe/{adsceId}")
    Call<RigaLibrettoConStatoQuestionario> getQuestionario(
            @Path("matId") long idCarriera,
            @Path("adsceId") long idRigaLibretto,
            @Query("questFilter") StatoQuestionario statoQuestionario
    );
}
