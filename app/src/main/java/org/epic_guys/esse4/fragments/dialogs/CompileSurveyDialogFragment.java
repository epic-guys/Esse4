package org.epic_guys.esse4.fragments.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.AppelloLibretto;

public class CompileSurveyDialogFragment extends DialogFragment {
    private final AppelloLibretto appello;

    public CompileSurveyDialogFragment(AppelloLibretto appello) {
        this.appello = appello;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle(R.string.survey_to_compile)
                .setMessage(R.string.survey_to_compile_message)
                .setPositiveButton(R.string.compile, (dialog, which) -> {

                })
                .create();
    }
}
