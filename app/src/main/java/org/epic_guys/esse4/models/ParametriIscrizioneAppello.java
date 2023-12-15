package org.epic_guys.esse4.models;

import com.google.gson.annotations.SerializedName;

/**
 * ParametriIscrizioneAppello
 */
public class ParametriIscrizioneAppello {
    @SerializedName("adsceId")
    private long adsceId;

    @SerializedName("tipoIscrStu")
    private String tipoIscrStu = null;

    @SerializedName("notaStu")
    private String notaStu = null;

    @SerializedName("appLogId")
    private Integer appLogId = null;

    @SerializedName("tagCod")
    private String tagCod = null;

    @SerializedName("attoreCod")
    private String attoreCod = null;

    @SerializedName("tipoSvolgimentoEsame")
    private String tipoSvolgimentoEsame = null;

    public ParametriIscrizioneAppello(long idRigaLibretto) {
        this.adsceId = idRigaLibretto;
    }


    public ParametriIscrizioneAppello adsceId(Long adsceId) {
        this.adsceId = adsceId;
        return this;
    }

    /**
     * id della riga di libretto da prenotare
     * minimum: 1
     * @return adsceId
     **/

    public long getAdsceId() {
        return adsceId;
    }

    public void setAdsceId(Long adsceId) {
        this.adsceId = adsceId;
    }

    public ParametriIscrizioneAppello tipoIscrStu(String tipoIscrStu) {
        this.tipoIscrStu = tipoIscrStu;
        return this;
    }

    /**
     * tipo di iscrizione dello studente
     * @return tipoIscrStu
     **/
    public String getTipoIscrStu() {
        return tipoIscrStu;
    }

    public void setTipoIscrStu(String tipoIscrStu) {
        this.tipoIscrStu = tipoIscrStu;
    }

    public ParametriIscrizioneAppello notaStu(String notaStu) {
        this.notaStu = notaStu;
        return this;
    }

    /**
     * nota dello studente inserita in fase di prenotazione
     * @return notaStu
     **/
    public String getNotaStu() {
        return notaStu;
    }

    public void setNotaStu(String notaStu) {
        this.notaStu = notaStu;
    }

    public ParametriIscrizioneAppello appLogId(Integer appLogId) {
        this.appLogId = appLogId;
        return this;
    }

    /**
     * eventuale turno a cui prenotare lo studente, se vuoto viene assegnato dal sistema
     * minimum: 1
     * @return appLogId
     **/
    public Integer getAppLogId() {
        return appLogId;
    }

    public void setAppLogId(Integer appLogId) {
        this.appLogId = appLogId;
    }

    public ParametriIscrizioneAppello tagCod(String tagCod) {
        this.tagCod = tagCod;
        return this;
    }

    /**
     * tag selezionato dallo studente in fase di prenotazione
     * @return tagCod
     **/

    public String getTagCod() {
        return tagCod;
    }

    public void setTagCod(String tagCod) {
        this.tagCod = tagCod;
    }

    public ParametriIscrizioneAppello attoreCod(String attoreCod) {
        this.attoreCod = attoreCod;
        return this;
    }

    /**
     * eventuale tipo di attore con cui si vuole effettuare la preotazione, valido solo se l&#39;utente che effettua la chiamata è un utente tecnico
     * @return attoreCod
     **/

    public String getAttoreCod() {
        return attoreCod;
    }

    public void setAttoreCod(String attoreCod) {
        this.attoreCod = attoreCod;
    }

    public ParametriIscrizioneAppello tipoSvolgimentoEsame(String tipoSvolgimentoEsame) {
        this.tipoSvolgimentoEsame = tipoSvolgimentoEsame;
        return this;
    }

    /**
     * tipo di svolgimento esame, se null viene imposato il default previsto nella tipi_gest_app, altrimenti viene inserito il valore richiesto. Lo studente può selezionare solo il default oppure un valore con richiesta_flg &#x3D; 1
     * @return tipoSvolgimentoEsame
     **/

    public String getTipoSvolgimentoEsame() {
        return tipoSvolgimentoEsame;
    }

    public void setTipoSvolgimentoEsame(String tipoSvolgimentoEsame) {
        this.tipoSvolgimentoEsame = tipoSvolgimentoEsame;
    }
}

