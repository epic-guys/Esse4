package org.epic_guys.esse4.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.BookedAppello;

import java.util.List;

public class BookedCardAdapter extends RecyclerView.Adapter<BookedCardAdapter.MyViewHolder> {

    @NonNull
    private final List<BookedAppello> appelli;

    public BookedCardAdapter(@NonNull List<BookedAppello> appelli) {
        this.appelli = appelli;
    }

    @Override
    public int getItemViewType(int position) {
        return position % getItemCount();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.booked_card_view, parent, false);
        return new BookedCardAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookedCardAdapter.MyViewHolder holder, int position) {
        BookedAppello appello = appelli.get(position);
        holder.setContentView(appello);
    }

    @Override
    public int getItemCount() {
        return appelli.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

            private final TextView exam_name;
            private final TextView date_data;
            private final TextView cfu_data;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                exam_name = itemView.findViewById(R.id.exam_name);
                date_data = itemView.findViewById(R.id.date_data);
                cfu_data = itemView.findViewById(R.id.cfu_data);
            }

            public void setContentView(BookedAppello appello) {

                exam_name.setText(appello.getName());
                date_data.setText(String.format(" %s", appello.getReservationDate().substring(0,10)));
                cfu_data.setText(String.format(" %s", appello.getPeso()));
            }
    }
}