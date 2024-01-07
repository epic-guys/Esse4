package org.epic_guys.esse4.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * DettaglioErrore
 */
public class ApiError {
    @SerializedName("statusCode")
    private Integer statusCode = null;

    @SerializedName("retCode")
    private Integer retCode = null;

    @SerializedName("retErrMsg")
    private String retErrMsg = null;

    /**
     * Http Status Code
     * @return statusCode
     **/
    @Nullable
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * codice di errore
     * @return retCode
     **/
    @Nullable
    public Integer getRetCode() {
        return retCode;
    }

    /**
     * descrizione dell&#39;errore
     * @return retErrMsg
     **/
    @Nullable
    public String getErrorMessage() {
        return retErrMsg;
    }
}
