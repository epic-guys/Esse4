package org.epic_guys.esse4.models;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;


/**
 * RigaLibretto
 */

public class RigaLibretto {
    @SerializedName("matId")
    private Long matId = null;

    @SerializedName("ord")
    private Integer ord = null;

    @SerializedName("adsceId")
    private Long adsceId = null;

    @SerializedName("stuId")
    private Long stuId = null;

    @SerializedName("pianoId")
    private Long pianoId = null;

    @SerializedName("itmId")
    private Long itmId = null;

    @SerializedName("ragId")
    private Long ragId = null;

    /**
     * contiene la tipologia del tipo di raggruppamento, valorizzato solo sul padre del raggruppamento (cioà quando ragId&#x3D;adsceId)
     */
    @JsonAdapter(RaggEsaTipoEnum.Adapter.class)
    public enum RaggEsaTipoEnum {
        ESA("ESA"),

        FREQ("FREQ");

        private String value;

        RaggEsaTipoEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static RaggEsaTipoEnum fromValue(String text) {
            for (RaggEsaTipoEnum b : RaggEsaTipoEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        public static class Adapter extends TypeAdapter<RaggEsaTipoEnum> {
            @Override
            public void write(final JsonWriter jsonWriter, final RaggEsaTipoEnum enumeration) throws IOException {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public RaggEsaTipoEnum read(final JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return RaggEsaTipoEnum.fromValue(String.valueOf(value));
            }
        }
    }

    @SerializedName("raggEsaTipo")
    private RaggEsaTipoEnum raggEsaTipo = null;

    @SerializedName("adCod")
    private String adCod = null;

    @SerializedName("adDes")
    private String adDes = null;

    @SerializedName("annoCorso")
    private Integer annoCorso = null;

    /**
     * Stato dell\\&#39;attività didattica (codice)
     */
    @JsonAdapter(StatoEnum.Adapter.class)
    public enum StatoEnum {
        P("P"),

        F("F"),

        S("S");

        private String value;

        StatoEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static StatoEnum fromValue(String text) {
            for (StatoEnum b : StatoEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        public static class Adapter extends TypeAdapter<StatoEnum> {
            @Override
            public void write(final JsonWriter jsonWriter, final StatoEnum enumeration) throws IOException {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public StatoEnum read(final JsonReader jsonReader) throws IOException {
                jsonReader.beginObject();
                jsonReader.nextName();
                String value = jsonReader.nextString();
                jsonReader.endObject();
                return StatoEnum.fromValue(String.valueOf(value));
            }
        }
    }

    @SerializedName("stato")
    private StatoEnum stato = null;

    @SerializedName("statoDes")
    private String statoDes = null;

    @SerializedName("chiaveADContestualizzata")
    private ChiaveADContestualizzata chiaveADContestualizzata = null;

    @SerializedName("tipoEsaCod")
    private String tipoEsaCod = null;

    @SerializedName("tipoEsaDes")
    private String tipoEsaDes = null;

    @SerializedName("tipoInsCod")
    private String tipoInsCod = null;

    @SerializedName("tipoInsDes")
    private String tipoInsDes = null;

    @SerializedName("ricId")
    private Integer ricId = null;

    @SerializedName("peso")
    private Float peso = null;

    @SerializedName("aaFreqId")
    private Integer aaFreqId = null;

    @SerializedName("dataFreq")
    private String dataFreq = null;

    @SerializedName("freqUffFlg")
    private Integer freqUffFlg = null;

    @SerializedName("freqObbligFlg")
    private Integer freqObbligFlg = null;

    @SerializedName("dataScadIscr")
    private String dataScadIscr = null;

    @SerializedName("gruppoVotoId")
    private Integer gruppoVotoId = null;

    @SerializedName("gruppoVotoMinVoto")
    private Integer gruppoVotoMinVoto = null;

    @SerializedName("gruppoVotoMaxVoto")
    private Integer gruppoVotoMaxVoto = null;

    @SerializedName("gruppoVotoLodeFlg")
    private Integer gruppoVotoLodeFlg = null;

    @SerializedName("gruppoGiudCod")
    private String gruppoGiudCod = null;

    @SerializedName("gruppoGiudDes")
    private String gruppoGiudDes = null;

    @SerializedName("esito")
    private Esito esito = null;

    @SerializedName("sovranFlg")
    private Integer sovranFlg = null;

    @SerializedName("note")
    private String note = null;

    @SerializedName("debitoFlg")
    private Integer debitoFlg = null;

    @SerializedName("ofaFlg")
    private Integer ofaFlg = null;

    @SerializedName("annoCorsoAnticipo")
    private Integer annoCorsoAnticipo = null;

    @SerializedName("genAutoFlg")
    private Integer genAutoFlg = null;

    @SerializedName("genRicSpecFlg")
    private Integer genRicSpecFlg = null;

    @SerializedName("tipoOrigEvecar")
    private Integer tipoOrigEvecar = null;

    @SerializedName("urlSitoWeb")
    private String urlSitoWeb = null;

    @SerializedName("statoMissione")
    private String statoMissione = null;

    @SerializedName("statoMissioneDes")
    private String statoMissioneDes = null;

    @SerializedName("numAppelliPrenotabili")
    private Integer numAppelliPrenotabili = null;

    @SerializedName("superataFlg")
    private Integer superataFlg = null;

    @SerializedName("numPrenotazioni")
    private Integer numPrenotazioni = null;


    public RigaLibretto matId(Long matId) {
        this.matId = matId;
        return this;
    }


    public Long getIdCarriera() {
        return matId;
    }

    public Integer getOrd() {
        return ord;
    }

    public Long getAdsceId() {
        return adsceId;
    }

    public Long getStuId() {
        return stuId;
    }

    public Long getPianoId() {
        return pianoId;
    }

    public Long getItmId() {
        return itmId;
    }

    public Long getRagId() {
        return ragId;
    }

    public RaggEsaTipoEnum getRaggEsaTipo() {
        return raggEsaTipo;
    }

    public String getAdCod() {
        return adCod;
    }

    public String getDescrizioneAttivitaDidattica() {
        return adDes;
    }

    public Integer getAnnoCorso() {
        return annoCorso;
    }

    public StatoEnum getStato() {
        return stato;
    }

    public String getDescrizioneStato() {
        return statoDes;
    }

    public ChiaveADContestualizzata getChiaveADContestualizzata() {
        return chiaveADContestualizzata;
    }

    public String getTipoEsaCod() {
        return tipoEsaCod;
    }

    public String getTipoEsaDes() {
        return tipoEsaDes;
    }

    public String getTipoInsCod() {
        return tipoInsCod;
    }

    public String getTipoInsDes() {
        return tipoInsDes;
    }

    public Integer getRicId() {
        return ricId;
    }

    public Float getPeso() {
        return peso;
    }

    public Integer getAaFreqId() {
        return aaFreqId;
    }

    public String getDataFreq() {
        return dataFreq;
    }

    public Integer getFreqUffFlg() {
        return freqUffFlg;
    }

    public Integer getFreqObbligFlg() {
        return freqObbligFlg;
    }

    public String getDataScadIscr() {
        return dataScadIscr;
    }

    public Integer getGruppoVotoId() {
        return gruppoVotoId;
    }

    public Integer getGruppoVotoMinVoto() {
        return gruppoVotoMinVoto;
    }

    public Integer getGruppoVotoMaxVoto() {
        return gruppoVotoMaxVoto;
    }

    public Integer getGruppoVotoLodeFlg() {
        return gruppoVotoLodeFlg;
    }

    public String getGruppoGiudCod() {
        return gruppoGiudCod;
    }

    public String getGruppoGiudDes() {
        return gruppoGiudDes;
    }

    public Esito getEsito() {
        return esito;
    }

    public Integer getSovranFlg() {
        return sovranFlg;
    }

    public String getNote() {
        return note;
    }

    public Integer getDebitoFlg() {
        return debitoFlg;
    }

    public Integer getOfaFlg() {
        return ofaFlg;
    }

    public Integer getAnnoCorsoAnticipo() {
        return annoCorsoAnticipo;
    }

    public Integer getGenAutoFlg() {
        return genAutoFlg;
    }

    public Integer getGenRicSpecFlg() {
        return genRicSpecFlg;
    }

    public Integer getTipoOrigEvecar() {
        return tipoOrigEvecar;
    }

    public String getUrlSitoWeb() {
        return urlSitoWeb;
    }

    public String getStatoMissione() {
        return statoMissione;
    }

    public String getStatoMissioneDes() {
        return statoMissioneDes;
    }

    public Integer getNumAppelliPrenotabili() {
        return numAppelliPrenotabili;
    }

    public Integer getSuperataFlg() {
        return superataFlg;
    }

    public Integer getNumPrenotazioni() {
        return numPrenotazioni;
    }
}
