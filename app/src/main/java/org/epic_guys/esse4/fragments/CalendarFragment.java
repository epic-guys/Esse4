package org.epic_guys.esse4.fragments;

import android.os.Bundle;
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
import org.epic_guys.esse4.views.ListAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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

        Common.startLoading( requireView().findViewById(R.id.page),requireView(), R.id.loading);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        calendarView = view.findViewById(R.id.calendar);
        calendar = Calendar.getInstance();

        ArrayList<AppelloLibretto> appelliTotali = new ArrayList<>();

        ListView listView = view.findViewById(R.id.list_view);
        TextView selectedDate = view.findViewById(R.id.selected_date);
        selectedDate.setText(Common.setCalendar(calendar, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)));

        long idCarriera = API.getCarriera().getIdCarriera();
        LibrettoService librettoService = API.getService(LibrettoService.class);
        Call<List<AppelloLibretto>> appelli = librettoService.getAppelli(idCarriera, LibrettoService.Condizioni.APPELLI_PRENOTABILI_E_FUTURI);

        API.enqueueResource(appelli)
                .thenAccept(appelliList -> {

                    initCalendar(calendarView, appelliList);

                    appelliTotali.addAll(appelliList);
                    HashMap<String, ArrayList<AppelloLibretto>> appelliPerData = new HashMap<>();

                    for (AppelloLibretto appello : appelliTotali) {
                        if(appello.getDataOraEsame() == null) {
                            continue;
                        }
                        String dataChiave = appello.getDataOraEsame().format(formatter);
                        appelliPerData.computeIfAbsent(dataChiave, k -> new ArrayList<>()).add(appello);
                    }
                    Common.stopLoading(requireView().findViewById(R.id.page), requireView(), R.id.loading);

                    calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
                        selectedDate.setText(Common.setCalendar(calendar, year, month, dayOfMonth));

                        String dataSelezionata = LocalDate.of(year, month + 1, dayOfMonth).format(formatter);
                        ArrayList<AppelloLibretto> appelliGiorno = appelliPerData.getOrDefault(dataSelezionata, new ArrayList<>());
                        listView.setAdapter(new ListAdapter(requireContext(), appelliGiorno));
                    });
                });


        //when back button is pressed, go back to home fragment
        view.findViewById(R.id.btn_back).setOnClickListener(v -> {
            NavOptions navOptions = new NavOptions.Builder()
                    .setPopUpTo(R.id.homeFragment, true)
                    .build();
            navController.navigate(R.id.homeFragment, null, navOptions);
        });
    }

    private void initCalendar(CalendarView calendarView, List<AppelloLibretto> appelliList) {
        //metti un pallino sotto i giorni che hanno appelli
    }
}
