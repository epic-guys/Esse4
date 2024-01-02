package org.epic_guys.esse4.common;

import android.view.View;
import android.widget.LinearLayout;

import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.Esito;
import org.epic_guys.esse4.models.RigaLibretto;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Common {
    // SOME EXAMS HAVE AN EMPTY GRADE (literally ""), LIKE "Tirocinio" and "B2 Inglese"... What should we do?
    public static String stringifyGrade(RigaLibretto riga){
        Esito esito = riga.getEsito();
        if(esito.getModValCod() == Esito.ModValCodEnum.V){
            try{
                return riga.getEsito().getVoto().intValue() + (riga.getEsito().getLodeFlag() == 1 ? " L" : "");
            } catch (NullPointerException e){
                return null;
            }
        }
        else{
            return riga.getEsito().getTipoGiudCod();
        }
    }

    static public void startLoading(@NotNull View toHide,@NotNull View generalView, int loadingId){
        toHide.setVisibility(View.INVISIBLE);
        generalView.findViewById(loadingId).setVisibility(View.VISIBLE);
    }

    static public void stopLoading(@NotNull View toShow,View generalView, int loadingId){
        toShow.setVisibility(View.VISIBLE);
        generalView.findViewById(loadingId).setVisibility(View.INVISIBLE);
    }

    public static String setCalendar(Calendar calendar, int year, int month, int dayOfMonth) {
        calendar.set(year, month, dayOfMonth);
        String dayOfWeek = new SimpleDateFormat("EEEE", Locale.getDefault()).format(calendar.getTime());
        dayOfWeek = dayOfWeek.substring(0, 1).toUpperCase() + dayOfWeek.substring(1);
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        String selectedDateFormatted = dayOfWeek + " " + formatter.format(calendar.getTime());
        return selectedDateFormatted;
    }
}
