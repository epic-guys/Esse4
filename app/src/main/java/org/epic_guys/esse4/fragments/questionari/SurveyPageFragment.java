package org.epic_guys.esse4.fragments.questionari;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.questionari.PaginaQuestionario;
import org.jetbrains.annotations.NotNull;

public class SurveyPageFragment extends Fragment {

    private PaginaQuestionario paginaQuestionario;
    private final long idQuestionario;
    private final long idCompilazione;
    private final boolean direction;
    private final long idPaginaDiProvenienza;
    private final long idCompilazioneUtente;
    private final long idRigaLibretto;
    private final long idStudente;

    public enum Status {
        STARTING,
        PROGRESS,
        COMPLETING
    }

    public SurveyPageFragment() {
        /*SurveyPageFragmentArgs args = SurveyPageFragmentArgs.fromBundle(getArguments());
        idQuestionario = args.getIdQuestionario();
        idCompilazione = args.getCompId();
        direction = args.getDirection();
        idPaginaDiProvenienza = args.getPageId();
        idCompilazioneUtente = args.getUserCompId();
        idRigaLibretto = args.getIdRigaLibretto();
        idStudente = args.getStuId();*/
        //fill with default values
        idQuestionario = 0;
        idCompilazione = 0;
        direction = false;
        idPaginaDiProvenienza = 0;
        idCompilazioneUtente = 0;
        idRigaLibretto = 0;
        idStudente = 0;
    }

    @NotNull
    public Status getStatus() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_survey_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //put a listener on the cancel button R.id.btn_cancel_survey

        //on click on the next button R.id.btn_cancel_survey
        ImageButton cancelButton = view.findViewById(R.id.btn_cancel_survey);
        cancelButton.setOnClickListener(v -> onCancelButton(v));

    }

    public void onPositiveButton(View view) {

    }

    public void onNegativeButton(View view) {

    }

    private void onCancelButton(View view) {
        requireActivity().runOnUiThread(() -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("ATTENZIONE")
                    .setMessage("Vuoi uscire dalla compilazione? \nPerderai i tuoi progressi.")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            requireActivity().getSupportFragmentManager().popBackStack();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        });
    }
}
