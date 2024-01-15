package org.epic_guys.esse4.fragments.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import org.epic_guys.esse4.R;
import org.epic_guys.esse4.fragments.AppelliFragment;
import org.epic_guys.esse4.models.Appello;
import org.epic_guys.esse4.models.AppelloLibretto;
import org.epic_guys.esse4.models.ParametriIscrizioneAppello;

public class ExamSubscribeDialogFragment extends DialogFragment {

    private final AppelloLibretto appello;

    public interface ExamSubscribeDialogListener {
        void onSubscribe(
                AppelloLibretto appelloLibretto,
                ParametriIscrizioneAppello parametri
        );
    }

    private ExamSubscribeDialogListener listener;

    public ExamSubscribeDialogFragment(AppelloLibretto appello) {
        super();
        this.appello = appello;

        this.setCancelable(false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ExamSubscribeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    context.toString() + " must implement ExamSubscribeDialogListener"
            );
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = requireActivity().getLayoutInflater().inflate(
                R.layout.fragment_exam_subscribe_dialog,
                null
        );

        Spinner examType = view.findViewById(R.id.spinner_exam_type);
        EditText examNotes = view.findViewById(R.id.edit_text_notes_for_professor);
        examType.setAdapter(
                new ArrayAdapter<>(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        Appello.TipoIscrCodEnum.values()
                )
        );

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(appello.getDescrizioneAttivitaDidattica());
        builder.setMessage(appello.getDescrizioneAppello());
        builder.setView(view);
        builder.setPositiveButton(R.string.subscribe, (dialog, which) -> {

            ParametriIscrizioneAppello parametri = new ParametriIscrizioneAppello(
                    appello.getIdRigaLibretto()
            );
            // Tipo esame DA IMPLEMENTARE
            // parametri.setTipoIscrStu((Appello.TipoIscrCodEnum) examType.getSelectedItem());
            // Note per docente
            // if (!examNotes.getText().toString().isEmpty()) {
            //     parametri.setNotaStu(examNotes.getText().toString());
            // }

            listener.onSubscribe(appello, parametri);
        });


        builder.setNegativeButton(R.string.cancel, (dialog, which) -> {
        });
        return builder
                .create();
    }

    public static String TAG = "ExamSubscribeDialogFragment";
}
