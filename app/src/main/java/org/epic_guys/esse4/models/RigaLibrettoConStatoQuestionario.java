package org.epic_guys.esse4.models;

import com.google.gson.annotations.SerializedName;

/**
 * RigaLibrettoConStatoQuestionario
 */
public class RigaLibrettoConStatoQuestionario extends RigaLibretto {

    public enum StatoQuestionario {
        NON_PRESENTI(0),
        COMPILATI(1),
        ALCUNI_DA_COMPILARE(2),
        DA_COMPILARE(3),
        ERRORE(4);

        private final int value;

        StatoQuestionario(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    @SerializedName("statoLink")
    private StatoQuestionario statoLink = null;

    /**
     * __Stato dei questionari di valutazione, può assumere i seguenti valori__  0. Questionari non presenti  1. Questionari compilati  2. Alcuni questionari da compilare  3. Questionari da compilare  4. Errore nella configurazione, sono presenti più questionari con stato A
     * @return statoLink
     **/
    public StatoQuestionario getStatoLink() {
        return statoLink;
    }
}

