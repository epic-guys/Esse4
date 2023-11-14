package org.epic_guys.esse4.models;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Jwt implements ApiResource {
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
            Base64.Decoder base64 = Base64.getDecoder();
            String payload = jwt.split("\\.")[2];
            payload = new String(base64.decode(payload));
            this.payload = gson.fromJson(payload, Map.class);
        }
        return payload;
    }

    public String get(String key) {
        return getPayload().get(key);
    }


    /**
     * @return The JWT as a String.
     */
    @NotNull
    @Override
    public String toString() {
        return jwt;
    }

    public Date getExpiration() {
        return new Date(Long.decode(getPayload().get("exp")));
    }
}
