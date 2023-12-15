package org.epic_guys.esse4.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.epic_guys.esse4.API.API;
import org.epic_guys.esse4.API.services.LibrettoService;
import org.epic_guys.esse4.R;
import org.epic_guys.esse4.common.Common;
import org.epic_guys.esse4.models.Appello;
import org.epic_guys.esse4.models.AppelloLibretto;
import org.epic_guys.esse4.views.ExamCardAdapter;

import java.util.List;

import retrofit2.Call;

public class AppelliFragment extends Fragment {

    private RecyclerView appelliRecyclerView;
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appelli, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        appelliRecyclerView = view.findViewById(R.id.recycler_view_appelli);
        appelliRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        AppelliFragmentArgs args = AppelliFragmentArgs.fromBundle(getArguments());

        long idCarriera = args.getIdCarriera();
        long idRigaLibretto = args.getIdRigaLibretto();
        LibrettoService librettoService = API.getService(LibrettoService.class);

        Call<List<AppelloLibretto>> appelli;

        Common.startLoading(requireView().findViewById(R.id.recycler_view_appelli), requireView(), R.id.loading);

        if (idRigaLibretto == -1) {
            appelli = librettoService.getAppelli(
                    idCarriera,
                    LibrettoService.Condizioni.APPELLI_PRENOTABILI_E_FUTURI
            );
        } else {
            appelli = librettoService.getAppelliPerRigaLibretto(
                    idCarriera,
                    idRigaLibretto,
                    LibrettoService.Condizioni.APPELLI_PRENOTABILI_E_FUTURI
            );
        }

        API.enqueueResource(appelli)
                .thenAccept(appelliList -> {
                    Log.d("AppelliFragment", "Ricevuti appelli: " + appelliList.size());
                    appelliRecyclerView.setAdapter(new ExamCardAdapter(appelliList));
                    Common.stopLoading(requireView().findViewById(R.id.recycler_view_appelli), requireView(), R.id.loading);
                })
                .exceptionally(throwable -> {
                    Log.w("AppelliFragment", throwable.getMessage());
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