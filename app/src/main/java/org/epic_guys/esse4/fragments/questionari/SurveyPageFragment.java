package org.epic_guys.esse4.fragments.questionari;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.epic_guys.esse4.API.API;
import org.epic_guys.esse4.API.services.QuestionariService;
import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.Appello;
import org.epic_guys.esse4.models.questionari.PaginaQuestionario;
import org.epic_guys.esse4.models.questionari.UnitaDidatticaConQuestionario;

import retrofit2.Call;

public class SurveyPageFragment extends Fragment {

    private PaginaQuestionario paginaQuestionario;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_survey_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
}
