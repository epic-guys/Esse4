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
import org.epic_guys.esse4.API.services.CalendarioEsamiService;
import org.epic_guys.esse4.API.services.LibrettoService;
import org.epic_guys.esse4.R;
import org.epic_guys.esse4.common.Common;
import org.epic_guys.esse4.models.AppelloLibretto;
import org.epic_guys.esse4.models.BookedAppello;
import org.epic_guys.esse4.models.IscrizioneAppello;
import org.epic_guys.esse4.models.ParametriIscrizioneAppello;
import org.epic_guys.esse4.models.RigaLibretto;
import org.epic_guys.esse4.views.BookedCardAdapter;
import org.epic_guys.esse4.views.ExamCardAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;

public class AppelliFragment extends Fragment {
    private RecyclerView appelliRecyclerView;
    private RecyclerView appelliPrenotatiRecyclerView;
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

    private CompletableFuture<List<BookedAppello>> generateAppelli(List<IscrizioneAppello> appelli){
        CompletableFuture<List<BookedAppello>> bookedAppelli = new CompletableFuture<>();
        LibrettoService librettoService = API.getService(LibrettoService.class);
        Call<List<RigaLibretto>> righeLibretto = librettoService.righeLibretto(API.getCarriera().getIdCarriera());
        API.enqueueResource(righeLibretto).thenAccept(righe -> {
                    List<BookedAppello> tmp = new ArrayList<>();
                    for(RigaLibretto riga : righe){
                        for(int i=0; i<appelli.size(); i++){
                            IscrizioneAppello appello = appelli.get(i);
                            if(appello.getAdsceId().equals(riga.getIdRigaLibretto())){
                                tmp.add(0,new BookedAppello(riga.getDescrizioneAttivitaDidattica(),appello.getDataIns(),String.valueOf(riga.getPeso().intValue())));
                                //noinspection SuspiciousListRemoveInLoop
                                appelli.remove(i);
                                //break; //WHY NOT BREAK? Because there could be more than one reservation for the same riga
                            }
                        }
                    }
                    //print all remaining appelli
                    for(IscrizioneAppello appello : appelli){
                        Log.i("AppelliFragment", "Appello non trovato: " + appello.getAdsceId());
                    }
                    bookedAppelli.complete(tmp);
                })
                .exceptionally(throwable -> {
                    Log.w("AppelliFragment", Objects.requireNonNull(throwable.getMessage()));
                    return null;
                });
        //return bookedAppelli when it's completed
        return bookedAppelli;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        appelliRecyclerView = view.findViewById(R.id.recycler_view_appelli);
        appelliRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        appelliPrenotatiRecyclerView = view.findViewById(R.id.recycler_view_appelli_prenotati);
        appelliPrenotatiRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
                .thenAccept(appelliList -> appelliRecyclerView.setAdapter(new ExamCardAdapter(appelliList)))
                .exceptionally(throwable -> {
                    Log.w("AppelliFragment", Objects.requireNonNull(throwable.getMessage()));
                    return null;
                }).thenApply(
                        unused -> {
                            Common.stopLoading(requireView().findViewById(R.id.recycler_view_appelli), requireView(), R.id.loading);
                            return null;
                        }
                );


        CalendarioEsamiService calEsaService = API.getService(CalendarioEsamiService.class);
        Call<List<IscrizioneAppello>> prenotati = calEsaService.getPrenotazioni(
                idCarriera
        );

        API.enqueueResource(prenotati)
                .thenAccept(prenotazioni -> generateAppelli(prenotazioni).thenAccept(bookedAppelli -> appelliPrenotatiRecyclerView.setAdapter(new BookedCardAdapter(bookedAppelli))))
                .exceptionally(throwable -> {
                    Log.w("AppelliFragment", Objects.requireNonNull(throwable.getMessage()));
                    return null;
                });


        //when back button is pressed, go back to home fragment
        view.findViewById(R.id.btn_back).setOnClickListener(v -> {
            NavOptions navOptions = new NavOptions.Builder()
                    .setPopUpTo(R.id.homeFragment, true)
                    .build();
            navController.navigate(R.id.homeFragment, null, navOptions);
        });

        view.findViewById(R.id.switch_appelli_button).setOnClickListener(v -> switchRecyclerViews());
    }

    private void switchRecyclerViews(){
        //see the visibility of the first recycler view
        int firstVisibility = appelliRecyclerView.getVisibility();

        //get the button

        //if the first is visible, hide it and show the second
        if(firstVisibility == View.VISIBLE){
            appelliRecyclerView.setVisibility(View.GONE);
            appelliPrenotatiRecyclerView.setVisibility(View.VISIBLE);
            ((TextView) requireView().findViewById(R.id.switch_appelli_button)).setText(R.string.see_booked);
        }
        else{
            appelliRecyclerView.setVisibility(View.VISIBLE);
            appelliPrenotatiRecyclerView.setVisibility(View.GONE);
            ((TextView) requireView().findViewById(R.id.switch_appelli_button)).setText(R.string.see_available);
        }
    }

    public void subscribe(
            AppelloLibretto appelloLibretto,
            ParametriIscrizioneAppello parametriIscrizioneAppello
    ) {
        CompletableFuture<Boolean> res = new CompletableFuture<>();
        CalendarioEsamiService service = API.getService(CalendarioEsamiService.class);
        Call<Void> call = service.postIscrizioneAppello(
                appelloLibretto.getCdsId(),
                appelloLibretto.getAdId(),
                appelloLibretto.getAppId(), //getAppId is different from getAppelloId (don't know why)
                parametriIscrizioneAppello
        );

        API.enqueueResource(call).thenAccept(aVoid -> {
                    res.complete(true);
                    requireActivity().runOnUiThread(() -> Toast.makeText(getContext(), "Iscrizione effettuata", Toast.LENGTH_SHORT).show());
                })
                .exceptionally(throwable -> {
                    res.complete(false);
                    //TODO: check that the error is because of a missing questionario
                    requireActivity().runOnUiThread(() -> Toast.makeText(getContext(), "Errore durante l'iscrizione", Toast.LENGTH_SHORT).show());
                    return null;
                });

        res.thenAccept(aBoolean -> {
            if (aBoolean) {
                Log.d(getClass().getName(), "Iscrizione effettuata");
                //refresh the fragment
                requireActivity().runOnUiThread(() -> {
                    NavOptions navOptions = new NavOptions.Builder()
                            .setPopUpTo(R.id.appelliFragment, true)
                            .build();
                    navController.navigate(R.id.appelliFragment, null, navOptions);
                });
            }
            else{
                Log.w(getClass().getName(), "Errore durante l'iscrizione");
            }
        });
    }
}