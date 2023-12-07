package org.epic_guys.esse4.views;

import android.content.Context;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import org.epic_guys.esse4.R;

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
    public SubjectCardView(Context context, long id ,String name, String year, String CFU, String grade){
        super(context);

        this.id = id;
        this.name = name;
        this.year = year;
        this.CFU = CFU;
        this.grade = grade;

        //if(grade == null)
            inflate(context, R.layout.subject_card_view, this);
        //else
        //inflate(context, R.layout.subject_card_view_passed, null);

        //((TextView)findViewById(R.id.text_name)).setText(name);
        //((TextView)findViewById(R.id.data_year)).setText(year);
        //((TextView)findViewById(R.id.CFU_data)).setText(CFU);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        TextView name = findViewById(R.id.text_name);
        TextView year = findViewById(R.id.data_year);
        TextView CFU = findViewById(R.id.CFU_data);

        name.setText(this.name);
        year.setText(this.year);
        CFU.setText(this.CFU);

        // Now the layout has been loaded, you can find your views and set the data
        //((TextView)findViewById(R.id.text_name)).setText(name);
        //((TextView)findViewById(R.id.data_year)).setText(year);
        //((TextView)findViewById(R.id.CFU_data)).setText(CFU);
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
