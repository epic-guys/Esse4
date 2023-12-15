package org.epic_guys.esse4.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.epic_guys.esse4.API.API;
import org.epic_guys.esse4.API.services.CalendarioEsamiService;
import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.Appello;
import org.epic_guys.esse4.models.AppelloLibretto;
import org.epic_guys.esse4.models.ParametriIscrizioneAppello;

import java.util.concurrent.CompletableFuture;

import retrofit2.Call;

public class ExamSubscribeDialogFragment extends DialogFragment {

    private final AppelloLibretto appello;

    public ExamSubscribeDialogFragment(AppelloLibretto appello) {
        super();
        this.appello = appello;

        this.setCancelable(false);
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

        return new AlertDialog.Builder(requireContext())
                .setTitle(appello.getDescrizioneAttivitaDidattica())
                .setMessage(appello.getDescrizioneAppello())
                .setView(view)
                .setPositiveButton(R.string.subscribe, (dialog, which) -> {
                    Log.d(TAG, appello.getIdRigaLibretto().toString());
                    ParametriIscrizioneAppello parametri = new ParametriIscrizioneAppello(
                        appello.getIdRigaLibretto()
                    );
                    // Tipo esame DA IMPLEMENTARE
                    // parametri.setTipoIscrStu((Appello.TipoIscrCodEnum) examType.getSelectedItem());
                    // Note per docente
                    // if (!examNotes.getText().toString().isEmpty()) {
                    //     parametri.setNotaStu(examNotes.getText().toString());
                    // }

                    subscribe(parametri)
                            .thenAccept(aVoid -> {
                                Log.d(TAG, "Iscrizione effettuata");
                                requireActivity().runOnUiThread(() -> {
                                    Toast.makeText(getContext(), "Iscrizione effettuata", Toast.LENGTH_SHORT).show();
                                });
                            })
                            .exceptionally(throwable -> {
                                Log.w(TAG, Log.getStackTraceString(throwable));
                                requireActivity().runOnUiThread(() -> {
                                    Toast.makeText(getContext(), "Errore durante l'iscrizione", Toast.LENGTH_SHORT).show();
                                });
                                return null;
                            });
                })
                .setNegativeButton(R.string.cancel, (dialog, which) -> {})
                .create();
    }


    public CompletableFuture<Void> subscribe(ParametriIscrizioneAppello parametriIscrizioneAppello) {
        CalendarioEsamiService service = API.getService(CalendarioEsamiService.class);
        Call<Void> call = service.postIscrizioneAppello(
                appello.getCdsId(),
                appello.getAdId(),
                appello.getAppelloId(),
                parametriIscrizioneAppello
        );

        Log.d(TAG, "Iscrizione appello: " + appello.getCodiceAttivitaDidattica());
        return API.enqueueResource(call);
    }


    public static String TAG = "ExamSubscribeDialogFragment";
}
