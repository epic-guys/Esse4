package org.epic_guys.esse4.common;

import android.view.View;
import android.widget.LinearLayout;

import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.Esito;
import org.epic_guys.esse4.models.RigaLibretto;
import org.jetbrains.annotations.NotNull;

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
}
