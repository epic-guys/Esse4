package org.epic_guys.esse4.models;

import com.google.gson.annotations.SerializedName;

/**
 * Chiave Attività Didattica Contestualizzata
 */
public class ChiaveADContestualizzata {
    @SerializedName("cdsId")
    private Long cdsId = null;

    @SerializedName("cdsCod")
    private String cdsCod = null;

    @SerializedName("cdsDes")
    private String cdsDes = null;

    @SerializedName("aaOrdId")
    private Integer aaOrdId = null;

    @SerializedName("aaOrdCod")
    private String aaOrdCod = null;

    @SerializedName("aaOrdDes")
    private String aaOrdDes = null;

    @SerializedName("pdsId")
    private Long pdsId = null;

    @SerializedName("pdsCod")
    private String pdsCod = null;

    @SerializedName("pdsDes")
    private String pdsDes = null;

    @SerializedName("aaOffId")
    private Long aaOffId = null;

    @SerializedName("adId")
    private Long adId = null;

    @SerializedName("adCod")
    private String adCod = null;

    @SerializedName("adDes")
    private String adDes = null;

    @SerializedName("afId")
    private Long afId = null;

    public ChiaveADContestualizzata cdsId(Long cdsId) {
        this.cdsId = cdsId;
        return this;
    }

    /**
     * chiave del corso di studio di erogazione dell&#39;attività didattica
     *
     * @return cdsId
     **/
    public Long getCdsId() {
        return cdsId;
    }

    /**
     * codice del corso di studio di erogazione dell&#39;attività didattica
     *
     * @return cdsCod
     **/
    public String getCdsCod() {
        return cdsCod;
    }

    /**
     * descrizione del corso di erogazione dell&#39;attività didattica
     *
     * @return cdsDes
     **/
    public String getCdsDes() {
        return cdsDes;
    }

    /**
     * anno di ordinamento del corso di studio di erogazione dell&#39;attività didattica
     *
     * @return aaOrdId
     **/
    public Integer getAaOrdId() {
        return aaOrdId;
    }

    /**
     * codice dell&#39;&#39;ordinamento di erogazione dell&#39;attività didattica
     *
     * @return aaOrdCod
     **/
    public String getAaOrdCod() {
        return aaOrdCod;
    }

    /**
     * descrizione dell&#39;&#39;ordinamento di erogazione dell&#39;attività didattica
     *
     * @return aaOrdDes
     **/
    public String getAaOrdDes() {
        return aaOrdDes;
    }


    /**
     * chiave del percorso di studio di erogazione dell&#39;attività didattica
     *
     * @return pdsId
     **/
    public Long getPdsId() {
        return pdsId;
    }

    /**
     * codice del percorso di erogazione dell&#39;attività didattica
     *
     * @return pdsCod
     **/
    public String getPdsCod() {
        return pdsCod;
    }

    /**
     * descrizione del percorso di erogazione dell&#39;attività didattica
     *
     * @return pdsDes
     **/
    public String getPdsDes() {
        return pdsDes;
    }


    /**
     * anno di offerta di erogazione dell&#39;attività didattica
     *
     * @return aaOffId
     **/
    public Long getAaOffId() {
        return aaOffId;
    }


    /**
     * chiave dell&#39;attività didattica
     *
     * @return adId
     **/
    public Long getAdId() {
        return adId;
    }


    /**
     * codice dell&#39;&#39;attività didattica
     *
     * @return adCod
     **/
    public String getAdCod() {
        return adCod;
    }

    /**
     * descrizione dell&#39;&#39;attività didattica
     *
     * @return adDes
     **/
    public String getAdDes() {
        return adDes;
    }


    /**
     * id della afId proveniente da U-Gov Didattica
     *
     * @return afId
     **/
    public Long getAfId() {
        return afId;
    }

}
