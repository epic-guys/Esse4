package org.epic_guys.esse4.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.epic_guys.esse4.API.API;
import org.epic_guys.esse4.API.services.PianiService;
import org.epic_guys.esse4.R;
import org.epic_guys.esse4.common.Common;
import org.epic_guys.esse4.models.ADPianoDiStudio;
import org.epic_guys.esse4.models.PianoDiStudio;
import org.epic_guys.esse4.models.TestataPianoDiStudio;
import org.epic_guys.esse4.views.SubjectCardAdapter;
import org.epic_guys.esse4.views.SubjectCardView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class StudyPlanFragment extends Fragment {
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navController = NavHostFragment.findNavController(this);



    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_study_plan, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<SubjectCardView> exams = new ArrayList<>();

        long idStudente = API.getCarriera().getIdStudente();

        Common.startLoading(requireView().findViewById(R.id.exams),requireView(),R.id.loading);

        PianiService pianiService = API.getService(PianiService.class);
        Call<List<TestataPianoDiStudio>> testataPianoDiStudio = pianiService.testataPianoDiStudio(idStudente);
        API.enqueueResource(testataPianoDiStudio).thenCompose(testate -> {
            TestataPianoDiStudio testata = testate.get(0);
            Call<PianoDiStudio> righePianoDiStudio = pianiService.righePianoDiStudio(idStudente, testata.getPianoId());
            return API.enqueueResource(righePianoDiStudio);
        }).thenAccept(righe -> {
            for (ADPianoDiStudio riga : righe.getAttivita()) {
                try {
                    Integer scePianoId = riga.getScePianoId();
                    if(scePianoId == 6 || scePianoId == 7 || scePianoId == 8) {
                        exams.add(new SubjectCardView(this.getContext(), 0L, riga.getAdLibCod() + " - " + riga.getAdLibDes(), Integer.toString(riga.getAnnoCorso()), String.valueOf(riga.getPeso().intValue()), null, null));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            RecyclerView recyclerView = requireView().findViewById(R.id.exams);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            SubjectCardAdapter adapter = new SubjectCardAdapter(getContext(), exams);
            recyclerView.setAdapter(adapter);
            Common.stopLoading(requireView().findViewById(R.id.exams),requireView(),R.id.loading);
        }).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });

        //when back button is pressed, go back to home fragment
        view.findViewById(R.id.btn_back).setOnClickListener(v -> {
            NavOptions navOptions = new NavOptions.Builder()
                    .setPopUpTo(R.id.homeFragment, true)
                    .build();
            navController.navigate(R.id.homeFragment, null, navOptions);
        });
    }
}
