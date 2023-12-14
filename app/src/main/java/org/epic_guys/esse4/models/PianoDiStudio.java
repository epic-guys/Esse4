package org.epic_guys.esse4.models;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.epic_guys.esse4.models.TestataPianoDiStudio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PianoDiStudio extends TestataPianoDiStudio {
    @SerializedName("regole")
    private List<ScePianoDiStudio> regole = null;

    @SerializedName("attivita")
    private List<ADPianoDiStudio> attivita = null;

    public PianoDiStudio regole(List<ScePianoDiStudio> regole) {
        this.regole = regole;
        return this;
    }

    public PianoDiStudio addRegoleItem(ScePianoDiStudio regoleItem) {
        if (this.regole == null) {
            this.regole = new ArrayList<ScePianoDiStudio>();
        }
        this.regole.add(regoleItem);
        return this;
    }

    public List<ScePianoDiStudio> getRegole() {
        return regole;
    }

    public void setRegole(List<ScePianoDiStudio> regole) {
        this.regole = regole;
    }

    public PianoDiStudio attivita(List<ADPianoDiStudio> attivita) {
        this.attivita = attivita;
        return this;
    }

    public PianoDiStudio addAttivitaItem(ADPianoDiStudio attivitaItem) {
        if (this.attivita == null) {
            this.attivita = new ArrayList<ADPianoDiStudio>();
        }
        this.attivita.add(attivitaItem);
        return this;
    }

    public List<ADPianoDiStudio> getAttivita() {
        return attivita;
    }

    public void setAttivita(List<ADPianoDiStudio> attivita) {
        this.attivita = attivita;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PianoDiStudio pianoDiStudio = (PianoDiStudio) o;
        return Objects.equals(this.regole, pianoDiStudio.regole) &&
                Objects.equals(this.attivita, pianoDiStudio.attivita) &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regole, attivita, super.hashCode());
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PianoDiStudio {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    regole: ").append(toIndentedString(regole)).append("\n");
        sb.append("    attivita: ").append(toIndentedString(attivita)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
