package org.epic_guys.esse4.views;

import android.content.Context;

import androidx.cardview.widget.CardView;


public class SubjectCardView extends CardView {
    //Someone at ess3 decided to use a long for the id of the subject, so, for them, a carrier should have 2^63 subjects.
    private long id;
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
    public SubjectCardView(Context context, long id ,String name, String year, String CFU, String grade) {
        super(context);
        this.id = id;
        this.name = name;
        this.year = year;
        this.CFU = CFU;
        this.grade = grade;
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

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getCFU() {
        return CFU;
    }

    public String getGrade() {
        return grade;
    }

    public void setCardId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setCFU(String CFU) {
        this.CFU = CFU;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
