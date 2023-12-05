package org.epic_guys.esse4.views;

import android.content.Context;
import android.view.View;

import org.epic_guys.esse4.R;

public class SubjectCardView extends View {
    private int id;
    private String name;
    private String year;
    private String CFU;
    private String grade;

    /**
     * Constructor
     * @param context The context of the application
     * @param id The id of the subject
     * @param name The name of the subject
     * @param year The year of the subject
     * @param CFU The CFU of the subject
     * @param grade The grade of the subject (if null, the subject is not passed)
     */
    public SubjectCardView(Context context, int id ,String name, String year, String CFU, String grade){
        super(context);
        this.id = id;
        this.name = name;
        this.year = year;
        this.CFU = CFU;
        this.grade = grade;

        if(grade == null)
            inflate(context, R.layout.subject_card_view, null);
        //else
            //inflate(context, R.layout.subject_card_view_passed, null);
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
}
