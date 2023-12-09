package org.epic_guys.esse4.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        view = inflater.inflate(R.layout.subject_card_view, parent, false);
        return new SubjectCardAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.examNameView.setText(exams.get(position).getName());
        holder.yearView.setText(exams.get(position).getYear());
        holder.CFUView.setText(exams.get(position).getCFU());

        if(exams.get(position).getGrade() != null) {
            holder.passedLayout.setVisibility(View.VISIBLE);
            holder.notPassedLayout.setVisibility(View.GONE);

            holder.examNameView_passed.setText(exams.get(position).getName());
            holder.yearView_passed.setText(exams.get(position).getYear());
            holder.CFUView_passed.setText(exams.get(position).getCFU());
            holder.gradeView_passed.setText(exams.get(position).getGrade());
        } else {
            holder.passedLayout.setVisibility(View.GONE);
            holder.notPassedLayout.setVisibility(View.VISIBLE);

            holder.examNameView.setText(exams.get(position).getName());
            holder.yearView.setText(exams.get(position).getYear());
            holder.CFUView.setText(exams.get(position).getCFU());
        }

    }

    @Override
    public int getItemCount() {
        return exams.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout passedLayout, notPassedLayout;
        TextView examNameView, yearView, CFUView;
        TextView examNameView_passed, yearView_passed, CFUView_passed, gradeView_passed;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            passedLayout = itemView.findViewById(R.id.passed);
            notPassedLayout = itemView.findViewById(R.id.not_passed);

            examNameView = itemView.findViewById(R.id.exam_name);
            yearView = itemView.findViewById(R.id.year_data) ;
            CFUView = itemView.findViewById(R.id.CFU_data);

            examNameView_passed = itemView.findViewById(R.id.exam_name_passed);
            yearView_passed = itemView.findViewById(R.id.year_data_passed) ;
            CFUView_passed = itemView.findViewById(R.id.CFU_data_passed);
            gradeView_passed = itemView.findViewById(R.id.grade_data_passed);
        }
    }
}
