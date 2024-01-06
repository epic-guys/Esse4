package org.epic_guys.esse4.models.questionari;

import com.google.gson.annotations.SerializedName;

import org.epic_guys.esse4.models.UdLogPdsListWeb;

import java.util.List;

/**
 * UnitaDidatticaConQuestionario
 */
public class UnitaDidatticaConQuestionario {
    @SerializedName("adsceId")
    private Long adsceId = null;

    @SerializedName("stato")
    private StatoQuestionario stato = null;

    @SerializedName("questConfigId")
    private Integer questConfigId = null;

    @SerializedName("des")
    private String des = null;

    @SerializedName("questionarioDes")
    private String questionarioDes = null;

    @SerializedName("questionarioId")
    private Integer questionarioId = null;

    @SerializedName("anonimoFlg")
    private Integer anonimoFlg = null;

    @SerializedName("udLogPdsListWeb")
    private List<UdLogPdsListWeb> udLogPdsListWeb = null;
    /**
     * id dell&#39;attivita&#39; didattica di libretto
     * @return adsceId
     **/
    public Long getIdRigaLibretto() {
        return adsceId;
    }


    enum StatoQuestionario {
        DA_COMPILARE(0),
        COMPLETO(1);

        private final int value;

        StatoQuestionario(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    /**
     * 0 questionario da compilare. 1 questionario completo o non presente.
     * @return stato
     **/
    public StatoQuestionario getStato() {
        return stato;
    }

    /**
     * Get questConfigId
     * @return questConfigId
     **/
    public Integer getQuestConfigId() {
        return questConfigId;
    }

    /**
     * Get des
     * @return des
     **/
    public String getDes() {
        return des;
    }

    /**
     * Get questionarioDes
     * @return questionarioDes
     **/
    public String getQuestionarioDes() {
        return questionarioDes;
    }

    /**
     * Get questionarioId
     * @return questionarioId
     **/
    public Integer getQuestionarioId() {
        return questionarioId;
    }

    /**
     * Se il flag è a 1 non sarà salvato nessun legame tra il questionario e chi l&#39;ha compilato.
     * @return anonimoFlg
     **/
    public Integer getAnonimoFlg() {
        return anonimoFlg;
    }

    public boolean isAnonimo() {
        return anonimoFlg == 1;
    }

}

