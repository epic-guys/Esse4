package org.epic_guys.esse4;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import org.epic_guys.esse4.API.JWTRefresher;
import org.epic_guys.esse4.fragments.AppelliFragment;
import org.epic_guys.esse4.fragments.dialogs.ExamSubscribeDialogFragment;
import org.epic_guys.esse4.models.AppelloLibretto;
import org.epic_guys.esse4.models.ParametriIscrizioneAppello;

import java.util.Objects;

public class MainActivity
        extends AppCompatActivity
        implements ExamSubscribeDialogFragment.ExamSubscribeDialogListener {

    private JWTRefresher jwtRefresher;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        jwtRefresher = new JWTRefresher(this);
        jwtRefresher.start();
    }


    @Override
    public void onSubscribe(
            AppelloLibretto appelloLibretto,
            ParametriIscrizioneAppello parametri
    ) {
        Log.d("MainActivity", "onSubscribe: " + appelloLibretto.getDescrizioneAttivitaDidattica());
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        AppelliFragment fragment = (AppelliFragment) navHostFragment.getChildFragmentManager().getFragments().get(0);
        // AppelliFragment fragment = (AppelliFragment) getSupportFragmentManager().findFragmentById(R.id.appelliFragment);
        assert fragment != null;
        fragment.subscribe(appelloLibretto, parametri);
    }
}
