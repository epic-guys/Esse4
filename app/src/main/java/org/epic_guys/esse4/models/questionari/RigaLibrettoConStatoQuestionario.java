package org.epic_guys.esse4.models.questionari;

import com.google.gson.annotations.SerializedName;

import org.epic_guys.esse4.models.RigaLibretto;

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
    * Stato dei questionari di valutazione, può assumere i seguenti valori:
    * <ul>
    *     <li>NON_PRESENTI: Questionari non presenti</li>
    *     <li>COMPILATI: Questionari compilati</li>
    *     <li>ALCUNI_DA_COMPILARE: Alcuni questionari da compilare</li>
    *     <li>DA_COMPILARE: Questionari da compilare</li>
    *     <li>ERRORE: Errore nella configurazione, sono presenti più questionari con stato A</li>
    * </ul>
    * @return statoLink
    **/
    public StatoQuestionario getStatoQuestionario() {
        return statoLink;
    }
}

