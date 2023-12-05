package org.epic_guys.esse4.API.services;

import org.epic_guys.esse4.models.ApiResource;
import org.epic_guys.esse4.models.RigaLibretto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface LibrettoService extends ApiService {
    String BASE_URL = "libretto-service-v2";

    @GET(BASE_URL + "/libretti/{matId}/righe/")
    Call<List<RigaLibretto>> righeLibretto(@Path("matId") long idCarriera);
}
