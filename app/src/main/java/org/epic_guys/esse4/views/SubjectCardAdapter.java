package org.epic_guys.esse4.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.epic_guys.esse4.R;

import java.util.List;

public class SubjectCardAdapter extends RecyclerView.Adapter<SubjectCardAdapter.MyViewHolder> {

    Context context;
    List<SubjectCardView> exams;

    public SubjectCardAdapter(Context context, List<SubjectCardView> exams) {
        this.context = context;
        this.exams = exams;
    }

    @NonNull
    @Override
    public SubjectCardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This is where you inflate the layout (giving a look to the card)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.subject_card_view_passed, parent, false);

        return new SubjectCardAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // This is where you set the data to the card
        holder.examNameView.setText(exams.get(position).getName());
        holder.yearView.setText(exams.get(position).getYear());
        holder.CFUView.setText(exams.get(position).getCFU());
        holder.gradeView.setText(exams.get(position).getGrade());
    }

    @Override
    public int getItemCount() {
        return exams.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView examNameView, yearView, CFUView, gradeView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            examNameView = itemView.findViewById(R.id.exam_name_passed);
            yearView = itemView.findViewById(R.id.year_data_passed);
            CFUView = itemView.findViewById(R.id.CFU_data_passed);
            gradeView = itemView.findViewById(R.id.grade_data_passed);
        }
    }
}
