package org.epic_guys.esse4.views;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import org.epic_guys.esse4.R;
import org.epic_guys.esse4.fragments.ExamSubscribeDialogFragment;
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

        private TextView exam_name;
        private TextView date_data;
        private TextView host_data;
        private TextView type_data;
        private TextView sub_period_data;
        private Button subscribe_button;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            exam_name = itemView.findViewById(R.id.exam_name);
            date_data = itemView.findViewById(R.id.date_data);
            host_data = itemView.findViewById(R.id.host_data);
            type_data = itemView.findViewById(R.id.type_data);
            sub_period_data = itemView.findViewById(R.id.sub_period_data);
            subscribe_button = itemView.findViewById(R.id.btn_subscribe);
        }

        public void setContentView(Appello appello) {
            exam_name.setText(appello.getDescrizioneAttivitaDidattica());
            try{
                date_data.setText(appello.getDataOraEsame().format(Appello.getDateTimeFormatter()));
            }
            catch (Exception e){
                Log.e("ExamCardAdapter", "setContentView: ", e);
            }

            type_data.setText(appello.getTipoEsame().getDescription());

            host_data.setText(appello.getPresidenteNomeCognome());
            sub_period_data.setText(appello.getDataInizioIscr() + " - " + appello.getDataFineIscr());


            subscribe_button.setOnClickListener(v -> new ExamSubscribeDialogFragment(appello).show(
                    // findFragment restituisce AppelliFragment, creiamo il dialog in questo fragment
                    FragmentManager.findFragment(itemView).getChildFragmentManager(),
                    ExamSubscribeDialogFragment.TAG
            ));
        }
    }
}