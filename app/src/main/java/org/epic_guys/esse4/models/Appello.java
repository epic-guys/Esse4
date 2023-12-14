package org.epic_guys.esse4.models;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import org.epic_guys.esse4.common.EnumAdapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.regex.Pattern;

/**
 * Appello
 */

public class Appello {


    @SerializedName("datacalId")
    private Long datacalId = null;

    @SerializedName("capostipiteId")
    private Long capostipiteId = null;

    @SerializedName("commPianId")
    private Long commPianId = null;

    @SerializedName("indexId")
    private Long indexId = null;

    @SerializedName("periodoId")
    private Long periodoId = null;

    @SerializedName("numVerbaliGen")
    private Integer numVerbaliGen = null;

    @SerializedName("numVerbaliCar")
    private Integer numVerbaliCar = null;

    @SerializedName("numPubblicazioni")
    private Integer numPubblicazioni = null;

    @SerializedName("numIscritti")
    private Integer numIscritti = null;

    @SerializedName("statoLog")
    private String statoLog = null;

    @SerializedName("statoAperturaApp")
    private String statoAperturaApp = null;

    @JsonAdapter(StatoEnum.Adapter.class)
    public enum StatoEnum {
        /**
         * Da iniziare
         */
        C("C", "Da iniziare"),

        /**
         * In fase di svolgimento
         */
        A("A", "In fase di svolgimento"),

        /**
         * Concluso
         */
        F("F", "Concluso");

        private final String value;
        private final String description;

        StatoEnum(String value, String description) {
            this.value = value;
            this.description = description;
        }

        public String getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        public static class Adapter extends EnumAdapter<StatoEnum> {

            @Override
            public Class<StatoEnum> getTClass() {
                return StatoEnum.class;
            }
        }
    }

    /**
     * stato del processo di verbalizzazione degli esiti.  I relaviti Valori sono (C&#x3D;da iniziare, A&#x3D;in fase di svolgimento, F&#x3D; concluso)
     */
    @SerializedName("statoVerb")
    private StatoEnum statoVerb = null;

    /**
     * stato del processo di pubblicazione esiti.  I relaviti Valori sono (C&#x3D;da iniziare, A&#x3D;in fase di svolgimento, F&#x3D; concluso)
     */
    @SerializedName("statoPubblEsiti")
    private StatoEnum statoPubblEsiti = null;

    /**
     * stato del processo di inserimento esiti.  I relaviti Valori sono (C&#x3D;da iniziare, A&#x3D;in fase di svolgimento, F&#x3D; concluso)
     */
    @SerializedName("statoInsEsiti")
    private StatoEnum statoInsEsiti = null;

    @SerializedName("statoDes")
    private String statoDes = null;

    @SerializedName("stato")
    private String stato = null;

    @SerializedName("presidenteNome")
    private String presidenteNome = null;

    @SerializedName("presidenteCognome")
    private String presidenteCognome = null;

    @SerializedName("presidenteId")
    private Long presidenteId = null;

    @SerializedName("tipoGestPrenDes")
    private String tipoGestPrenDes = null;

    @SerializedName("tipoGestAppDes")
    private String tipoGestAppDes = null;

    @SerializedName("tipoDefAppDes")
    private String tipoDefAppDes = null;

    @SerializedName("adDes")
    private String adDes = null;

    @SerializedName("adCod")
    private String adCod = null;

    @SerializedName("cdsDes")
    private String cdsDes = null;

    @SerializedName("cdsCod")
    private String cdsCod = null;

    @SerializedName("tipoAppCod")
    private String tipoAppCod = null;

    @SerializedName("appId")
    private Integer appId = null;

    @SerializedName("appelloId")
    private Long appelloId = null;

    @SerializedName("aaCalId")
    private Integer aaCalId = null;

    @SerializedName("noteSistLog")
    private String noteSistLog = null;

    @SerializedName("note")
    private String note = null;

    @SerializedName("tagTemplId")
    private Long tagTemplId = null;

    @SerializedName("sedeId")
    private Long sedeId = null;

    @SerializedName("gruppoVotoId")
    private Long gruppoVotoId = null;

    @SerializedName("condId")
    private Long condId = null;

    @SerializedName("tipoSceltaTurno")
    private Integer tipoSceltaTurno = null;

    @SerializedName("riservatoFlg")
    private Integer riservatoFlg = null;

    @SerializedName("oraEsa")
    private String oraEsa = null;

    @SerializedName("dataInizioApp")
    private String dataInizioApp = null;

    @SerializedName("dataFineIscr")
    private String dataFineIscr = null;

    @SerializedName("dataInizioIscr")
    private String dataInizioIscr = null;

    /**
     * modalità dell&#39;esame definita nell&#39;appello (valorizzata se il par_conf&#x3D;CONTR_TIPO_ESA_APP&#x3D;0), i possibili valori sono  ( Scritto&#x3D;S, Orale&#x3D;O, Scritto e Orale Congiunto&#x3D;SOC, Scritto e Orale Separato&#x3D;SOS).
     */
    @JsonAdapter(TipoEsaCodEnum.Adapter.class)
    public enum TipoEsaCodEnum {
        S("S", "Scritto"),

        O("O", "Orale"),

        SOC("SOC", "Scritto e Orale Congiunto"),

        SOS("SOS", "Scritto e Orale Separato");

        private String value;
        private String description;

        TipoEsaCodEnum(String value, String description) {
            this.value = value;
            this.description = description;
        }

        public String getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        public static class Adapter extends EnumAdapter<TipoEsaCodEnum> {
            @Override
            public Class<TipoEsaCodEnum> getTClass() {
                return TipoEsaCodEnum.class;
            }
        }
    }

    @SerializedName("tipoEsaCod")
    private TipoEsaCodEnum tipoEsaCod = null;

    /**
     * modalità di iscrizione definita nell&#39;appello, i possibili valori sono  ( Scritto&#x3D;S, Orale&#x3D;O, Scritto e Orale&#x3D;SO).
     */
    @JsonAdapter(TipoIscrCodEnum.Adapter.class)
    public enum TipoIscrCodEnum {
        S("S", "Scritto"),

        O("O", "Orale"),

        SO("SO", "Scritto e Orale");

        private String value;
        private String description;

        TipoIscrCodEnum(String value, String description) {
            this.value = value;
            this.description = description;
        }

        public String getValue() {
            return value;
        }


        public static class Adapter extends EnumAdapter<TipoIscrCodEnum> {
            @Override
            public Class<TipoIscrCodEnum> getTClass() {
                return TipoIscrCodEnum.class;
            }
        }
    }

    @SerializedName("tipoIscrCod")
    private TipoIscrCodEnum tipoIscrCod = null;

    @SerializedName("tipoGestPrenCod")
    private String tipoGestPrenCod = null;

    @SerializedName("tipoGestAppCod")
    private String tipoGestAppCod = null;

    @SerializedName("tipoDefAppCod")
    private String tipoDefAppCod = null;

    @SerializedName("desApp")
    private String desApp = null;

    @SerializedName("adId")
    private Long adId = null;

    @SerializedName("cdsId")
    private Long cdsId = null;




    public static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }


    public String getDescrizioneAppello() {
        return desApp;
    }

    public Long getDatacalId() {
        return datacalId;
    }

    public Long getCapostipiteId() {
        return capostipiteId;
    }

    public Long getCommPianId() {
        return commPianId;
    }

    public Long getIndexId() {
        return indexId;
    }

    public Long getPeriodoId() {
        return periodoId;
    }

    public Integer getNumVerbaliGen() {
        return numVerbaliGen;
    }

    public Integer getNumVerbaliCar() {
        return numVerbaliCar;
    }

    public Integer getNumPubblicazioni() {
        return numPubblicazioni;
    }

    public Integer getNumIscritti() {
        return numIscritti;
    }

    public String getStatoLog() {
        return statoLog;
    }

    public String getStatoAperturaApp() {
        return statoAperturaApp;
    }

    public StatoEnum getStatoVerb() {
        return statoVerb;
    }

    public StatoEnum getStatoPubblEsiti() {
        return statoPubblEsiti;
    }

    public StatoEnum getStatoInsEsiti() {
        return statoInsEsiti;
    }

    public String getStatoDes() {
        return statoDes;
    }

    public String getStato() {
        return stato;
    }

    public String getPresidenteNome() {
        return presidenteNome;
    }

    public String getPresidenteCognome() {
        return presidenteCognome;
    }

    public String getPresidenteNomeCognome() {
        return presidenteNome + ' ' + presidenteCognome;
    }

    public Long getPresidenteId() {
        return presidenteId;
    }

    public String getTipoGestPrenDes() {
        return tipoGestPrenDes;
    }

    public String getTipoGestAppDes() {
        return tipoGestAppDes;
    }

    public String getTipoDefAppDes() {
        return tipoDefAppDes;
    }

    public String getAdDes() {
        return adDes;
    }

    public String getAdCod() {
        return adCod;
    }

    public String getCdsDes() {
        return cdsDes;
    }

    public String getCdsCod() {
        return cdsCod;
    }

    public String getTipoAppCod() {
        return tipoAppCod;
    }

    public Integer getAppId() {
        return appId;
    }

    public Long getAppelloId() {
        return appelloId;
    }

    public Integer getAaCalId() {
        return aaCalId;
    }

    public String getNoteSistLog() {
        return noteSistLog;
    }

    public String getNote() {
        return note;
    }

    public Long getTagTemplId() {
        return tagTemplId;
    }

    public Long getSedeId() {
        return sedeId;
    }

    public Long getGruppoVotoId() {
        return gruppoVotoId;
    }

    public Long getCondId() {
        return condId;
    }

    public Integer getTipoSceltaTurno() {
        return tipoSceltaTurno;
    }

    public Integer getRiservatoFlg() {
        return riservatoFlg;
    }

    public String getOraEsa() {
        return oraEsa;
    }

    /**
     * La data è sempre 01/01/1990 quindi viene scartata.
     * Restituire solo l'ora dell'esame.
     * @return l'ora dell'esame.
     */
    public LocalTime getOraEsame() {
        return Appello.getDateTimeFormatter().parse(oraEsa, LocalTime::from);
    }

    public String getDataInizioApp() {
        return dataInizioApp;
    }

    public LocalDate getDataInizioAppello() {
        return Appello.getDateTimeFormatter().parse(dataInizioApp, LocalDate::from);
    }

    public LocalDateTime getDataOraEsame() {
        return getDataInizioAppello().atTime(getOraEsame());
    }



    public String getDataFineIscr() {
        return dataFineIscr.substring(0, dataFineIscr.length() - 9);
    }

    public String getDataInizioIscr() {
        return dataInizioIscr.substring(0, dataFineIscr.length() - 9);
    }

    public TipoEsaCodEnum getTipoEsaCod() {
        return tipoEsaCod;
    }

    public TipoIscrCodEnum getTipoIscrCod() {
        return tipoIscrCod;
    }

    public String getTipoGestPrenCod() {
        return tipoGestPrenCod;
    }

    public String getTipoGestAppCod() {
        return tipoGestAppCod;
    }

    public String getTipoDefAppCod() {
        return tipoDefAppCod;
    }

    public String getDesApp() {
        return desApp;
    }

    public Long getAdId() {
        return adId;
    }

    public Long getCdsId() {
        return cdsId;
    }
}