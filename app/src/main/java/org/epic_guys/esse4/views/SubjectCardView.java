package org.epic_guys.esse4.views;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.epic_guys.esse4.R;
import org.epic_guys.esse4.common.Common;
import org.epic_guys.esse4.models.RigaLibretto;

public class SubjectCardView extends View {

    private long id;
    @NonNull
    private String name;
    @NonNull
    private String year;
    @NonNull
    private String CFU;
    @Nullable
    private String grade;

    @Nullable
    private OnClickListener appelliClickListener;

    /**
     * Constructor
     * @param context The context of the application
     * @param id The id of the subject
     * @param name The name of the subject
     * @param year The year of the subject
     * @param CFU The CFU of the subject
     * @param grade The grade of the subject (if null, the subject is not passed)
     */
    public SubjectCardView(Context context, long id ,String name, String year, String CFU, String grade, @Nullable OnClickListener appelliClickListener){
        super(context);
        this.id = id;
        this.name = name;
        this.year = year;
        this.CFU = CFU;
        this.grade = grade;
        this.appelliClickListener = appelliClickListener;
    }

    public SubjectCardView(Context context, @NonNull RigaLibretto rigaLibretto, @Nullable OnClickListener appelliClickListener) {
        super(context); // Call the superclass constructor with the provided context

        this.id = rigaLibretto.getIdRigaLibretto();
        this.name = rigaLibretto.getCodiceAttivitaDidattica() + " - " + rigaLibretto.getDescrizioneAttivitaDidattica();
        this.year = rigaLibretto.getAnnoCorso().toString();
        this.CFU = String.valueOf(rigaLibretto.getPeso().intValue());
        this.grade = Common.stringifyGrade(rigaLibretto);
        this.appelliClickListener = appelliClickListener; // Set the OnClickListener for the appelli button in this SubjectCardView
    }

    @Override
    public String toString() {
        return "SubjectCardView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", CFU='" + CFU + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

    public long getCardId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getYear() {
        return year;
    }

    @NonNull
    public String getCFU() {
        return CFU;
    }

    @Nullable
    public String getGrade() {
        return grade;
    }

    @Nullable
    public OnClickListener getAppelliClickListener() {
        return appelliClickListener;
    }
}