package org.epic_guys.esse4.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.IscrizioneAppello;

import java.util.List;

public class BookedCardAdapter extends RecyclerView.Adapter<BookedCardAdapter.MyViewHolder> {

    @NonNull
    private List<IscrizioneAppello> appelli;

    public BookedCardAdapter(@NonNull List<IscrizioneAppello> appelli) {
        this.appelli = appelli;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.exam_card_view, parent, false);
        return new BookedCardAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookedCardAdapter.MyViewHolder holder, int position) {
        IscrizioneAppello appello = appelli.get(position);
        holder.setContentView(appello);
    }

    @Override
    public int getItemCount() {
        return appelli.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView exam_name;
            private TextView date_data;
            private TextView host_data;
            private TextView type_data;
            private TextView sub_period_data;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                exam_name = itemView.findViewById(R.id.exam_name);
                date_data = itemView.findViewById(R.id.date_data);
                host_data = itemView.findViewById(R.id.host_data);
                type_data = itemView.findViewById(R.id.type_data);
                sub_period_data = itemView.findViewById(R.id.sub_period_data);
            }

            public void setContentView(IscrizioneAppello appello) {
                /*exam_name.setText(appello.getNome());
                date_data.setText(appello.getData());
                host_data.setText(appello.getAula());
                type_data.setText(appello.getTipo());
                sub_period_data.setText(appello.getPeriodo());*/
            }
    }
}