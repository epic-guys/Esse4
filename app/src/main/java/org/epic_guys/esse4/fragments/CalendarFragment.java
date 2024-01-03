package org.epic_guys.esse4.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import org.epic_guys.esse4.API.API;
import org.epic_guys.esse4.API.services.LibrettoService;
import org.epic_guys.esse4.R;
import org.epic_guys.esse4.common.Common;
import org.epic_guys.esse4.models.AppelloLibretto;
import org.epic_guys.esse4.models.RigaLibretto;
import org.epic_guys.esse4.views.ExamCardAdapter;
import org.epic_guys.esse4.views.ListAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;

public class CalendarFragment extends Fragment {

    private NavController navController;
    private CalendarView calendarView;
    private Calendar calendar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calendarView = view.findViewById(R.id.calendar);
        calendar = Calendar.getInstance();

        ArrayList<Common.ExamSession> exams = new ArrayList<>();

        ListView listView = view.findViewById(R.id.list_view);

        TextView selectedDate = view.findViewById(R.id.selected_date);
        selectedDate.setText(Common.setCalendar(calendar, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)));

        long idCarriera = API.getCarriera().getIdCarriera();
        LibrettoService librettoService = API.getService(LibrettoService.class);
        Call<List<RigaLibretto>> righeLibretto = librettoService.righeLibretto(idCarriera);

        Call<List<AppelloLibretto>> appelli = librettoService.getAppelli(
                idCarriera,
                LibrettoService.Condizioni.APPELLI_PRENOTABILI_E_FUTURI
        );

        //TODO: guardare come Alvise ha fatto per gli appelli e fare una classe apposta per le sessioni d'esame

        API.enqueueResource(appelli)
                .thenAccept(System.out::println)
                .exceptionally(throwable -> {
                    Log.w("AppelliFragment", throwable.getMessage());
                    return null;
                });

/*        API.enqueueResource(righeLibretto)
                .thenAccept(righe -> {
                    for (RigaLibretto riga : righe) {
                        String nome = riga.getDescrizioneAttivitaDidattica();
                        if(!Objects.equals(nome, "PROVA FINALE") && Common.stringifyGrade(riga) == null) {
                            //exams.add(Common.ExamSession(nome, riga.getDataAppello(), riga.getOraAppello(), riga.getCrediti(), riga.getAula(), riga.getDocente()));
                        }
                    }
                    listView.setAdapter(new ListAdapter(getContext(), exams));
                });*/

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            selectedDate.setText(Common.setCalendar(calendar, year, month, dayOfMonth));
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
