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
import org.epic_guys.esse4.API.services.LibrettoService;
import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.Esito;
import org.epic_guys.esse4.models.RigaLibretto;
import org.epic_guys.esse4.views.SubjectCardAdapter;
import org.epic_guys.esse4.views.SubjectCardView;

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
                            Esito esito = riga.getEsito();

                            long id = riga.getItmId();
                            String nomeString = riga.getAdCod() + " - " + riga.getDescrizioneAttivitaDidattica();
                            String annoString = riga.getAnnoCorso().toString();
                            String cfuString = String.valueOf(riga.getPeso().intValue());
                            String esitoString = (esito.getModValCod() == Esito.ModValCodEnum.V) ? (riga.getEsito().getVoto() == null) ? "N/D" : Integer.toString(riga.getEsito().getVoto().intValue()) : (riga.getEsito().getTipoGiudCod() == null) ? "N/D" : riga.getEsito().getTipoGiudCod();
                            // SCOMMENTARE PER VEDERE OUTPUT IN CASO SIA UN GIUDIZIO E NON VOTO
/*                            if(esito.getModValCod() == Esito.ModValCodEnum.G)
                                System.out.println(riga.getEsito().getTipoGiudCod());*/
                            exams.add(new SubjectCardView(getContext(), id, nomeString, annoString, cfuString, esitoString));
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                    RecyclerView recyclerView = getView().findViewById(R.id.exams);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    SubjectCardAdapter adapter = new SubjectCardAdapter(getContext(), exams);
                    recyclerView.setAdapter(adapter);
                    //System.out.println(exams);
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