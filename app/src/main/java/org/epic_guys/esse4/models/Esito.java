package org.epic_guys.esse4.models;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Esito
 */
public class Esito {
    /**
     * Tipo di modalità con cui viene valutato l&#39;esame.  Può assumere i valori V oppure G, se il valore è V allora al momento del superamento viene valorizzato il campo voto,  altrimenti se il valore è G viene valorizzato il campo tipo_giud_cod
     */

    @JsonAdapter(ModValCodEnum.Adapter.class)
    public enum ModValCodEnum {
        V("V", "Voto"),

        G("G", "Giudizio");

        private String value;
        private String descrizione;

        ModValCodEnum(String value, String descrizione) {
            this.value = value;
            this.descrizione = descrizione;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static ModValCodEnum fromValue(String text) {
            for (ModValCodEnum b : ModValCodEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        public static class Adapter extends TypeAdapter<ModValCodEnum> {
            @Override
            public void write(final JsonWriter jsonWriter, final ModValCodEnum enumeration) throws IOException {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public ModValCodEnum read(final JsonReader jsonReader) throws IOException {
                jsonReader.beginObject();
                jsonReader.nextName();
                String value = jsonReader.nextString();
                jsonReader.endObject();
                return ModValCodEnum.fromValue(String.valueOf(value));
            }
        }
    }

    @SerializedName("modValCod")
    private ModValCodEnum modValCod = null;

    @SerializedName("supEsaFlg")
    private Integer supEsaFlg = null;

    @SerializedName("voto")
    private Float voto = null;

    @SerializedName("lodeFlg")
    private Integer lodeFlg = null;

    @SerializedName("tipoGiudCod")
    private String tipoGiudCod = null;

    @SerializedName("tipoGiudDes")
    private String tipoGiudDes = null;

    @SerializedName("dataEsa")
    private String dataEsa = null;

    @SerializedName("aaSupId")
    private Integer aaSupId = null;


    /**
     * Tipo di modalità con cui viene valutato l&#39;esame.  Può assumere i valori V oppure G, se il valore è V allora al momento del superamento viene valorizzato il campo voto,  altrimenti se il valore è G viene valorizzato il campo tipo_giud_cod
     *
     * @return modValCod
     **/
    public ModValCodEnum getModValCod() {
        return modValCod;
    }


    /**
     * flag che indica se l&#39;&#39;esito è positivo
     * minimum: 0
     * maximum: 1
     *
     * @return supEsaFlg
     **/
    public Integer getEsameSuperatoFlag() {
        return supEsaFlg;
    }

    public boolean isSuperato() {
        return getEsameSuperatoFlag() == 1;
    }

    /**
     * voto, valorizzato se modValCod è V.  Gli esiti delle prove finali (cioè quelle che prevedono il caricamento nella riga di libretto) sono INTERI,  gli esti di prove parziali invece possono avere 2 cifre decimali
     *
     * @return voto
     **/
    public Float getVoto() {
        return voto;
    }

    /**
     * flag che indica la lode, impostato a 1 solo per modValCod è V e la lode deve essere impostata
     * minimum: 0
     * maximum: 1
     *
     * @return lodeFlg
     **/
    public Integer getLodeFlag() {
        return lodeFlg;
    }

    /**
     * codice che indica il tipo di giudizio utilizzato, valorizzato solo se modValCod è G
     *
     * @return tipoGiudCod
     **/
    public String getTipoGiudCod() {
        return tipoGiudCod;
    }

    /**
     * descrizione che indica il tipo di giudizio utilizzato, valorizzato solo se modValCod è G
     *
     * @return tipoGiudDes
     **/
    public String getTipoGiudDes() {
        return tipoGiudDes;
    }


    /**
     * data della prova, il formato con cui deve essere definita la data è DD/MM/YYYY
     *
     * @return dataEsa
     **/
    public String getDataEsa() {
        return dataEsa;
    }


    /**
     * anno di superamento della prova
     *
     * @return aaSupId
     **/
    public Integer getAaSupId() {
        return aaSupId;
    }
}
