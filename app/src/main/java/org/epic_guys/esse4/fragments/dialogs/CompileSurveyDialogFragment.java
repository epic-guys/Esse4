package org.epic_guys.esse4.fragments.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.AppelloLibretto;

public class CompileSurveyDialogFragment extends DialogFragment {
    private final AppelloLibretto appello;
    private NavController navController;

    public CompileSurveyDialogFragment(AppelloLibretto appello) {
        this.appello = appello;
        navController = NavHostFragment.findNavController(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle(R.string.survey_to_compile)
                .setMessage(R.string.survey_to_compile_message)
                .setPositiveButton(R.string.compile, (dialog, which) -> {
                    // Navigate to the survey fragment
                    // Least verbose Java code:
                    CompileSurveyDialogFragmentDirections.ActionCompileSurveyDialogFragmentToSurveyFragment action =
                            CompileSurveyDialogFragmentDirections.actionCompileSurveyDialogFragmentToSurveyFragment(appello.getIdRigaLibretto());
                    navController.navigate(action);
                })
                .create();
    }
}
