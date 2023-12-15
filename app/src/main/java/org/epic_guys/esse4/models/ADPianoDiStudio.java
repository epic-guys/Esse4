package org.epic_guys.esse4.models;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ADPianoDiStudio {
    @SerializedName("stuId")
    private Long stuId = null;

    @SerializedName("pianoId")
    private Integer pianoId = null;

    @SerializedName("scePianoId")
    private Integer scePianoId = null;

    @SerializedName("itmId")
    private Integer itmId = null;

    @SerializedName("matId")
    private Long matId = null;

    @SerializedName("schemaId")
    private Long schemaId = null;

    @SerializedName("sceltaId")
    private Long sceltaId = null;

    @SerializedName("sceltaAdId")
    private Integer sceltaAdId = null;

    @SerializedName("chiaveADContestualizzata")
    private AttivitaDidatticaContestualizzata chiaveADContestualizzata = null;

    @SerializedName("adsceId")
    private Long adsceId = null;

    @SerializedName("adsceAttId")
    private Long adsceAttId = null;

    @SerializedName("sceltaFlg")
    private Integer sceltaFlg = null;

    @SerializedName("tesorettoFlg")
    private Integer tesorettoFlg = null;

    @SerializedName("ragId")
    private Integer ragId = null;

    @SerializedName("adragoffId")
    private Long adragoffId = null;

    @SerializedName("tipoRagCod")
    private String tipoRagCod = null;

    @SerializedName("adLibCod")
    private String adLibCod = null;

    @SerializedName("adLibDes")
    private String adLibDes = null;

    @SerializedName("peso")
    private Float peso = null;

    @SerializedName("pesoAdVis")
    private Float pesoAdVis = null;

    @SerializedName("linguaSceCod")
    private String linguaSceCod = null;

    @SerializedName("linguaSceNum")
    private Long linguaSceNum = null;

    @SerializedName("udSelezionate")
    private List<UDPianoDiStudio> udSelezionate = null;

    @SerializedName("conteggiabileFlg")
    private Integer conteggiabileFlg = null;

    @SerializedName("deliberaFlg")
    private Integer deliberaFlg = null;

    @SerializedName("statoAttuazione")
    private Integer statoAttuazione = null;

    @SerializedName("partEffCod")
    private String partEffCod = null;

    @SerializedName("linguaDidId")
    private String linguaDidId = null;

    @SerializedName("linguaDidCod")
    private String linguaDidCod = null;

    @SerializedName("linguaDidDes")
    private String linguaDidDes = null;

    @SerializedName("tipoDidCod")
    private String tipoDidCod = null;

    @SerializedName("tipoDidDes")
    private String tipoDidDes = null;

    @SerializedName("grpAdlogCod")
    private String grpAdlogCod = null;

    @SerializedName("grpAdlogDes")
    private String grpAdlogDes = null;

    @SerializedName("annoCorso")
    private Integer annoCorso = null;

    @SerializedName("annoCorsoAnticipo")
    private Integer annoCorsoAnticipo = null;

    public Long getStuId() {
        return stuId;
    }

    public Integer getPianoId() {
        return pianoId;
    }

    public Integer getScePianoId() {
        return scePianoId;
    }

    public Integer getItmId() {
        return itmId;
    }

    public Long getMatId() {
        return matId;
    }

    public Long getSchemaId() {
        return schemaId;
    }

    public Long getSceltaId() {
        return sceltaId;
    }

    public Integer getSceltaAdId() {
        return sceltaAdId;
    }

    public AttivitaDidatticaContestualizzata getChiaveADContestualizzata() {
        return chiaveADContestualizzata;
    }

    public Long getAdsceId() {
        return adsceId;
    }

    public Long getAdsceAttId() {
        return adsceAttId;
    }

    public Integer getSceltaFlg() {
        return sceltaFlg;
    }

    public Integer getTesorettoFlg() {
        return tesorettoFlg;
    }

    public Integer getRagId() {
        return ragId;
    }

    public Long getAdragoffId() {
        return adragoffId;
    }

    public String getTipoRagCod() {
        return tipoRagCod;
    }

    public String getAdLibCod() {
        return adLibCod;
    }

    public String getAdLibDes() {
        return adLibDes;
    }

    public Float getPeso() {
        return peso;
    }

    public Float getPesoAdVis() {
        return pesoAdVis;
    }

    public String getLinguaSceCod() {
        return linguaSceCod;
    }

    public Long getLinguaSceNum() {
        return linguaSceNum;
    }

    public List<UDPianoDiStudio> getUdSelezionate() {
        return udSelezionate;
    }

    public Integer getConteggiabileFlg() {
        return conteggiabileFlg;
    }

    public Integer getDeliberaFlg() {
        return deliberaFlg;
    }

    public Integer getStatoAttuazione() {
        return statoAttuazione;
    }

    public String getPartEffCod() {
        return partEffCod;
    }

    public String getLinguaDidId() {
        return linguaDidId;
    }

    public String getLinguaDidCod() {
        return linguaDidCod;
    }

    public String getLinguaDidDes() {
        return linguaDidDes;
    }

    public String getTipoDidCod() {
        return tipoDidCod;
    }

    public String getTipoDidDes() {
        return tipoDidDes;
    }

    public String getGrpAdlogCod() {
        return grpAdlogCod;
    }

    public String getGrpAdlogDes() {
        return grpAdlogDes;
    }

    public Integer getAnnoCorso() {
        return annoCorso;
    }

    public Integer getAnnoCorsoAnticipo() {
        return annoCorsoAnticipo;
    }

    @Override
    public String toString() {
        return "ADPianoDiStudio{" +
                "stuId=" + stuId +
                ", pianoId=" + pianoId +
                ", scePianoId=" + scePianoId +
                ", itmId=" + itmId +
                ", matId=" + matId +
                ", schemaId=" + schemaId +
                ", sceltaId=" + sceltaId +
                ", sceltaAdId=" + sceltaAdId +
                ", chiaveADContestualizzata=" + chiaveADContestualizzata +
                ", adsceId=" + adsceId +
                ", adsceAttId=" + adsceAttId +
                ", sceltaFlg=" + sceltaFlg +
                ", tesorettoFlg=" + tesorettoFlg +
                ", ragId=" + ragId +
                ", adragoffId=" + adragoffId +
                ", tipoRagCod='" + tipoRagCod + '\'' +
                ", adLibCod='" + adLibCod + '\'' +
                ", adLibDes='" + adLibDes + '\'' +
                ", peso=" + peso +
                ", pesoAdVis=" + pesoAdVis +
                ", linguaSceCod='" + linguaSceCod + '\'' +
                ", linguaSceNum=" + linguaSceNum +
                ", udSelezionate=" + udSelezionate +
                ", conteggiabileFlg=" + conteggiabileFlg +
                ", deliberaFlg=" + deliberaFlg +
                ", statoAttuazione=" + statoAttuazione +
                ", partEffCod='" + partEffCod + '\'' +
                ", linguaDidId='" + linguaDidId + '\'' +
                ", linguaDidCod='" + linguaDidCod + '\'' +
                ", linguaDidDes='" + linguaDidDes + '\'' +
                ", tipoDidCod='" + tipoDidCod + '\'' +
                ", tipoDidDes='" + tipoDidDes + '\'' +
                ", grpAdlogCod='" + grpAdlogCod + '\'' +
                ", grpAdlogDes='" + grpAdlogDes + '\'' +
                ", annoCorso=" + annoCorso +
                ", annoCorsoAnticipo=" + annoCorsoAnticipo +
                '}';
    }
}
