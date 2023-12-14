package org.epic_guys.esse4.models;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class UDPianoDiStudio {
    @SerializedName("stuId")
    private Long stuId = null;

    @SerializedName("pianoId")
    private Integer pianoId = null;

    @SerializedName("itmId")
    private Integer itmId = null;

    @SerializedName("matId")
    private Long matId = null;

    @SerializedName("schemaId")
    private Long schemaId = null;

    @SerializedName("scePianoId")
    private Integer scePianoId = null;

    @SerializedName("sceltaId")
    private Long sceltaId = null;

    @SerializedName("sceltaAdId")
    private Integer sceltaAdId = null;

    @SerializedName("udId")
    private Integer udId = null;

    @SerializedName("udCod")
    private String udCod = null;

    @SerializedName("udDes")
    private String udDes = null;

    @SerializedName("peso")
    private Float peso = null;

}
