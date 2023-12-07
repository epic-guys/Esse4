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


import org.epic_guys.esse4.API.API;
import org.epic_guys.esse4.API.services.LibrettoService;
import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.Esito;
import org.epic_guys.esse4.models.RigaLibretto;
import org.epic_guys.esse4.views.SubjectCardView;

import java.util.List;

import retrofit2.Call;

public class StudentBookFragment extends Fragment {
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navController = NavHostFragment.findNavController(this);

        long idCarriera = API.getCarriera().getIdCarriera();
        LibrettoService librettoService = API.getService(LibrettoService.class);
        Call<List<RigaLibretto>> righeLibretto = librettoService.righeLibretto(idCarriera);
        API.enqueueResource(righeLibretto)
                .thenAccept(righe -> {
                    for (RigaLibretto riga : righe) {
                        try {
                            Esito esito = riga.getEsito();

                            long id = riga.getItmId();
                            String nomeString = riga.getAdCod() + " - " + riga.getDescrizioneAttivitaDidattica();
                            String annoString = riga.getAnnoCorso().toString();
                            String cfuString = riga.getPeso().toString();
                            String esitoString = (esito.getModValCod() == Esito.ModValCodEnum.V) ? (riga.getEsito().getVoto() == null) ? "N/D" : Integer.toString(riga.getEsito().getVoto().intValue()) : (riga.getEsito().getTipoGiudCod() == null) ? "N/D" : riga.getEsito().getTipoGiudCod();

                            SubjectCardView card = new SubjectCardView(getContext(), id, nomeString, annoString, cfuString, esitoString);
                            ((ViewGroup) getView().findViewById(R.id.exams)).addView(card);
                            //System.out.println(card.toString());
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //when back button is pressed, go back to home fragment
        view.findViewById(R.id.btn_back).setOnClickListener(v -> {
            NavOptions navOptions = new NavOptions.Builder()
                    .setPopUpTo(R.id.homeFragment, true)
                    .build();
            navController.navigate(R.id.homeFragment, null, navOptions);
        });
    }
}