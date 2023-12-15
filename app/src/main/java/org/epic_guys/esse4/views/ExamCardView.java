package org.epic_guys.esse4.views;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

public class ExamCardView extends CardView {

    @NonNull
    private String name;

    @NonNull
    private String date;

    @NonNull
    private String start_sub;

    @NonNull
    private String end_sub;

    @NonNull
    private String CFU;

    @NonNull
    private String host;

    @Nullable
    private OnClickListener appelliClickListener;

    /**
     * Constructor for the ExamCardView class.
     *
     * @param context The context in which the ExamCardView is being used.
     * @param name The name of the exam.
     * @param date The date of the exam.
     * @param start_sub The start time of the exam.
     * @param end_sub The end time of the exam.
     * @param CFU The CFU of the exam.
     * @param host The host of the exam.
     * @param appelliClickListener The OnClickListener for the appelli button in this ExamCardView.
     */
    public ExamCardView(Context context, @NonNull String name, @NonNull String date, @NonNull String start_sub, @NonNull String end_sub, @NonNull String CFU,@NonNull String host, @Nullable OnClickListener appelliClickListener) {
        super(context); // Call the superclass constructor with the provided context
        this.name = name;
        this.date = date;
        this.start_sub = start_sub;
        this.end_sub = end_sub;
        this.CFU = CFU;
        this.host = host;
        this.appelliClickListener = appelliClickListener;
    }

    @Override
    public String toString() {
        return "ExamCardView{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", CFU='" + CFU + '\'' +
                ", host='" + host + '\'' +
                '}';
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    @NonNull
    public String getCFU() {
        return CFU;
    }

    @NonNull
    public String getHost() {
        return host;
    }

    @Nullable
    public OnClickListener getAppelliClickListener() {
        return appelliClickListener;
    }

}
