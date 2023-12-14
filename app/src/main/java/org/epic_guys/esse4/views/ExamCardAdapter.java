package org.epic_guys.esse4.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.Appello;

import java.util.List;

public class ExamCardAdapter extends RecyclerView.Adapter<ExamCardAdapter.MyViewHolder> {

    @NonNull
    private List<Appello> appelli;

    public ExamCardAdapter(@NonNull List<Appello> appelli) {
        this.appelli = appelli;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.exam_card_view, parent, false);
        return new ExamCardAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Appello appello = appelli.get(position);
        holder.setContentView(appello);
    }

    @Override
    public int getItemCount() {
        return appelli.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView date_data;
        private TextView host_data;
        private TextView cfu_data;
        private TextView sub_period_data;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            date_data = itemView.findViewById(R.id.date_data);
            host_data = itemView.findViewById(R.id.host_data);
            cfu_data = itemView.findViewById(R.id.cfu_data);
            sub_period_data = itemView.findViewById(R.id.sub_period_data);
        }

        public void setContentView(Appello appello) {
            date_data.setText(appello.getDataOraEsame().format(Appello.getDateTimeFormatter()));
            host_data.setText(appello.getPresidenteNomeCognome());
            sub_period_data.setText(appello.getDataInizioIscr() + " - " + appello.getDataFineIscr());
        }
    }
}