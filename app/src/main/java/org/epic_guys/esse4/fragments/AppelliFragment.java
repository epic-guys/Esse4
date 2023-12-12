package org.epic_guys.esse4.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.epic_guys.esse4.R;

public class AppelliFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appelli, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AppelliFragmentArgs args = AppelliFragmentArgs.fromBundle(getArguments());
        view.<TextView>findViewById(R.id.txt_id_ad).setText(
                String.valueOf(args.getIdAttivitaDidattica()));
        view.<TextView>findViewById(R.id.txt_id_corso).setText(
                String.valueOf(args.getIdCorsoDiStudio())
        );
    }
}