package org.epic_guys.esse4.API.services;

import org.epic_guys.esse4.models.questionari.Answer;
import org.epic_guys.esse4.models.questionari.PaginaQuestionario;
import org.epic_guys.esse4.models.questionari.RigaLibrettoConStatoQuestionario;
import org.epic_guys.esse4.models.questionari.TagsList;
import org.epic_guys.esse4.models.questionari.UnitaDidatticaConQuestionario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
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

    enum EventoCompilazione {
        EV_VAL_DID
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

    /**
     * Recupera l'unità didattica con il questionario associato.
     *
     * @param idRigaLibretto L'ID della riga del libretto.
     * @param eventoCompilazione L'evento di compilazione del questionario. Deve essere sempre impostato a EV_VAL_DID.
     * @return Un oggetto Call che rappresenta la richiesta HTTP per ottenere l'unità didattica con il questionario associato.
     */
    @GET(BASE_URL + "/questionari/libretto/{adsceId}/unitadidattiche")
    Call<UnitaDidatticaConQuestionario> getUnitaDidatticaConQuestionario(
            @Path("adsceId") long idRigaLibretto,
            @Query("eventCompId") EventoCompilazione eventoCompilazione
    );


    /**
     * Inizia la compilazione di un questionario.
     *
     * @param idStudente L'ID dello studente.
     * @param idRigaLibretto L'ID della riga del libretto.
     * @param idQuestionario L'ID del questionario da iniziare.
     *                       Si recupera tramite {@link QuestionariService#getUnitaDidatticaConQuestionario(long, EventoCompilazione)}.
     * @param eventoCompilazione L'evento di compilazione del questionario. Deve essere sempre impostato a EV_VAL_DID.
     * @param idConfigurazioneQuestionario L'ID della configurazione del questionario. Si recupera
     *                                     tramite {@link QuestionariService#getUnitaDidatticaConQuestionario(long, EventoCompilazione)}.
     * @return Un oggetto Call che rappresenta la richiesta HTTP per iniziare la compilazione del questionario.
     */
    @PUT(BASE_URL + "/questionari/compilazione/{stuId}/{adsceId}/quest/{questionarioId}/start"  )
    Call<PaginaQuestionario> iniziaQuestionario(
            @Path("stuId") long idStudente,
            @Path("adsceId") long idRigaLibretto,
            @Path("questionarioId") long idQuestionario,
            @Query("eventCompId") EventoCompilazione eventoCompilazione,
            @Query("questConfigId") long idConfigurazioneQuestionario,
            @Query("userCompId") long idCompilazioneUtente,
            @Body TagsList tagsList
            );

    @PUT(BASE_URL + "/questionari/compilazione/{stuId}/quest/{questionarioId}/{questCompId}/save/{pageId}/")
    Call<String> salvaPaginaQuestionario(
            @Path("stuId") long idStudente,
            @Path("questionarioId") long idQuestionario,
            @Path("questCompId") long idCompilazioneQuestionario,
            @Path("pageId") long idPaginaQuestionario,
            @Query("eventCompId") EventoCompilazione eventoCompilazione,
            @Body List<Answer> answers
    );

    @GET(BASE_URL + "/questionari/compilazione/{stuId}/{adsceId}/quest/{questionarioId}/{questCompId}/pagina/{pageId}/next")
    Call<PaginaQuestionario> getNextPaginaQuestionario(
            @Path("stuId") long idStudente,
            @Path("adsceId") long idRigaLibretto,
            @Path("questionarioId") long idQuestionario,
            @Path("questCompId") long idCompilazioneQuestionario,
            @Path("pageId") long idPaginaQuestionario,
            @Query("userCompId") long idCompilazioneUtente
    );

    @GET(BASE_URL + "/questionari/compilazione/{stuId}/{adsceId}/quest/{questionarioId}/{questCompId}/pagina/{pageId}/prev")
    Call<PaginaQuestionario> getPrevPaginaQuestionario(
            @Path("stuId") long idStudente,
            @Path("adsceId") long idRigaLibretto,
            @Path("questionarioId") long idQuestionario,
            @Path("questCompId") long idCompilazioneQuestionario,
            @Path("pageId") long idPaginaQuestionario,
            @Query("userCompId") long idCompilazioneUtente
    );
}
