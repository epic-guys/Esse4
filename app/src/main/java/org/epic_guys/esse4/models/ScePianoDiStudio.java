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

public class ScePianoDiStudio {
    @SerializedName("stuId")
    private Long stuId = null;

    @SerializedName("pianoId")
    private Integer pianoId = null;

    @SerializedName("matId")
    private Long matId = null;

    @SerializedName("schemaId")
    private Long schemaId = null;

    @SerializedName("scePianoId")
    private Integer scePianoId = null;

    @SerializedName("sceltaId")
    private Long sceltaId = null;

    @SerializedName("ordNum")
    private Integer ordNum = null;

    @SerializedName("des")
    private String des = null;

    @SerializedName("ptSlotId")
    private Long ptSlotId = null;

    @SerializedName("ptSlotCod")
    private String ptSlotCod = null;

    @SerializedName("ptSlotDes")
    private String ptSlotDes = null;

    @SerializedName("annoCorso")
    private Integer annoCorso = null;

    @SerializedName("annoCorsoAnticipo")
    private Integer annoCorsoAnticipo = null;

    /**
     * tipo di regola di scelta ( O &#x3D;&gt; Obbligatoria; F &#x3D;&gt; Da Elenco; T &#x3D;&gt; Da Elenco Libero da OD; G &#x3D;&gt; Gruppo; W &#x3D;&gt; Gruppo Libero da OD; S &#x3D;&gt; Libera da OD; V &#x3D;&gt; Vincolo; D &#x3D;&gt; Regola per Dottorati)
     */
    @JsonAdapter(TipSceEnum.Adapter.class)
    public enum TipSceEnum {
        O("O"),

        F("F"),

        T("T"),

        G("G"),

        W("W"),

        S("S"),

        V("V"),

        D("D");

        private String value;

        TipSceEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static TipSceEnum fromValue(String text) {
            for (TipSceEnum b : TipSceEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        public static class Adapter extends EnumAdapter<TipSceEnum> {
            @Override
            public Class<TipSceEnum> getTClass() {
                return TipSceEnum.class;
            }
        }
    }

    @SerializedName("tipSce")
    private TipSceEnum tipSce = null;

    @SerializedName("tipSceDes")
    private String tipSceDes = null;

    /**
     * tipo di unità di misura
     */
    @JsonAdapter(TipUntEnum.Adapter.class)
    public enum TipUntEnum {
        BLK("BLK"),

        CFU("CFU"),

        ANN("ANN");

        private String value;

        TipUntEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static TipUntEnum fromValue(String text) {
            for (TipUntEnum b : TipUntEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        public static class Adapter extends EnumAdapter<TipUntEnum> {
            @Override
            public Class<TipUntEnum> getTClass() {
                return TipUntEnum.class;
            }
        }
    }

    @SerializedName("tipUnt")
    private TipUntEnum tipUnt = null;

    @SerializedName("minUnt")
    private Float minUnt = null;

    @SerializedName("maxUnt")
    private Float maxUnt = null;

    /**
     * modificatore del TAF della regola di scelta
     */
    @JsonAdapter(ModTAFEnum.Adapter.class)
    public enum ModTAFEnum {
        A("A"),

        B("B"),

        C("C"),

        D("D"),

        E("E"),

        F("F"),

        G("G"),

        S("S");

        private String value;

        ModTAFEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static ModTAFEnum fromValue(String text) {
            for (ModTAFEnum b : ModTAFEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        public static class Adapter extends EnumAdapter<ModTAFEnum> {
            @Override
            public Class<ModTAFEnum> getTClass() {
                return ModTAFEnum.class;
            }
        }
    }

    @SerializedName("modTAF")
    private ModTAFEnum modTAF = null;

    @SerializedName("opzFlg")
    private Integer opzFlg = null;

    @SerializedName("tesorettoFlg")
    private Integer tesorettoFlg = null;

    @SerializedName("sovranFlg")
    private Integer sovranFlg = null;

    @SerializedName("azzeraCfuFlg")
    private Integer azzeraCfuFlg = null;

    @SerializedName("abilFlg")
    private Integer abilFlg = null;

    @SerializedName("regolaSistemaFlg")
    private Integer regolaSistemaFlg = null;

    @SerializedName("extCod")
    private String extCod = null;
}
