package org.epic_guys.esse4.models;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Jwt {
    private final String jwt;
    private transient Map<String, String> payload;

    public Jwt(String jwt) {
        this.jwt = jwt;
        getPayload();
    }

    @NotNull
    private Map<String, String> getPayload() {
        if (payload == null) {
            Gson gson = new Gson();
            String payload = jwt.split("\\.")[2];
            this.payload = gson.fromJson(payload, Map.class);
        }
        return payload;
    }

    public String get(String key) {
        return getPayload().get(key);
    }

    public String getJwt() {
        return jwt;
    }

    public Date getExpiration() {
        return new Date(Long.decode(getPayload().get("exp")));
    }
}
