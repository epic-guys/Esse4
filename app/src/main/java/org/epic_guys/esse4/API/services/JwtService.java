package org.epic_guys.esse4.API.services;

import org.epic_guys.esse4.models.Jwt;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JwtService extends ApiService {
    @GET("login/jwt/new")
    Call<Jwt> newJwt();
}
