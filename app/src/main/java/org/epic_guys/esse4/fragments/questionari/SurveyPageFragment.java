package org.epic_guys.esse4.fragments.questionari;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import org.epic_guys.esse4.API.API;
import org.epic_guys.esse4.API.services.QuestionariService;
import org.epic_guys.esse4.R;
import org.epic_guys.esse4.common.Common;
import org.epic_guys.esse4.exceptions.ApiException;
import org.epic_guys.esse4.models.questionari.Answer;
import org.epic_guys.esse4.models.questionari.Domande;
import org.epic_guys.esse4.models.questionari.PaginaQuestionario;
import org.epic_guys.esse4.models.questionari.Paragrafi;
import org.epic_guys.esse4.models.questionari.RisposteCompilate;
import org.epic_guys.esse4.models.questionari.RisposteDisponibili;
import org.epic_guys.esse4.models.questionari.TagsList;
import org.epic_guys.esse4.models.questionari.UnitaDidatticaConQuestionario;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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
    private long basePage;
    private QuestionariService questionariService;

    private Map<Integer, Answer> answers;


    public enum Status {
        IGNITION,
        PROGRESS,
        COMPLETING,
        ERROR
    }

    @NotNull
    public Status getStatus() {
        // Si sta iniziando il questionario quando non abbiamo ancora un idQuestionario
        // oppure quando siamo tornati alla prima pagina
        if (idQuestionario == -1 || (basePage == idPaginaDiProvenienza && !direction)) {
            return Status.IGNITION;
        }
        // Se la pagina di provenienza è -1 e stiamo andando avanti, siamo arrivati alla fine
        else if (direction && paginaQuestionario != null && paginaQuestionario.getPaginaId()  == -1) {
            Log.i("SurveyPageFragment", "COMPLETING");
            return Status.COMPLETING; //check if this is correct
        }
        // In tutti gli altri casi siamo in progress
        else {
            return Status.PROGRESS;
        }
        //TODO: check for status COMPLETING and ERROR
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_survey_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        SurveyPageFragmentArgs args = SurveyPageFragmentArgs.fromBundle(getArguments());
        Log.d("SurveyPageFragment", args.toString());
        idQuestionario = args.getIdQuestionario();
        idCompilazione = args.getCompId();
        direction = args.getDirection();
        idPaginaDiProvenienza = args.getPageId();
        idCompilazioneUtente = args.getUserCompId();
        idRigaLibretto = args.getIdRigaLibretto();
        idStudente = args.getStuId();
        questionariService = API.getService(QuestionariService.class);
        basePage = args.getBasePageId();
        answers = new HashMap<>();


        navController = NavHostFragment.findNavController(this);

        // Back esce dal questionario
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                onCancelButton(view);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), onBackPressedCallback);

        ImageButton cancelButton = view.findViewById(R.id.btn_cancel_survey);
        cancelButton.setOnClickListener(this::onCancelButton);

        Button positiveButton = view.findViewById(R.id.btn_next_page);
        positiveButton.setOnClickListener(this::onPositiveButton);

        Button negativeButton = view.findViewById(R.id.btn_prev_page);
        negativeButton.setOnClickListener(this::onNegativeButton);

        Common.startLoading(view.findViewById(R.id.survey_container), view, R.id.loading);

        switch (getStatus()) {
            case ERROR:
                emergencyAbort("Errore nella compilazione del questionario");
            case IGNITION:
                igniteSurvey().thenAccept(aVoid -> renderPage());
                positiveButton.setText("Inizia");
                break;

            case PROGRESS:
                fetchPage().thenAccept(aVoid -> renderPage());
                positiveButton.setText("Avanti");
                negativeButton.setText("Indietro");
                break;

            case COMPLETING:
                positiveButton.setText("Salva e termina");
                negativeButton.setText("Indietro");
                //end survey by confirming
                break;
        }


    }

    public void onPositiveButton(View view) {
        if(getStatus()!= Status.COMPLETING) {
            //here the code to save and setup next page
            saveAnswers()
                    .thenRun(() -> movePage(true))
                    .exceptionally(throwable -> {
                        Log.e("SurveyPageFragment", "Errore nel salvataggio delle risposte", throwable);

                        if(throwable.getCause() instanceof ApiException && ((ApiException) throwable.getCause()).getApiError().getStatusCode() == 422){
                            Toast.makeText(getContext(), "Rispondi alle domande obbligatorie (quelle con *)", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            emergencyAbort("Errore nel salvataggio delle risposte");
                        }
                        return null;
                    });
            //return;
        }

        //code for completing the survey and save everything

    }

    public void onNegativeButton(View view) {
        if(getStatus()!= Status.IGNITION) {
            //here the code to save and setup previous page
            saveAnswers().exceptionally(throwable -> {
                        Log.e("SurveyPageFragment", "Errore nel salvataggio delle risposte", throwable);
                        // emergencyAbort("Errore nel salvataggio delle risposte");
                        return null;
                    }).thenRun(() -> movePage(false)
                    );
            return;
        }
        onCancelButton(view);
    }

    private void renderPage(){
        Common.stopLoading(requireView().findViewById(R.id.survey_container), requireView(), R.id.loading);
        //render the page
        ViewGroup container = requireView().findViewById(R.id.survey_container);

        renderButtons();

        if(getStatus() == Status.COMPLETING){
            TextView textView = new TextView(getContext());
            textView.setText("Hai completato il questionario");
            container.addView(textView);
            return;
        }

        this.paginaQuestionario.getParagrafi().forEach(paragrafo -> {
            container.addView(renderParagrafo(paragrafo));
            //add a separator
            View separator = new View(getContext());
            container.addView(separator);
        });


        //DEBUG
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

        paragrafo.getDomande().forEach(domanda -> paragraph.addView(renderQuestion(domanda)));

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

    private void renderButtons(){
        View positiveButton = requireView().findViewById(R.id.btn_next_page);
        View negativeButton = requireView().findViewById(R.id.btn_prev_page);
        switch (getStatus()) {
            case ERROR:
                break;
            case COMPLETING:
            case PROGRESS:
                negativeButton.setVisibility(View.VISIBLE);
            case IGNITION:
                positiveButton.setVisibility(View.VISIBLE);
            break;
        }
    }

    private List<Integer> getRisposteCompilate(Domande domanda) {
        return domanda.getRispComplete().stream()
                .map(RisposteCompilate::getQuesitoId)
                .collect(Collectors.toList());
    }

    private View renderRadioQuestion(Domande domanda, boolean horizontal){
        Log.d("SurveyPageFragment", "renderRadioQuestion: " + domanda.toString());
        LinearLayout container = new LinearLayout(getContext());
        container.setOrientation(LinearLayout.VERTICAL);

        TextView domanda_view= new TextView(getContext());
        domanda_view.setText(domanda.getElementiDes());
        container.addView(domanda_view);

        RadioGroup radioGroup = new RadioGroup(getContext());

        // TOOD: range slider
        //if(horizontal){
        //    radioGroup.setOrientation(LinearLayout.HORIZONTAL);
        //}
        List<Integer> idRisposteCompletate = getRisposteCompilate(domanda);
        Log.i("SurveyPageFragment", "Risposte date: " + idRisposteCompletate.toString());
        for (RisposteDisponibili risposta : domanda.getRispDisponibili()) {
            RadioButton rad = new RadioButton(getContext());
            rad.setText(risposta.getElementiDes());
            radioGroup.addView(rad);

            Log.i("SurveyPageFragment", "Risposta corrente: " + risposta.getRispostaId().toString());

            Answer answer = new Answer()
                    .domandaId(domanda.getDomandaId())
                    .rispostaId(risposta.getRispostaId());
            rad.setOnClickListener(v -> answers.put(domanda.getDomandaId(), answer));

            if (idRisposteCompletate.contains(risposta.getRispostaId())) {
                Log.i("SurveyPageFragment", "Risposta già data");
                rad.setChecked(true);
                rad.callOnClick();
            }

        }

        container.addView(radioGroup);
        return container;
    }

    private View renderMultipleChoiceQuestion(Domande domanda, boolean horizontal){
        LinearLayout container = new LinearLayout(getContext());
        container.setOrientation(LinearLayout.VERTICAL);

        TextView domanda_view = new TextView(getContext());
        domanda_view.setText(domanda.getElementiDes());
        container.addView(domanda_view);

        LinearLayout checkboxGroup = new LinearLayout(getContext());
        //if (horizontal) {
        //    checkboxGroup.setOrientation(LinearLayout.HORIZONTAL);
        //}

        List<Integer> idRisposteCompletate = getRisposteCompilate(domanda);
        for (RisposteDisponibili risposta : domanda.getRispDisponibili()) {
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(risposta.getElementiDes());
            checkboxGroup.addView(checkBox);
            if (idRisposteCompletate.contains(risposta.getRispostaId())) {
                checkBox.setChecked(true);
            }

            checkBox.setOnCheckedChangeListener(((buttonView, isChecked) -> {
                throw new UnsupportedOperationException("I checkbox sono troppo difficili da gestire, non li implemento");
            }));

        }

        container.addView(checkboxGroup);

        return container;
    }

    private View renderFreeTextQuestion(Domande domanda){
        LinearLayout container = new LinearLayout(getContext());
        container.setOrientation(LinearLayout.VERTICAL);

        Log.d("SurveyPageFragment", "renderFreeTextQuestion: " + domanda.toString());

        TextView domanda_view = new TextView(getContext());
        domanda_view.setText(domanda.getElementiDes());
        container.addView(domanda_view);
        EditText risposta_view = new EditText(getContext());
        risposta_view.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        container.addView(risposta_view);
        if (domanda.getRispDisponibili().size() != 1) {
            throw new UnsupportedOperationException("Domanda a risposta libera con più di una risposta disponibile");
        }



        // Quando esce dal focus salvo la risposta
        risposta_view.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                Answer answer = new Answer();
                answer
                        .domandaId(domanda.getDomandaId())
                        .rispostaId(domanda.getRispDisponibili().get(0).getRispostaId())
                        .corpoRisposta(((EditText) v).getText().toString());
                answers.put(domanda.getDomandaId(), answer);
            }
        });

        // Se c'è una già una risposta, la salvo
        if (domanda.getRispComplete().size() > 0) {
            risposta_view.setText(domanda.getRispComplete().get(0).getTestoLibero());
            risposta_view.getOnFocusChangeListener().onFocusChange(risposta_view, false);
        }

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
        requireActivity().runOnUiThread(() -> new AlertDialog.Builder(requireContext())
                .setTitle("ATTENZIONE")
                .setMessage("Vuoi uscire dalla compilazione? \nPerderai i tuoi progressi.")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> requireActivity().getSupportFragmentManager().popBackStack())
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show());
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
            paginaCall = questionariService.getPrevPaginaQuestionario(
                    idStudente,
                    idRigaLibretto,
                    idQuestionario,
                    idCompilazione,
                    idPaginaDiProvenienza,
                    idCompilazioneUtente
            );
        }

        return API.enqueueResource(paginaCall).thenAccept(paginaQuestionario -> this.paginaQuestionario = paginaQuestionario);
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
        /*
        if (answers.isEmpty()) {
            return CompletableFuture.completedFuture(null);
        }
         */

        int pid = paginaQuestionario.getPaginaId() == null ? -1 : paginaQuestionario.getPaginaId();
        int gcid = paginaQuestionario.getQuestCompId() == null ? -1 : paginaQuestionario.getQuestCompId();

        if(getStatus() == Status.COMPLETING){
            /*Call<String> saveCall = questionariService.confermaQuestionario(
                    idStudente,
                    idQuestionario,
                    gcid,
                    pid,

                    QuestionariService.EventoCompilazione.EV_VAL_DID
            );
            API.enqueueResource(
                    saveCall
            )*/
            return CompletableFuture.completedFuture(null);
        }

        List<Answer> answers = new ArrayList<>(this.answers.values());
        Call<String> saveCall = questionariService.salvaPaginaQuestionario(
                idStudente,
                idQuestionario,
                gcid,
                pid,
                QuestionariService.EventoCompilazione.EV_VAL_DID,
                answers
        );
        return API.enqueueResource(saveCall).thenAccept(aVoid -> {});
    }

    private void movePage(boolean direction) {

        if(getStatus() == Status.IGNITION){
            basePage = paginaQuestionario.getPaginaId();
        }

        SurveyPageFragmentDirections.ActionSurveyPageFragmentSelf action =
                SurveyPageFragmentDirections.actionSurveyPageFragmentSelf(
                        paginaQuestionario.getPaginaId(),
                        paginaQuestionario.getQuestCompId(),
                        direction,
                        idRigaLibretto,
                        idQuestionario,
                        paginaQuestionario.getUserCompId(),
                        idStudente,
                        basePage
                );

        NavOptions options = new NavOptions.Builder()
                .setPopUpTo(R.id.appelliFragment, false)
                .build();
        navController.navigate(action, options);
    }
}
