package org.epic_guys.esse4.common;

import org.epic_guys.esse4.models.Esito;
import org.epic_guys.esse4.models.RigaLibretto;

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
}
