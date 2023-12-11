package org.epic_guys.esse4.models;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

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

    /**
     * stato del processo di verbalizzazione degli esiti.  I relaviti Valori sono (C&#x3D;da iniziare, A&#x3D;in fase di svolgimento, F&#x3D; concluso)
     */
    @JsonAdapter(StatoVerbEnum.Adapter.class)
    public enum StatoVerbEnum {
        C("C"),

        A("A"),

        F("F");

        private String value;

        StatoVerbEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static StatoVerbEnum fromValue(String text) {
            for (StatoVerbEnum b : StatoVerbEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        public static class Adapter extends TypeAdapter<StatoVerbEnum> {
            @Override
            public void write(final JsonWriter jsonWriter, final StatoVerbEnum enumeration) throws IOException {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public StatoVerbEnum read(final JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return StatoVerbEnum.fromValue(String.valueOf(value));
            }
        }
    }

    @SerializedName("statoVerb")
    private StatoVerbEnum statoVerb = null;

    /**
     * stato del processo di pubblicazione esiti.  I relaviti Valori sono (C&#x3D;da iniziare, A&#x3D;in fase di svolgimento, F&#x3D; concluso)
     */
    @JsonAdapter(StatoPubblEsitiEnum.Adapter.class)
    public enum StatoPubblEsitiEnum {
        C("C"),

        A("A"),

        F("F");

        private String value;

        StatoPubblEsitiEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static StatoPubblEsitiEnum fromValue(String text) {
            for (StatoPubblEsitiEnum b : StatoPubblEsitiEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        public static class Adapter extends TypeAdapter<StatoPubblEsitiEnum> {
            @Override
            public void write(final JsonWriter jsonWriter, final StatoPubblEsitiEnum enumeration) throws IOException {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public StatoPubblEsitiEnum read(final JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return StatoPubblEsitiEnum.fromValue(String.valueOf(value));
            }
        }
    }

    @SerializedName("statoPubblEsiti")
    private StatoPubblEsitiEnum statoPubblEsiti = null;

    /**
     * stato del processo di inserimento esiti.  I relaviti Valori sono (C&#x3D;da iniziare, A&#x3D;in fase di svolgimento, F&#x3D; concluso)
     */
    @JsonAdapter(StatoInsEsitiEnum.Adapter.class)
    public enum StatoInsEsitiEnum {
        C("C"),

        A("A"),

        F("F");

        private String value;

        StatoInsEsitiEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static StatoInsEsitiEnum fromValue(String text) {
            for (StatoInsEsitiEnum b : StatoInsEsitiEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        public static class Adapter extends TypeAdapter<StatoInsEsitiEnum> {
            @Override
            public void write(final JsonWriter jsonWriter, final StatoInsEsitiEnum enumeration) throws IOException {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public StatoInsEsitiEnum read(final JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return StatoInsEsitiEnum.fromValue(String.valueOf(value));
            }
        }
    }

    @SerializedName("statoInsEsiti")
    private StatoInsEsitiEnum statoInsEsiti = null;

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
        S("S"),

        O("O"),

        SOC("SOC"),

        SOS("SOS");

        private String value;

        TipoEsaCodEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static TipoEsaCodEnum fromValue(String text) {
            for (TipoEsaCodEnum b : TipoEsaCodEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        public static class Adapter extends TypeAdapter<TipoEsaCodEnum> {
            @Override
            public void write(final JsonWriter jsonWriter, final TipoEsaCodEnum enumeration) throws IOException {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public TipoEsaCodEnum read(final JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return TipoEsaCodEnum.fromValue(String.valueOf(value));
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
        S("S"),

        O("O"),

        SO("SO");

        private String value;

        TipoIscrCodEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static TipoIscrCodEnum fromValue(String text) {
            for (TipoIscrCodEnum b : TipoIscrCodEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        public static class Adapter extends TypeAdapter<TipoIscrCodEnum> {
            @Override
            public void write(final JsonWriter jsonWriter, final TipoIscrCodEnum enumeration) throws IOException {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public TipoIscrCodEnum read(final JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return TipoIscrCodEnum.fromValue(String.valueOf(value));
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


    public String getDescrizioneAppello() {
        return desApp;
    }
}