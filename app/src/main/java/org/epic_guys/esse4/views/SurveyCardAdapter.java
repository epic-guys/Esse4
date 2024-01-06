package org.epic_guys.esse4.views;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SurveyCardAdapter extends RecyclerView.Adapter<SurveyCardAdapter.SurveyCardViewHolder> {

    @NonNull
    @Override
    public SurveyCardAdapter.SurveyCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyCardAdapter.SurveyCardViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class SurveyCardViewHolder extends RecyclerView.ViewHolder {

        public SurveyCardViewHolder(@NonNull ViewGroup parent) {
            super(parent);
        }
    }
}
