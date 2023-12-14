package org.epic_guys.esse4.API.services;

import org.epic_guys.esse4.models.ApiResource;
import org.epic_guys.esse4.models.Appello;
import org.epic_guys.esse4.models.PianoDiStudio;
import org.epic_guys.esse4.models.RigaLibretto;
import org.epic_guys.esse4.models.TestataPianoDiStudio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
public interface PianiService extends ApiService {
    String BASE_URL = "piani-service-v1";

    @GET(BASE_URL + "/piani/{stuId}")
    Call<List<TestataPianoDiStudio>> testataPianoDiStudio(@Path("stuId") long idStudente);

    @GET(BASE_URL + "/piani/{stuId}/{pianoId}")
    Call<PianoDiStudio> righePianoDiStudio(@Path("stuId") long idStudente, @Path("pianoId") long idPianoDiStudio);

}
