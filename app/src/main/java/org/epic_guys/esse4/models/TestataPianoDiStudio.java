package org.epic_guys.esse4.models;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import org.epic_guys.esse4.common.EnumAdapter;

import java.io.IOException;

public class TestataPianoDiStudio {
    @SerializedName("stuId")
    private Long stuId = null;

    @SerializedName("pianoId")
    private Integer pianoId = null;

    @SerializedName("staStuCod")
    private String staStuCod = null;

    @SerializedName("motStastuCod")
    private String motStastuCod = null;

    @SerializedName("staMatCod")
    private String staMatCod = null;

    @SerializedName("motStamatCod")
    private String motStamatCod = null;

    @SerializedName("matId")
    private Long matId = null;

    @SerializedName("regsceId")
    private Long regsceId = null;

    @SerializedName("schemaId")
    private Long schemaId = null;

    @SerializedName("finregsceId")
    private Long finregsceId = null;

    /**
     * stato del piano (B &#x3D;&gt; Bozza, P &#x3D;&gt; Proposto, V &#x3D;&gt; in Valutuazione, A &#x3D;&gt; Approvato, R &#x3D;&gt; Respinto, X &#x3D;&gt; Annullato)
     */
    @JsonAdapter(StatoEnum.Adapter.class)
    public enum StatoEnum {
        B("B"),

        P("P"),

        V("V"),

        A("A"),

        R("R"),

        X("X");

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

        public static class Adapter extends EnumAdapter<StatoEnum> {
            @Override
            public Class<StatoEnum> getTClass() {
                return StatoEnum.class;
            }
        }
    }

    @SerializedName("stato")
    private StatoEnum stato = null;

    @SerializedName("statoDes")
    private String statoDes = null;

    @SerializedName("dataUltimaVarStato")
    private String dataUltimaVarStato = null;

    /**
     * tipo di piano (S &#x3D;&gt; Standard, I &#x3D;&gt; Individuale)
     */
    @JsonAdapter(TipoPianoEnum.Adapter.class)
    public enum TipoPianoEnum {
        S("S"),

        I("I");

        private String value;

        TipoPianoEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static TipoPianoEnum fromValue(String text) {
            for (TipoPianoEnum b : TipoPianoEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        public static class Adapter extends EnumAdapter<TipoPianoEnum> {
            @Override
            public Class<TipoPianoEnum> getTClass() {
                return TipoPianoEnum.class;
            }
        }
    }

    @SerializedName("tipoPiano")
    private TipoPianoEnum tipoPiano = null;

    @SerializedName("statutarioFlg")
    private Integer statutarioFlg = null;

    @SerializedName("coorte")
    private Integer coorte = null;

    @SerializedName("aaRevisioneId")
    private Integer aaRevisioneId = null;

    @SerializedName("cdsStuId")
    private Long cdsStuId = null;

    @SerializedName("cdsStuCod")
    private String cdsStuCod = null;

    @SerializedName("cdsStuDes")
    private String cdsStuDes = null;

    @SerializedName("aaOrdStuId")
    private Long aaOrdStuId = null;

    @SerializedName("pdsStuId")
    private Long pdsStuId = null;

    @SerializedName("pdsStuCod")
    private String pdsStuCod = null;

    @SerializedName("pdsDes")
    private String pdsDes = null;

    @SerializedName("pdsSceId")
    private Long pdsSceId = null;

    @SerializedName("pdsSceCod")
    private String pdsSceCod = null;

    @SerializedName("pdsSceDes")
    private String pdsSceDes = null;

    @SerializedName("aaDefId")
    private Integer aaDefId = null;

    @SerializedName("aaIscrId")
    private Integer aaIscrId = null;

    @SerializedName("aptId")
    private Long aptId = null;

    @SerializedName("aptCod")
    private String aptCod = null;

    @SerializedName("aptDes")
    private String aptDes = null;

    @SerializedName("aaUltimaAttuazioneId")
    private Integer aaUltimaAttuazioneId = null;

    @SerializedName("dataUltimaAttuazione")
    private String dataUltimaAttuazione = null;

    @SerializedName("userUltimaAttuazione")
    private String userUltimaAttuazione = null;

    @SerializedName("userControllo")
    private String userControllo = null;

    @SerializedName("notaControllo")
    private String notaControllo = null;

    @SerializedName("noteSistema")
    private String noteSistema = null;

    @SerializedName("noteUtente")
    private String noteUtente = null;

    @SerializedName("extCod")
    private String extCod = null;

    public Long getStuId() {
        return stuId;
    }

    public Integer getPianoId() {
        return pianoId;
    }

    public String getStaStuCod() {
        return staStuCod;
    }

    public String getMotStastuCod() {
        return motStastuCod;
    }

    public String getStaMatCod() {
        return staMatCod;
    }

    public String getMotStamatCod() {
        return motStamatCod;
    }

    public Long getMatId() {
        return matId;
    }

    public Long getRegsceId() {
        return regsceId;
    }

    public Long getSchemaId() {
        return schemaId;
    }

    public Long getFinregsceId() {
        return finregsceId;
    }

    public StatoEnum getStato() {
        return stato;
    }

    public String getStatoDes() {
        return statoDes;
    }

    public String getDataUltimaVarStato() {
        return dataUltimaVarStato;
    }

    public TipoPianoEnum getTipoPiano() {
        return tipoPiano;
    }

    public Integer getStatutarioFlg() {
        return statutarioFlg;
    }

    public Integer getCoorte() {
        return coorte;
    }

    public Integer getAaRevisioneId() {
        return aaRevisioneId;
    }

    public Long getCdsStuId() {
        return cdsStuId;
    }

    public String getCdsStuCod() {
        return cdsStuCod;
    }

    public String getCdsStuDes() {
        return cdsStuDes;
    }

    public Long getAaOrdStuId() {
        return aaOrdStuId;
    }

    public Long getPdsStuId() {
        return pdsStuId;
    }

    public String getPdsStuCod() {
        return pdsStuCod;
    }

    public String getPdsDes() {
        return pdsDes;
    }

    public Long getPdsSceId() {
        return pdsSceId;
    }

    public String getPdsSceCod() {
        return pdsSceCod;
    }

    public String getPdsSceDes() {
        return pdsSceDes;
    }

    public Integer getAaDefId() {
        return aaDefId;
    }

    public Integer getAaIscrId() {
        return aaIscrId;
    }

    public Long getAptId() {
        return aptId;
    }

    public String getAptCod() {
        return aptCod;
    }

    public String getAptDes() {
        return aptDes;
    }

    public Integer getAaUltimaAttuazioneId() {
        return aaUltimaAttuazioneId;
    }

    public String getDataUltimaAttuazione() {
        return dataUltimaAttuazione;
    }

    public String getUserUltimaAttuazione() {
        return userUltimaAttuazione;
    }

    public String getUserControllo() {
        return userControllo;
    }

    public String getNotaControllo() {
        return notaControllo;
    }

    public String getNoteSistema() {
        return noteSistema;
    }

    public String getNoteUtente() {
        return noteUtente;
    }

    public String getExtCod() {
        return extCod;
    }
}