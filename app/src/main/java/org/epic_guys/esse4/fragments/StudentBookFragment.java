package org.epic_guys.esse4.fragments;

import android.os.Bundle;
import android.util.Log;
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
import org.epic_guys.esse4.API.services.LibrettoService;
import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.RigaLibretto;
import org.epic_guys.esse4.views.SubjectCardAdapter;
import org.epic_guys.esse4.views.SubjectCardView;
import org.epic_guys.esse4.common.Common;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class StudentBookFragment extends Fragment {
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navController = NavHostFragment.findNavController(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<SubjectCardView> exams = new ArrayList<>();

        long idCarriera = API.getCarriera().getIdCarriera();
        LibrettoService librettoService = API.getService(LibrettoService.class);
        Call<List<RigaLibretto>> righeLibretto = librettoService.righeLibretto(idCarriera);
        API.enqueueResource(righeLibretto)
                .thenAccept(righe -> {
                    for (RigaLibretto riga : righe) {
                        try {
                            View.OnClickListener onClickListener = v -> {
                                Log.d("StudentBookFragment", "onClick: " + riga.getDescrizioneAttivitaDidattica());

                                StudentBookFragmentDirections.ActionStudentBookFragmentToAppelloFragment action =
                                        StudentBookFragmentDirections.actionStudentBookFragmentToAppelloFragment(
                                                riga.getAttivitaDidattica().getIdCorsoDiStudio(),
                                                riga.getAttivitaDidattica().getIdAttivitaDidattica()
                                        );

                                navController.navigate(action);
                            };
                            exams.add(new SubjectCardView(getContext(), riga, onClickListener));
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                    RecyclerView recyclerView = requireView().findViewById(R.id.exams);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    SubjectCardAdapter adapter = new SubjectCardAdapter(getContext(), exams);
                    recyclerView.setAdapter(adapter);
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