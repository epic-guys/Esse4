package org.epic_guys.esse4.fragments.questionari;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.questionari.PaginaQuestionario;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;

public class SurveyPageFragment extends Fragment {

    private NavController navController;

    private PaginaQuestionario paginaQuestionario;
    private long idQuestionario;
    private long idCompilazione;
    private boolean direction;
    private long idPaginaDiProvenienza;
    private long idCompilazioneUtente;
    private long idRigaLibretto;
    private long idStudente;
    private QuestionariService questionariService;

    private Map<Integer, Answer> answers;


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
        if (idQuestionario == -1) {
            return Status.IGNITION;
        } else {
            return Status.PROGRESS;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_survey_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        SurveyPageFragmentArgs args = SurveyPageFragmentArgs.fromBundle(getArguments());
        idQuestionario = args.getIdQuestionario();
        idCompilazione = args.getCompId();
        direction = args.getDirection();
        idPaginaDiProvenienza = args.getPageId();
        idCompilazioneUtente = args.getUserCompId();
        idRigaLibretto = args.getIdRigaLibretto();
        idStudente = args.getStuId();
        questionariService = API.getService(QuestionariService.class);
        answers = new HashMap<>();

        navController = NavHostFragment.findNavController(this);
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                onCancelButton(view);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), onBackPressedCallback);

        ImageButton cancelButton = view.findViewById(R.id.btn_cancel_survey);
        cancelButton.setOnClickListener(v -> onCancelButton(v));

    }

    public void onPositiveButton(View view) {

    }

    public void onNegativeButton(View view) {

    }

    private void renderPage(){
        //render the page
        ViewGroup container = requireView().findViewById(R.id.survey_container);


        this.paginaQuestionario.getParagrafi().forEach(paragrafo -> {
            container.addView(renderParagrafo(paragrafo));
        });

        //place a TextView inside the container dynamically
        TextView textView = new TextView(getContext());
        textView.setText(this.paginaQuestionario.toString());
        container.addView(textView);
    }

    private View renderParagrafo(Paragrafi paragrafo) {
        LinearLayout paragraph = new LinearLayout(getContext());
        paragraph.setOrientation(LinearLayout.VERTICAL);

        TextView paragrafo_view = new TextView(getContext());
        paragrafo_view.setText(paragrafo.getElementiDes());
        paragraph.addView(paragrafo_view);

        paragrafo.getDomande().forEach(domanda -> {
            paragraph.addView(renderQuestion(domanda));
        });

        return paragraph;
    }

    private View renderQuestion(Domande domanda) {
        switch (domanda.getTipoFormatoCod()) {
            case TL_DOM_DFS:
                return renderRadioQuestion(domanda, false);
            case TL_DOM_DFM:
                return renderMultipleChoiceQuestion(domanda, false);
            case TL_DOM_OFS:
                return renderHorizontalRadioQuestion(domanda);
            case TL_DOM_OFM:
                return renderHorizontalMultipleChoiceQuestion(domanda);
            case TL_DOM_LIB:
                return renderFreeTextQuestion(domanda);
            case TL_DOM_VAR:
                emergencyAbort("Domanda di tipo TL_DOM_VAR non supportata per ora");
                return null;
            default:
                throw new RuntimeException("Tipo domanda non supportato, try this tutorial: https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        }
    }

    private View renderRadioQuestion(Domande domanda, boolean horizontal){
        LinearLayout container = new LinearLayout(getContext());

        if(!horizontal){
            container.setOrientation(LinearLayout.VERTICAL);
        }

        TextView domanda_view= new TextView(getContext());
        domanda_view.setText(domanda.getElementiDes());
        container.addView(domanda_view);

        RadioGroup radioGroup = new RadioGroup(getContext());
        for (RisposteDisponibili risposta : domanda.getRispDisponibili()) {
            RadioButton rad = new RadioButton(getContext());
            rad.setText(risposta.getElementiDes());
            Answer answer = new Answer()
                    .domandaId(domanda.getDomandaId())
                    .rispostaId(risposta.getRispostaId());
            rad.setOnClickListener(v -> {
                answers.put(domanda.getDomandaId(), answer);
            });

            radioGroup.addView(rad);
        }

        container.addView(radioGroup);
        return container;
    }

    private View renderMultipleChoiceQuestion(Domande domanda, boolean horizontal){
        LinearLayout container = new LinearLayout(getContext());

        if(!horizontal){
            container.setOrientation(LinearLayout.VERTICAL);
        }

        TextView domanda_view = new TextView(getContext());
        domanda_view.setText(domanda.getElementiDes());
        container.addView(domanda_view);

        for (int i = 0; i < domanda.getRispDisponibili().size(); i++) {
            RisposteDisponibili risposta = domanda.getRispDisponibili().get(i);
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(risposta.getElementiDes());
            container.addView(checkBox);
        }

        return container;
    }

    private View renderFreeTextQuestion(Domande domanda){
        LinearLayout container = new LinearLayout(getContext());
        container.setOrientation(LinearLayout.VERTICAL);

        TextView domanda_view = new TextView(getContext());
        domanda_view.setText(domanda.getElementiDes());
        container.addView(domanda_view);

        TextView risposta_view = new TextView(getContext());
        risposta_view.setText("Risposta");
        container.addView(risposta_view);

        return container;
    }

    private View renderHorizontalRadioQuestion(Domande domanda){
        return renderRadioQuestion(domanda, true);
    }

    private View renderHorizontalMultipleChoiceQuestion(Domande domanda){
        return renderMultipleChoiceQuestion(domanda, true);
    }

    private CompletableFuture<Void> igniteSurvey() {
        CompletableFuture<Void> res = new CompletableFuture<>();
        getQuestionario().thenAccept(
                questionario -> {
                    idQuestionario = questionario.getQuestionarioId();
                    iniziaQuestionario(questionario).thenAccept(paginaQuestionario -> {
                        this.paginaQuestionario = paginaQuestionario;
                        res.complete(null);
                    }).exceptionally(throwable -> {
                        Log.e("SurveyPageFragment", "Errore nell'inizio del questionario", throwable);
                        emergencyAbort("Errore nell'inizio del questionario");
                        return null;
                    });
                }
        ).exceptionally(throwable -> {
            Toast.makeText(getContext(), "Errore nel caricamento del questionario", Toast.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStack();
            return null;
        });
        return res;
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

    private void emergencyAbort(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    private CompletableFuture<UnitaDidatticaConQuestionario> getQuestionario() {
        Call<UnitaDidatticaConQuestionario> questionarioCall = questionariService.getUnitaDidatticaConQuestionario(
                idRigaLibretto,
                QuestionariService.EventoCompilazione.EV_VAL_DID
        );
        return API.enqueueResource(questionarioCall);
    }

    private CompletableFuture<Void> fetchPage() {
        Call<PaginaQuestionario> paginaCall;
        // Forward direction
        if (direction) {
            paginaCall = questionariService.getNextPaginaQuestionario(
                    idStudente,
                    idRigaLibretto,
                    idQuestionario,
                    idCompilazione,
                    idPaginaDiProvenienza,
                    idCompilazioneUtente
            );
        }
        // Backward direction
        else {
            // TODO
            throw new UnsupportedOperationException();
        }

        return API.enqueueResource(paginaCall).thenAccept(paginaQuestionario -> {
            this.paginaQuestionario = paginaQuestionario;
        });
    }

    private CompletableFuture<PaginaQuestionario> iniziaQuestionario(UnitaDidatticaConQuestionario questionario) {
        Call<PaginaQuestionario> paginaCall = questionariService.iniziaQuestionario(
                API.getCarriera().getIdStudente(),
                idRigaLibretto,
                questionario.getQuestionarioId(),
                QuestionariService.EventoCompilazione.EV_VAL_DID,
                questionario.getQuestConfigId(),
                idCompilazioneUtente,
                new TagsList().tags(
                    questionario.getUdLogPdsListWeb().get(0).getTagsValdid()
                )
        );

        return API.enqueueResource(paginaCall);
    }

    private CompletableFuture<Void> saveAnswers() {
        if (answers.isEmpty()) {
            return CompletableFuture.completedFuture(null);
        }

        List<Answer> answers = new ArrayList<>(this.answers.values());
        Call<String> saveCall = questionariService.salvaPaginaQuestionario(
                idStudente,
                idQuestionario,
                paginaQuestionario.getQuestCompId(),
                paginaQuestionario.getPaginaId(),
                idCompilazioneUtente,
                answers
        );
        return API.enqueueResource(saveCall).thenAccept(aVoid -> {});
    }

    private void movePage(boolean direction) {
        SurveyPageFragmentDirections.ActionSurveyPageFragmentSelf action =
                SurveyPageFragmentDirections.actionSurveyPageFragmentSelf(
                        paginaQuestionario.getPaginaId(),
                        paginaQuestionario.getQuestCompId(),
                        direction,
                        idRigaLibretto,
                        idQuestionario,
                        paginaQuestionario.getUserCompId(),
                        idStudente
                );

        NavOptions options = new NavOptions.Builder()
                .setPopUpTo(R.id.appelliFragment, false)
                .build();
        navController.navigate(action, options);
    }
}
