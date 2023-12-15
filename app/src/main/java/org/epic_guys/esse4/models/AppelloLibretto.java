package org.epic_guys.esse4.models;

import com.google.gson.annotations.SerializedName;

/**
 * AppelloLibretto
 */
public class AppelloLibretto extends Appello {
    @SerializedName("matId")
    private Long matId = null;

    @SerializedName("adsceId")
    private Long adsceId = null;

    @SerializedName("adStuCod")
    private String adStuCod = null;

    @SerializedName("adStuDes")
    private String adStuDes = null;

    @SerializedName("staSceCod")
    private String staSceCod = null;

    /**
     * Id della testata del libretto
     * @return matId
     **/
    public Long getIdCarriera() {
        return matId;
    }

    /**
     * Id della riga del libretto
     * @return adsceId
     **/
    public Long getIdRigaLibretto() {
        return adsceId;
    }

    /**
     * codice dell&#39;attività didattica del libretto
     * @return adStuCod
     **/
    public String getAdStuCod() {
        return adStuCod;
    }

    /**
     * descrizione dell&#39;attività didattica del libretto
     * @return adStuDes
     **/
    public String getAdStuDes() {
        return adStuDes;
    }

    /**
     * Stato dell\\&#39;attività didattica (codice)
     * @return staSceCod
     **/
    public String getStaSceCod() {
        return staSceCod;
    }

}

