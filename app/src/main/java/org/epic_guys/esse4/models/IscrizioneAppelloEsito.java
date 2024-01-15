/*
 * ESSE3 Calendario Esami  e Prenotazione API
 * i servizi presenti permetto di operare sul calendario esami di ESSE3 e di effettuare le operazioni sulle liste iscritti, le principali operazioni sono le seguenti  * consultazione del calendario esami * inserimento e modifica di un appello * prenotazione dello studente * rimozione della prenotazione * presa visione del voto   * pubblicazione esiti * inserimento esiti  ## ChangeLog  Versione       | Versione di Esse3 di rilascio | Interventi  -              | -                             | -     1.1.0      | 17.07.03.00                   | aggiunto il metodo /appelli/{cdsId}/{adId}/{appId}/pubblicazione      1.1.0      | 17.07.03.00                   | aggiunto il metodo /appelli/{cdsId}/{adId}/{appId}/turni/{appLogId}/pubblicazione      1.1.0      | 17.07.03.00                   | aggiunto il metodo /appelli/{cdsId}/{adId}/{appId}/iscritti/{stuId}/esito     1.2.0      | 17.09.04.00                   | aggiunto l'utente tecnico (grp16)     1.3.0      | 17.12.02.00                   | abilitato lo studente all'accesso del metodo /appelli/{cdsId}/{adId}/{appId}     1.3.0      | 17.12.02.00                   | aggiunto il filtro q=APPELLI_PRENOTABILI sui metodo /appelli/{cdsId}/{adId} e /appelli/{cdsId}/{adId}/{appId} per i soli studenti     1.4.0      | 18.02.00.00                   | aggiunto il campo extAulaCod sul turno dell'appello     1.4.0      | 18.02.00.00                   | aggiunti i campi desAppello,desTurno,dataOraInizioTurno su IscrizioneAppello     1.5.0      | 18.04.01.00                   | aggiunto l'endpoint /sessioni e relativi figli con le informazioni sulle sessioni     1.6.0      | 18.06.02.00                   | corretto il bug per sulla prenotazione appelli nel caso di warning, aggiunti i warning di prenotazione nella IscrizioneAppello     1.6.0      | 18.06.02.00                   | Aggiunta la gestione delle commissioni nella PUT di modifica appello /appelli/{cdsId}/{adId}/{appId}/     1.7.0      | 18.10.01.00                   | Aggiunta la gestione dei link tra appelli nei metodi POST, PUT, GEST /appelli/{cdsId}/{adId}/{appId}/     1.8.0      | 18.12.01.00                   | Aggiunta la gestione delle prentazioni collegate in fase di presa visione su /prenotazioni/{matId}/{applistaId}/presaVisione     1.8.0      | 18.12.01.00                   | Aggiunta la gestione delle prentazioni collegate in fase di presa visione su /appelli/{cdsId}/{adId}/{appId}/iscritti/{stuId}/presaVisione     1.9.0      | 19.04.01.00                   | Aggiunta l'API /sistLogExt/export     1.9.0      | 19.04.01.00                   | Aggiunta l'API /sistLogExt/export/{elabId}     1.9.0      | 19.04.01.00                   | Aggiunta l'API /sistLogExt/export/{elabId}/eventi     1.9.0      | 19.04.01.00                   | Aggiunta l'API /sistLogExt/export/{elabId}/sessioni     1.10.0     | 19.10.03.00                   | Modificata l'API calesa-service-v1/appelli/{cdsId}/{adId}/{appId}/iscritti aggiungendo il supporto all'utenteTecnico     1.10.0     | 19.10.03.00                   | Modificata l'API calesa-service-v1/appelli/{cdsId}/{adId}/{appId}/iscritti/{stuId} aggiungendo il supporto all'utenteTecnico     1.10.0     | 19.10.03.00                   | Modificata l'API calesa-service-v1/prenotazioni/{matId} aggiungendo il supporto all'utenteTecnico     1.10.0     | 19.10.03.00                   | Modificata l'API calesa-service-v1/prenotazioni/{matId}/{applistaId} aggiungendo il supporto all'utenteTecnico     1.11.0     | 20.01.00.00                   | Modificata l'API calesa-service-v1//appelli/{cdsId}/{adId}/{appId}/iscritti aggiungendo al body della post il campo attoreCod (solo per utenti tecnici)     1.12.0     | 20.05.01.00                   | Aggiunto l'endpoint /abilitazioni/{docenteId}     1.12.0     | 20.05.01.00                   | Aggiunto il campo userId alla classe di modello IscrizioneAppello     1.12.0     | 20.05.01.00                   | Aggiunta la gestione del caricamento degli esiti tramite utenteTecnico (necessario valorizzare il campo docenteImpersId)     1.12.0     | 20.05.01.00                   | Aggiunto un filtro per recuperare la lista di appelli integrati con moodle     1.13.0     | 20.06.00.00                   | Aggiunta la possibilità di definire le misure compensative per una singola prenotazione     1.14.0     | 20.07.02.00                   | Modificata l'API calesa-service-v1/appelli/{cdsId}/{adId} aggiungendo il parametro opzionale config e il relativo parametro in queryString attoreCod     1.14.0     | 20.07.02.00                   | Modificata l'API calesa-service-v1/appelli/{cdsId}/{adId}/{appId} aggiungendo il parametro opzionale config e il relativo parametro in queryString attoreCod     1.15.0     | 20.10.02.00                   | Aggiunta la gestione del tipoSvolgimentoEsame alla prenotazione (POST /appelli/{cdsId}/{adId}/{appId}/iscritti)     1.15.0     | 20.10.02.00                   | Aggiunta la gestione del tipoSvolgimentoEsame all'inserimento esiti (PUT /appelli/{cdsId}/{adId}/{appId}/iscritti/{stuId}/esito)     1.16.0     | 20.11.00.00                   | Aggiunto l'endpoint /sistLogExt/update per l'aggiornamento di impegni precedentemente importati     1.17.0     | 21.03.03.00                   | Aggiunto il codice del template gruppo turni nel caricamento di un appello     1.18.0     | 21.04.03.00                   | aggiunto il filtro q=APPELLI_PRENOTABILI_E_FUTURI     1.19.0     | 21.05.02.00                   | aggiunto endpoint /abilitazioni/{docenteId}/appelli     1.20.0     | 21.06.01.00                   | Aggiunto endpoint /appelli/{cdsId}/{adId}/{appId}/tipi-svolgimento-esame     1.21.0     | 21.10.00.00                   | Aggiunta descrizione eventi in inglese perl'endpoint /sistLogExt/export/{elabId}/eventi     1.22.0     | 21.10.01.00                   | Aggiunta l'api per la gestione degli esami  comuni     1.23.0     | 21.11.00.00                   | Aggiunto endpoint /appelli/{cdsId}/{adId}/{appId}/tags/{adsceId}     1.24.0     | 22.06.00.00                   | Aggiunto endpoint /appelli/{cdsId}/{adId}/{appId}/iscritti/{stuId}     1.25.0     | 23.03.02.00                   | aggiunti gli endpoint per il recupero dello statino di prenotazione (pdf) e attestato di presenza esame (pdf)    
 *
 * OpenAPI spec version: 1.25.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package org.epic_guys.esse4.models;

import androidx.annotation.NonNull;

import java.util.Objects;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import org.epic_guys.esse4.common.EnumAdapter;
/**
 * IscrizioneAppelloEsito
 */
public class IscrizioneAppelloEsito {
  /**
   * modalità di valutazione della prova, deve corrispondere con il tipo di esito inserito. I flag assente e ritirato sono in esclusione rispetto al voto 
   */
  @JsonAdapter(ModValCodEnum.Adapter.class)
  public enum ModValCodEnum {
    V("V"),
    
    G("G");

    private String value;

    ModValCodEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static ModValCodEnum fromValue(String text) {
      for (ModValCodEnum b : ModValCodEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends EnumAdapter<ModValCodEnum> {
      @Override
      public Class<ModValCodEnum> getTClass() {
        return ModValCodEnum.class;
      }
    }
  }

  @SerializedName("modValCod")
  private ModValCodEnum modValCod = null;

  @SerializedName("superatoFlg")
  private Integer superatoFlg = null;

  @SerializedName("votoEsa")
  private Integer votoEsa = null;

  @SerializedName("tipoGiudCod")
  private String tipoGiudCod = null;

  @SerializedName("tipoGiudizioDes")
  private String tipoGiudizioDes = null;

  @SerializedName("assenteFlg")
  private Integer assenteFlg = null;

  @SerializedName("ritiratoFlg")
  private Integer ritiratoFlg = null;

  public IscrizioneAppelloEsito modValCod(ModValCodEnum modValCod) {
    this.modValCod = modValCod;
    return this;
  }

   /**
   * modalità di valutazione della prova, deve corrispondere con il tipo di esito inserito. I flag assente e ritirato sono in esclusione rispetto al voto 
   * @return modValCod
  **/

  public ModValCodEnum getModValCod() {
    return modValCod;
  }

  public void setModValCod(ModValCodEnum modValCod) {
    this.modValCod = modValCod;
  }

  public IscrizioneAppelloEsito superatoFlg(Integer superatoFlg) {
    this.superatoFlg = superatoFlg;
    return this;
  }

   /**
   * indica se il voto/giudizio sono positivi
   * minimum: 0
   * maximum: 1
   * @return superatoFlg
  **/

  public Integer getSuperatoFlg() {
    return superatoFlg;
  }

  public void setSuperatoFlg(Integer superatoFlg) {
    this.superatoFlg = superatoFlg;
  }

  public IscrizioneAppelloEsito votoEsa(Integer votoEsa) {
    this.votoEsa = votoEsa;
    return this;
  }

   /**
   * votazione numerica della prova d&#39;esame
   * minimum: 0
   * @return votoEsa
  **/

  public Integer getVotoEsa() {
    return votoEsa;
  }

  public void setVotoEsa(Integer votoEsa) {
    this.votoEsa = votoEsa;
  }

  public IscrizioneAppelloEsito tipoGiudCod(String tipoGiudCod) {
    this.tipoGiudCod = tipoGiudCod;
    return this;
  }

   /**
   * votazione a giudizio della prova d&#39;esame
   * @return tipoGiudCod
  **/

  public String getTipoGiudCod() {
    return tipoGiudCod;
  }

  public void setTipoGiudCod(String tipoGiudCod) {
    this.tipoGiudCod = tipoGiudCod;
  }

  public IscrizioneAppelloEsito tipoGiudizioDes(String tipoGiudizioDes) {
    this.tipoGiudizioDes = tipoGiudizioDes;
    return this;
  }

   /**
   * descrizione della votazione a giudizio della prova d&#39;esame
   * @return tipoGiudizioDes
  **/

  public String getTipoGiudizioDes() {
    return tipoGiudizioDes;
  }

  public void setTipoGiudizioDes(String tipoGiudizioDes) {
    this.tipoGiudizioDes = tipoGiudizioDes;
  }

  public IscrizioneAppelloEsito assenteFlg(Integer assenteFlg) {
    this.assenteFlg = assenteFlg;
    return this;
  }

   /**
   * flag che indica l&#39;assenza alla prova
   * minimum: 0
   * maximum: 1
   * @return assenteFlg
  **/

  public Integer getAssenteFlg() {
    return assenteFlg;
  }

  public void setAssenteFlg(Integer assenteFlg) {
    this.assenteFlg = assenteFlg;
  }

  public IscrizioneAppelloEsito ritiratoFlg(Integer ritiratoFlg) {
    this.ritiratoFlg = ritiratoFlg;
    return this;
  }

   /**
   * flag che indica il ritiro alla prova
   * minimum: 0
   * maximum: 1
   * @return ritiratoFlg
  **/

  public Integer getRitiratoFlg() {
    return ritiratoFlg;
  }

  public void setRitiratoFlg(Integer ritiratoFlg) {
    this.ritiratoFlg = ritiratoFlg;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IscrizioneAppelloEsito iscrizioneAppelloEsito = (IscrizioneAppelloEsito) o;
    return Objects.equals(this.modValCod, iscrizioneAppelloEsito.modValCod) &&
        Objects.equals(this.superatoFlg, iscrizioneAppelloEsito.superatoFlg) &&
        Objects.equals(this.votoEsa, iscrizioneAppelloEsito.votoEsa) &&
        Objects.equals(this.tipoGiudCod, iscrizioneAppelloEsito.tipoGiudCod) &&
        Objects.equals(this.tipoGiudizioDes, iscrizioneAppelloEsito.tipoGiudizioDes) &&
        Objects.equals(this.assenteFlg, iscrizioneAppelloEsito.assenteFlg) &&
        Objects.equals(this.ritiratoFlg, iscrizioneAppelloEsito.ritiratoFlg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(modValCod, superatoFlg, votoEsa, tipoGiudCod, tipoGiudizioDes, assenteFlg, ritiratoFlg);
  }


  @NonNull
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IscrizioneAppelloEsito {\n");
    
    sb.append("    modValCod: ").append(toIndentedString(modValCod)).append("\n");
    sb.append("    superatoFlg: ").append(toIndentedString(superatoFlg)).append("\n");
    sb.append("    votoEsa: ").append(toIndentedString(votoEsa)).append("\n");
    sb.append("    tipoGiudCod: ").append(toIndentedString(tipoGiudCod)).append("\n");
    sb.append("    tipoGiudizioDes: ").append(toIndentedString(tipoGiudizioDes)).append("\n");
    sb.append("    assenteFlg: ").append(toIndentedString(assenteFlg)).append("\n");
    sb.append("    ritiratoFlg: ").append(toIndentedString(ritiratoFlg)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

