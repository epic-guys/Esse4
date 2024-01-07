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

import org.epic_guys.esse4.API.API;
import org.epic_guys.esse4.API.services.QuestionariService;
import org.epic_guys.esse4.R;
import org.epic_guys.esse4.fragments.dialogs.ExamSubscribeDialogFragment;
import org.epic_guys.esse4.models.Appello;
import org.epic_guys.esse4.models.AppelloLibretto;
import org.epic_guys.esse4.models.questionari.RigaLibrettoConStatoQuestionario;

import java.util.List;

import retrofit2.Call;

public class ExamCardAdapter extends RecyclerView.Adapter<ExamCardAdapter.MyViewHolder> {

    @NonNull
    private List<AppelloLibretto> appelli;

    public ExamCardAdapter(@NonNull List<AppelloLibretto> appelli) {
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
        AppelloLibretto appello = appelli.get(position);
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

        public void setContentView(final AppelloLibretto appello) {
            exam_name.setText(appello.getDescrizioneAttivitaDidattica());
            try{
                date_data.setText(appello.getDataOraEsame().format(Appello.getDateTimeFormatter()));
            }
            catch (Exception e){
                Log.e("ExamCardAdapter", "setContentView: ", e);
            }

            // FIXME - appello.getTipoEsame() può essere null
            // type_data.setText(appello.getTipoEsame().getDescription());

            host_data.setText(appello.getPresidenteNomeCognome());
            sub_period_data.setText(String.format(" %s - %s", appello.getDataInizioIscr(), appello.getDataFineIscr()));
/*
            LocalDate parseStartDate = Appello.getDateTimeFormatter().parse(appello.getDataInizioIscr(), LocalDate::from);
            LocalDate parseEndDate = Appello.getDateTimeFormatter().parse(appello.getDataFineIscr(), LocalDate::from);

            if (LocalDate.now().isBefore(parseStartDate) || LocalDate.now().isAfter(parseEndDate)) {
                subscribe_button.setEnabled(false);
            } */


            subscribe_button.setOnClickListener(v -> {
                QuestionariService questionariService = API.getService(QuestionariService.class);
                Call<RigaLibrettoConStatoQuestionario> statoQuestionarioCall = questionariService.getQuestionario(
                        appello.getIdCarriera(),
                        appello.getIdRigaLibretto()
                );

                createExamSubscribeDialog(appello);

                /*  API.enqueueResource(statoQuestionarioCall)
                        .thenAccept(rigaLibrettoConStatoQuestionario -> {
                            switch (rigaLibrettoConStatoQuestionario.getStatoQuestionario()) {
                                case COMPILATI:
                                    Log.d("ExamCardAdapter", "Tutti i questionari compilati");
                                    createExamSubscribeDialog(appello);
                                    break;

                                case DA_COMPILARE:
                                case ALCUNI_DA_COMPILARE:
                                    Log.d("ExamCardAdapter", "Questionario non compilabile");
                                    break;
                            }

                            Log.d("ExamCardAdapter", "Questionario non compilato");
                        })
                        .exceptionally(throwable -> {
                            Log.w("ExamCardAdapter", throwable.getMessage());
                            return null;
                        }); */
            });
        }

        public void createExamSubscribeDialog(AppelloLibretto appello) {
            new ExamSubscribeDialogFragment(appello).show(
                    // findFragment restituisce AppelliFragment, creiamo il dialog in questo fragment
                    FragmentManager.findFragment(itemView).getChildFragmentManager(),
                    ExamSubscribeDialogFragment.TAG
            );
        }
    }
}