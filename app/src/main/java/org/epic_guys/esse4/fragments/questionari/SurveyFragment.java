package org.epic_guys.esse4.fragments.questionari;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.epic_guys.esse4.API.API;
import org.epic_guys.esse4.API.services.QuestionariService;
import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.questionari.PaginaQuestionario;
import org.epic_guys.esse4.models.questionari.TagsList;
import org.epic_guys.esse4.models.questionari.UnitaDidatticaConQuestionario;

import retrofit2.Call;

public class SurveyFragment extends Fragment {

    private UnitaDidatticaConQuestionario questionario;
    private QuestionariService questionariService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_survey, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        long idRigaLibretto = SurveyFragmentArgs.fromBundle(getArguments()).getIdRigaLibretto();
        Button compileSurveyBtn = view.findViewById(R.id.btn_survey_compile);
        questionariService = API.getService(QuestionariService.class);

        compileSurveyBtn.setOnClickListener(clickView -> createSurvey());

        Call<UnitaDidatticaConQuestionario> questionarioCall = questionariService.getUnitaDidatticaConQuestionario(
                idRigaLibretto,
                QuestionariService.EventoCompilazione.EV_VAL_DID
        );

        API.enqueueResource(questionarioCall)
                .thenAccept(questionario -> {
                    this.questionario = questionario;
                    compileSurveyBtn.setEnabled(true);
                });

    }

    private void createSurvey() {
        /*
        Call<PaginaQuestionario> paginaCall = questionariService.iniziaQuestionario(
                API.getCarriera().getIdCarriera(),
                questionario.getIdRigaLibretto(),
                questionario.getQuestionarioId(),
                QuestionariService.EventoCompilazione.EV_VAL_DID,
                questionario.getQuestConfigId(),
        )
         */
    }
}