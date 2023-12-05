package org.epic_guys.esse4.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import org.epic_guys.esse4.R;
import org.epic_guys.esse4.views.SubjectCardView;

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

        //when back button is pressed, go back to home fragment
        view.findViewById(R.id.btn_back).setOnClickListener(v -> navController.popBackStack());

        //THIS IS JUST A TEST, (AND IT DOESN'T WORK)
        SubjectCardView cardView = new SubjectCardView(getContext(), 1, "Analisi 1", "1", "12", null);

        SubjectCardView finalCardView = cardView;
        getActivity().runOnUiThread(() -> {
            ((ViewGroup)view.findViewById(R.id.study_plan_container)).addView(finalCardView);
        });

        //((ViewGroup)view.findViewById(R.id.study_plan_container)).addView(cardView);
        cardView = new SubjectCardView(getContext(), 2, "Analisi 2", "2", "12", null);
        ((ViewGroup)view.findViewById(R.id.study_plan_container)).addView(cardView);
        cardView = new SubjectCardView(getContext(), 3, "Analisi 3", "3", "12", null);
        ((ViewGroup)view.findViewById(R.id.study_plan_container)).addView(cardView);
        cardView = new SubjectCardView(getContext(), 4, "Analisi 4", "4", "12", null);
        ((ViewGroup)view.findViewById(R.id.study_plan_container)).addView(cardView);
        cardView = new SubjectCardView(getContext(), 5, "Analisi 5", "5", "12", null);
        ((ViewGroup)view.findViewById(R.id.study_plan_container)).addView(cardView);
        cardView = new SubjectCardView(getContext(), 6, "Analisi 6", "6", "12", null);
        ((ViewGroup)view.findViewById(R.id.study_plan_container)).addView(cardView);
    }




}
