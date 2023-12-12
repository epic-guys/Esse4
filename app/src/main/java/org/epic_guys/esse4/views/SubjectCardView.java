package org.epic_guys.esse4.views;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import org.epic_guys.esse4.common.Common;
import org.epic_guys.esse4.models.RigaLibretto;


public class SubjectCardView extends CardView {

    @NonNull
    private RigaLibretto rigaLibretto;

    @Nullable
    private OnClickListener appelliClickListener;


/**
 * Constructor for the SubjectCardView class.
 *
 * @param context The context in which the SubjectCardView is being used.
 * @param rigaLibretto The RigaLibretto object associated with this SubjectCardView.
 * @param appelliClickListener The OnClickListener for the appelli button in this SubjectCardView.
 */
public SubjectCardView(Context context, @NonNull RigaLibretto rigaLibretto, @Nullable OnClickListener appelliClickListener) {
    super(context); // Call the superclass constructor with the provided context
    this.rigaLibretto = rigaLibretto; // Set the RigaLibretto object for this SubjectCardView
    this.appelliClickListener = appelliClickListener; // Set the OnClickListener for the appelli button in this SubjectCardView
}

    @Override
    public String toString() {
        return "SubjectCardView{" +
                "id=" + rigaLibretto.getIdRigaLibretto() +
                ", name='" + rigaLibretto.getCodiceAttivitaDidattica() + '\'' +
                ", year='" + rigaLibretto.getAnnoCorso() + '\'' +
                ", CFU='" + rigaLibretto.getPeso() + '\'' +
                ", grade='" + rigaLibretto.getEsito().getVoto() + '\'' +
                '}';
    }

    @NonNull
    public RigaLibretto getRigaLibretto() {
        return rigaLibretto;
    }

    @Nullable
    public OnClickListener getAppelliClickListener() {
        return appelliClickListener;
    }

    public String getName() {
        return rigaLibretto.getCodiceAttivitaDidattica() + " - " + rigaLibretto.getDescrizioneAttivitaDidattica();
    }

    public String getYear() {
        return rigaLibretto.getAnnoCorso().toString();
    }

    public String getCFU() {
        return String.valueOf(rigaLibretto.getPeso().intValue());
    }

    public String getGrade() {
        return Common.stringifyGrade(rigaLibretto);
    }
}
