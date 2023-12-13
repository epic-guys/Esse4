package org.epic_guys.esse4.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.epic_guys.esse4.API.API;
import org.epic_guys.esse4.API.services.LibrettoService;
import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.Appello;

import java.util.List;

import retrofit2.Call;

public class AppelliFragment extends Fragment {

    private RecyclerView appelliRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appelli, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //appelliRecyclerView = view.findViewById(R.id.recycler_view_appelli);

        AppelliFragmentArgs args = AppelliFragmentArgs.fromBundle(getArguments());
        long idCarriera = args.getIdCarriera();
        long idRigaLibretto = args.getIdRigaLibretto();
        LibrettoService librettoService = API.getService(LibrettoService.class);
        Call<List<Appello>> appelli = librettoService.getAppelliPerRigaLibretto(idCarriera, idRigaLibretto);
        Log.d("AppelliFragment", "onViewCreated: " + idCarriera + " " + idRigaLibretto);
        API.enqueueResource(appelli)
                .thenAccept(appelliList -> {
                    Log.d("AppelliFragment", "onViewCreated: " + appelliList.size());
                    Appello appello = appelliList.get(0);
                    TextView textView = view.findViewById(R.id.txt_first_appello);
                    textView.setText(appello.getDescrizioneAppello());
                })
                .exceptionally(throwable -> {
                    Log.d("AppelliFragment", "onViewCreated: " + throwable.getMessage());
                    return null;
                });
    }
}