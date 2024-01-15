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

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/**
 * ErroriIscrizioneAppello
 */
public class ErroriIscrizioneAppello {
  @SerializedName("applistaId")
  private Long applistaId = null;

  @SerializedName("cdsId")
  private Long cdsId = null;

  @SerializedName("adId")
  private Integer adId = null;

  @SerializedName("appId")
  private Integer appId = null;

  @SerializedName("appLogId")
  private Integer appLogId = null;

  @SerializedName("stuId")
  private Integer stuId = null;

  @SerializedName("tipoErrore")
  private String tipoErrore = null;

  @SerializedName("des")
  private String des = null;

  public ErroriIscrizioneAppello applistaId(Long applistaId) {
    this.applistaId = applistaId;
    return this;
  }

   /**
   * identificativo univoco della prenotazione
   * @return applistaId
  **/

  public Long getApplistaId() {
    return applistaId;
  }

  public void setApplistaId(Long applistaId) {
    this.applistaId = applistaId;
  }

  public ErroriIscrizioneAppello cdsId(Long cdsId) {
    this.cdsId = cdsId;
    return this;
  }

   /**
   * id del corso di studio di erogazione dell&#39;appello
   * @return cdsId
  **/

  public Long getCdsId() {
    return cdsId;
  }

  public void setCdsId(Long cdsId) {
    this.cdsId = cdsId;
  }

  public ErroriIscrizioneAppello adId(Integer adId) {
    this.adId = adId;
    return this;
  }

   /**
   * id dell&#39;attività didattica di erogazione dell&#39;appello
   * @return adId
  **/

  public Integer getAdId() {
    return adId;
  }

  public void setAdId(Integer adId) {
    this.adId = adId;
  }

  public ErroriIscrizioneAppello appId(Integer appId) {
    this.appId = appId;
    return this;
  }

   /**
   * id progressivo dell&#39;appello rispetto alla coppia (cds_id,ad_id)
   * @return appId
  **/

  public Integer getAppId() {
    return appId;
  }

  public void setAppId(Integer appId) {
    this.appId = appId;
  }

  public ErroriIscrizioneAppello appLogId(Integer appLogId) {
    this.appLogId = appLogId;
    return this;
  }

   /**
   * id progressivo del turno rispetto alla terna (cds_id,ad_id,app_id)
   * @return appLogId
  **/

  public Integer getAppLogId() {
    return appLogId;
  }

  public void setAppLogId(Integer appLogId) {
    this.appLogId = appLogId;
  }

  public ErroriIscrizioneAppello stuId(Integer stuId) {
    this.stuId = stuId;
    return this;
  }

   /**
   * id della carriera dello studente che ha effettato la prenotazione
   * @return stuId
  **/

  public Integer getStuId() {
    return stuId;
  }

  public void setStuId(Integer stuId) {
    this.stuId = stuId;
  }

  public ErroriIscrizioneAppello tipoErrore(String tipoErrore) {
    this.tipoErrore = tipoErrore;
    return this;
  }

   /**
   * tipo di errore
   * @return tipoErrore
  **/

  public String getTipoErrore() {
    return tipoErrore;
  }

  public void setTipoErrore(String tipoErrore) {
    this.tipoErrore = tipoErrore;
  }

  public ErroriIscrizioneAppello des(String des) {
    this.des = des;
    return this;
  }

   /**
   * descrizione dell&#39;errore
   * @return des
  **/

  public String getDes() {
    return des;
  }

  public void setDes(String des) {
    this.des = des;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErroriIscrizioneAppello erroriIscrizioneAppello = (ErroriIscrizioneAppello) o;
    return Objects.equals(this.applistaId, erroriIscrizioneAppello.applistaId) &&
        Objects.equals(this.cdsId, erroriIscrizioneAppello.cdsId) &&
        Objects.equals(this.adId, erroriIscrizioneAppello.adId) &&
        Objects.equals(this.appId, erroriIscrizioneAppello.appId) &&
        Objects.equals(this.appLogId, erroriIscrizioneAppello.appLogId) &&
        Objects.equals(this.stuId, erroriIscrizioneAppello.stuId) &&
        Objects.equals(this.tipoErrore, erroriIscrizioneAppello.tipoErrore) &&
        Objects.equals(this.des, erroriIscrizioneAppello.des);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applistaId, cdsId, adId, appId, appLogId, stuId, tipoErrore, des);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErroriIscrizioneAppello {\n");
    
    sb.append("    applistaId: ").append(toIndentedString(applistaId)).append("\n");
    sb.append("    cdsId: ").append(toIndentedString(cdsId)).append("\n");
    sb.append("    adId: ").append(toIndentedString(adId)).append("\n");
    sb.append("    appId: ").append(toIndentedString(appId)).append("\n");
    sb.append("    appLogId: ").append(toIndentedString(appLogId)).append("\n");
    sb.append("    stuId: ").append(toIndentedString(stuId)).append("\n");
    sb.append("    tipoErrore: ").append(toIndentedString(tipoErrore)).append("\n");
    sb.append("    des: ").append(toIndentedString(des)).append("\n");
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

