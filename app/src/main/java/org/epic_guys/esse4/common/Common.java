package org.epic_guys.esse4.common;

import android.view.View;
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
        return dayOfWeek + " " + formatter.format(calendar.getTime());
    }

    public class ExamSession {
        private String name;
        private String date;
        private String hour;
        private String cfu;
        private String place;
        private String teacher;

        public ExamSession(String name, String date, String hour, String cfu, String place, String teacher) {
            this.name = name;
            this.date = date;
            this.hour = hour;
            this.cfu = cfu;
            this.place = place;
            this.teacher = teacher;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public String getHour() {
            return hour;
        }

        public String getCfu() {
            return cfu;
        }

        public String getPlace() {
            return place;
        }

        public String getTeacher() {
            return teacher;
        }
    }
}
