package org.epic_guys.esse4.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Jwt implements ApiResource {
    private final String jwt;

    public static class Payload {
        @SerializedName("iss")
        private String issuer;

        @SerializedName("sub")
        private String subject;

        @SerializedName("aud")
        private String audience;

        @SerializedName("exp")
        private long expirationTime;

        @SerializedName("nbf")
        private long notBefore;

        @SerializedName("iat")
        private long issuedAt;

        @SerializedName("jti")
        private String jwtId;

        public String getIssuer() {
            return issuer;
        }

        public String getSubject() {
            return subject;
        }

        public String getAudience() {
            return audience;
        }

        public Date getExpirationTime() {
            /*
            * Java essere tipo: molto interessante il tuo epoch in secondi,
            * peccato che io lo voglio in millisecondi.
            */
            return new Date(expirationTime * 1000);
        }

        public long getNotBefore() {
            return notBefore;
        }

        public long getIssuedAt() {
            return issuedAt;
        }

        public String getJwtId() {
            return jwtId;
        }
    }

    private transient Payload payload;


    public Jwt(String jwt) {
        this.jwt = jwt;
        getPayload();
    }

    @NotNull
    public Payload getPayload() {
        if (this.payload == null) {
            Gson gson = new Gson();
            Base64.Decoder base64 = Base64.getUrlDecoder();
            String payload = jwt.split("\\.")[1];
            payload = new String(base64.decode(payload));
            this.payload = gson.fromJson(payload, Payload.class);
        }
        return this.payload;
    }

    /**
     * @return The JWT as a String.
     */
    @NotNull
    @Override
    public String toString() {
        return jwt;
    }
}
