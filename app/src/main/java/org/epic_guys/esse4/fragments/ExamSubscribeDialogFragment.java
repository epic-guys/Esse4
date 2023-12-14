package org.epic_guys.esse4.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.Appello;

public class ExamSubscribeDialogFragment extends DialogFragment {

    private Appello appello;

    public ExamSubscribeDialogFragment(Appello appello) {
        super();
        this.appello = appello;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return new AlertDialog.Builder(requireContext())
                .setTitle(appello.getAdDes())
                .setMessage("Sei sicuro di volerti iscrivere all'appello?")
                .setPositiveButton("Iscriviti", (dialog, which) -> {
                    // TODO iscrizione
                    Toast.makeText(getContext(), "Test iscrizione", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Annulla", (dialog, which) -> {})
                .create();
    }


    public static String TAG = "ExamSubscribeDialogFragment";
}
