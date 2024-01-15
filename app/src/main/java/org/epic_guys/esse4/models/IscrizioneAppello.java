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

import org.epic_guys.esse4.common.EnumAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * IscrizioneAppello
 */
public class IscrizioneAppello {
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

  @SerializedName("adregId")
  private Long adregId = null;

  @SerializedName("adsceId")
  private Long adsceId = null;

  @SerializedName("matId")
  private Long matId = null;

  @SerializedName("adStuCod")
  private String adStuCod = null;

  @SerializedName("adStuDes")
  private String adStuDes = null;

  @SerializedName("cdsAdStuCod")
  private String cdsAdStuCod = null;

  @SerializedName("cdsAdStuDes")
  private String cdsAdStuDes = null;

  @SerializedName("cdsAdIdStu")
  private Long cdsAdIdStu = null;

  @SerializedName("desAppello")
  private String desAppello = null;

  @SerializedName("desTurno")
  private String desTurno = null;

  @SerializedName("dataOraTurno")
  private String dataOraTurno = null;

  @SerializedName("aaFreqId")
  private Integer aaFreqId = null;

  /**
   * Stato dell&#39;attività didattica (codice)
   */
  @JsonAdapter(StatoAdsceEnum.Adapter.class)
  public enum StatoAdsceEnum {
    P("P"),
    
    F("F"),
    
    S("S");

    private String value;

    StatoAdsceEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static StatoAdsceEnum fromValue(String text) {
      for (StatoAdsceEnum b : StatoAdsceEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends EnumAdapter<StatoAdsceEnum> {
        @Override
        public Class<StatoAdsceEnum> getTClass() {
            return StatoAdsceEnum.class;
        }
    }
  }

  @SerializedName("statoAdsce")
  private StatoAdsceEnum statoAdsce = null;

  @SerializedName("pesoAd")
  private Float pesoAd = null;

  @SerializedName("userId")
  private String userId = null;

  @SerializedName("matricola")
  private String matricola = null;

  @SerializedName("nomeStudente")
  private String nomeStudente = null;

  @SerializedName("nomeAlias")
  private String nomeAlias = null;

  @SerializedName("cognomeStudente")
  private String cognomeStudente = null;

  @SerializedName("codFisStudente")
  private String codFisStudente = null;

  @SerializedName("dataNascitaStudente")
  private String dataNascitaStudente = null;

  /**
   * sesso dello studente
   */
  @JsonAdapter(SessoStudenteEnum.Adapter.class)
  public enum SessoStudenteEnum {
    M("M"),
    
    F("F");

    private String value;

    SessoStudenteEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static SessoStudenteEnum fromValue(String text) {
      for (SessoStudenteEnum b : SessoStudenteEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends EnumAdapter<SessoStudenteEnum> {
      @Override
      public Class<SessoStudenteEnum> getTClass() {
        return SessoStudenteEnum.class;
      }
    }
  }

  @SerializedName("sessoStudente")
  private SessoStudenteEnum sessoStudente = null;

  @SerializedName("comuNascCodIstat")
  private String comuNascCodIstat = null;

  @SerializedName("cittStraNasc")
  private String cittStraNasc = null;

  @SerializedName("cittCod")
  private String cittCod = null;

  @SerializedName("cdsStuCod")
  private String cdsStuCod = null;

  @SerializedName("cdsStuDes")
  private String cdsStuDes = null;

  @SerializedName("cdsIdStu")
  private Long cdsIdStu = null;

  @SerializedName("aaOrdStuId")
  private Integer aaOrdStuId = null;

  @SerializedName("pdsStuCod")
  private String pdsStuCod = null;

  @SerializedName("pdsStuDes")
  private String pdsStuDes = null;

  @SerializedName("pdsIdStu")
  private Long pdsIdStu = null;

  @SerializedName("pubblId")
  private Long pubblId = null;

  /**
   * stato di presa visione dell&#39;esito
   */
  @JsonAdapter(PresaVisioneEnum.Adapter.class)
  public enum PresaVisioneEnum {
    N("N"),
    
    V("V"),
    
    A("A"),
    
    R("R");

    private String value;

    PresaVisioneEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static PresaVisioneEnum fromValue(String text) {
      for (PresaVisioneEnum b : PresaVisioneEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends EnumAdapter<PresaVisioneEnum> {
        @Override
        public Class<PresaVisioneEnum> getTClass() {
            return PresaVisioneEnum.class;
        }
    }
  }

  @SerializedName("presaVisione")
  private PresaVisioneEnum presaVisione = null;

  @SerializedName("userIdPresaVisione")
  private String userIdPresaVisione = null;

  @SerializedName("userGrpPresaVisione")
  private Long userGrpPresaVisione = null;

  @SerializedName("dataRifEsito")
  private String dataRifEsito = null;

  @SerializedName("dataRifEsitoStu")
  private String dataRifEsitoStu = null;

  @SerializedName("notaPubbl")
  private String notaPubbl = null;

  @SerializedName("gruppoVotoCod")
  private String gruppoVotoCod = null;

  @SerializedName("gruppoVotoMaxPunti")
  private Integer gruppoVotoMaxPunti = null;

  @SerializedName("esito")
  private IscrizioneAppelloEsito esito = null;

  @SerializedName("manualeFlg")
  private Integer manualeFlg = null;

  @SerializedName("dataEsa")
  private String dataEsa = null;

  @SerializedName("domandeEsame")
  private String domandeEsame = null;

  @SerializedName("notaStudente")
  private String notaStudente = null;

  @SerializedName("tipoSvolgimentoEsameCod")
  private String tipoSvolgimentoEsameCod = null;

  @SerializedName("tipoSvolgimentoEsameDes")
  private String tipoSvolgimentoEsameDes = null;

  @SerializedName("tipoSvolgimentoEsameRichiestaFlg")
  private String tipoSvolgimentoEsameRichiestaFlg = null;

  @SerializedName("tagCod")
  private String tagCod = null;

  @SerializedName("autoTagCod")
  private String autoTagCod = null;

  @SerializedName("livUscitaCod")
  private String livUscitaCod = null;

  @SerializedName("linguaUscitaCod")
  private String linguaUscitaCod = null;

  @SerializedName("dataIns")
  private String dataIns = null;

  @SerializedName("tipoDefAppCod")
  private String tipoDefAppCod = null;

  @SerializedName("tipoGestPrenCod")
  private String tipoGestPrenCod = null;

  @SerializedName("tipoGestAppCod")
  private String tipoGestAppCod = null;

  @SerializedName("tipoAppCod")
  private String tipoAppCod = null;

  @SerializedName("posiz")
  private Integer posiz = null;

  @SerializedName("posizApp")
  private Integer posizApp = null;

  @SerializedName("dataInizioIscr")
  private String dataInizioIscr = null;

  @SerializedName("dataFineIscr")
  private String dataFineIscr = null;

  @SerializedName("tipoIscrCod")
  private String tipoIscrCod = null;

  @SerializedName("tipoEsaCod")
  private String tipoEsaCod = null;

  @SerializedName("aaCalId")
  private Integer aaCalId = null;

  @SerializedName("aaSesId")
  private Integer aaSesId = null;

  @SerializedName("sesDes")
  private String sesDes = null;

  @SerializedName("warnings")
  private List<ErroriIscrizioneAppello> warnings = null;

  public IscrizioneAppello applistaId(Long applistaId) {
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

  public IscrizioneAppello cdsId(Long cdsId) {
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

  public IscrizioneAppello adId(Integer adId) {
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

  public IscrizioneAppello appId(Integer appId) {
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

  public IscrizioneAppello appLogId(Integer appLogId) {
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

  public IscrizioneAppello stuId(Integer stuId) {
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

  public IscrizioneAppello adregId(Long adregId) {
    this.adregId = adregId;
    return this;
  }

   /**
   * id della prova collegata alla preontazione
   * @return adregId
  **/

  public Long getAdregId() {
    return adregId;
  }

  public void setAdregId(Long adregId) {
    this.adregId = adregId;
  }

  public IscrizioneAppello adsceId(Long adsceId) {
    this.adsceId = adsceId;
    return this;
  }

   /**
   * id della riga di libretto collegata alla prenotazione
   * @return adsceId
  **/

  public Long getAdsceId() {
    return adsceId;
  }

  public void setAdsceId(Long adsceId) {
    this.adsceId = adsceId;
  }

  public IscrizioneAppello matId(Long matId) {
    this.matId = matId;
    return this;
  }

   /**
   * id della matricola collegata alla prenotazione
   * @return matId
  **/

  public Long getMatId() {
    return matId;
  }

  public void setMatId(Long matId) {
    this.matId = matId;
  }

  public IscrizioneAppello adStuCod(String adStuCod) {
    this.adStuCod = adStuCod;
    return this;
  }

   /**
   * codice dell&#39;attività prenotata dallo studente
   * @return adStuCod
  **/

  public String getAdStuCod() {
    return adStuCod;
  }

  public void setAdStuCod(String adStuCod) {
    this.adStuCod = adStuCod;
  }

  public IscrizioneAppello adStuDes(String adStuDes) {
    this.adStuDes = adStuDes;
    return this;
  }

   /**
   * descrizione dell&#39;attività prenotata dallo studente
   * @return adStuDes
  **/

  public String getAdStuDes() {
    return adStuDes;
  }

  public void setAdStuDes(String adStuDes) {
    this.adStuDes = adStuDes;
  }

  public IscrizioneAppello cdsAdStuCod(String cdsAdStuCod) {
    this.cdsAdStuCod = cdsAdStuCod;
    return this;
  }

   /**
   * codice del corso di studio prenotato dallo studente
   * @return cdsAdStuCod
  **/

  public String getCdsAdStuCod() {
    return cdsAdStuCod;
  }

  public void setCdsAdStuCod(String cdsAdStuCod) {
    this.cdsAdStuCod = cdsAdStuCod;
  }

  public IscrizioneAppello cdsAdStuDes(String cdsAdStuDes) {
    this.cdsAdStuDes = cdsAdStuDes;
    return this;
  }

   /**
   * descrizione del corso di studio prenotato dallo studente
   * @return cdsAdStuDes
  **/

  public String getCdsAdStuDes() {
    return cdsAdStuDes;
  }

  public void setCdsAdStuDes(String cdsAdStuDes) {
    this.cdsAdStuDes = cdsAdStuDes;
  }

  public IscrizioneAppello cdsAdIdStu(Long cdsAdIdStu) {
    this.cdsAdIdStu = cdsAdIdStu;
    return this;
  }

   /**
   * id del corso di studio prenotato dallo studente
   * @return cdsAdIdStu
  **/

  public Long getCdsAdIdStu() {
    return cdsAdIdStu;
  }

  public void setCdsAdIdStu(Long cdsAdIdStu) {
    this.cdsAdIdStu = cdsAdIdStu;
  }

  public IscrizioneAppello desAppello(String desAppello) {
    this.desAppello = desAppello;
    return this;
  }

   /**
   * descrizione dell&#39;appello
   * @return desAppello
  **/

  public String getDesAppello() {
    return desAppello;
  }

  public void setDesAppello(String desAppello) {
    this.desAppello = desAppello;
  }

  public IscrizioneAppello desTurno(String desTurno) {
    this.desTurno = desTurno;
    return this;
  }

   /**
   * descrizione del turno a cui è iscritto lo studente
   * @return desTurno
  **/

  public String getDesTurno() {
    return desTurno;
  }

  public void setDesTurno(String desTurno) {
    this.desTurno = desTurno;
  }

  public IscrizioneAppello dataOraTurno(String dataOraTurno) {
    this.dataOraTurno = dataOraTurno;
    return this;
  }

   /**
   * data/ora del turno a cui è iscritto lo studente
   * @return dataOraTurno
  **/

  public String getDataOraTurno() {
    return dataOraTurno;
  }

  public void setDataOraTurno(String dataOraTurno) {
    this.dataOraTurno = dataOraTurno;
  }

  public IscrizioneAppello aaFreqId(Integer aaFreqId) {
    this.aaFreqId = aaFreqId;
    return this;
  }

   /**
   * anno di frequenza dell&#39;attività
   * minimum: 1970
   * maximum: 2100
   * @return aaFreqId
  **/

  public Integer getAaFreqId() {
    return aaFreqId;
  }

  public void setAaFreqId(Integer aaFreqId) {
    this.aaFreqId = aaFreqId;
  }

  public IscrizioneAppello statoAdsce(StatoAdsceEnum statoAdsce) {
    this.statoAdsce = statoAdsce;
    return this;
  }

   /**
   * Stato dell&#39;attività didattica (codice)
   * @return statoAdsce
  **/

  public StatoAdsceEnum getStatoAdsce() {
    return statoAdsce;
  }

  public void setStatoAdsce(StatoAdsceEnum statoAdsce) {
    this.statoAdsce = statoAdsce;
  }

  public IscrizioneAppello pesoAd(Float pesoAd) {
    this.pesoAd = pesoAd;
    return this;
  }

   /**
   * peso dell&#39;&#39;attività didattica, il peso prevede due decimali opzionali
   * @return pesoAd
  **/

  public Float getPesoAd() {
    return pesoAd;
  }

  public void setPesoAd(Float pesoAd) {
    this.pesoAd = pesoAd;
  }

  public IscrizioneAppello userId(String userId) {
    this.userId = userId;
    return this;
  }

   /**
   * userId attivo dello studente
   * @return userId
  **/

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public IscrizioneAppello matricola(String matricola) {
    this.matricola = matricola;
    return this;
  }

   /**
   * codice matricola dello studente
   * @return matricola
  **/

  public String getMatricola() {
    return matricola;
  }

  public void setMatricola(String matricola) {
    this.matricola = matricola;
  }

  public IscrizioneAppello nomeStudente(String nomeStudente) {
    this.nomeStudente = nomeStudente;
    return this;
  }

   /**
   * nome dello studente
   * @return nomeStudente
  **/

  public String getNomeStudente() {
    return nomeStudente;
  }

  public void setNomeStudente(String nomeStudente) {
    this.nomeStudente = nomeStudente;
  }

  public IscrizioneAppello nomeAlias(String nomeAlias) {
    this.nomeAlias = nomeAlias;
    return this;
  }

   /**
   * nome alias dello studente
   * @return nomeAlias
  **/

  public String getNomeAlias() {
    return nomeAlias;
  }

  public void setNomeAlias(String nomeAlias) {
    this.nomeAlias = nomeAlias;
  }

  public IscrizioneAppello cognomeStudente(String cognomeStudente) {
    this.cognomeStudente = cognomeStudente;
    return this;
  }

   /**
   * cognome dello studente
   * @return cognomeStudente
  **/

  public String getCognomeStudente() {
    return cognomeStudente;
  }

  public void setCognomeStudente(String cognomeStudente) {
    this.cognomeStudente = cognomeStudente;
  }

  public IscrizioneAppello codFisStudente(String codFisStudente) {
    this.codFisStudente = codFisStudente;
    return this;
  }

   /**
   * codice fiscale dello studente
   * @return codFisStudente
  **/

  public String getCodFisStudente() {
    return codFisStudente;
  }

  public void setCodFisStudente(String codFisStudente) {
    this.codFisStudente = codFisStudente;
  }

  public IscrizioneAppello dataNascitaStudente(String dataNascitaStudente) {
    this.dataNascitaStudente = dataNascitaStudente;
    return this;
  }

   /**
   * data di nascita dello studente (DD/MM/YYYY)
   * @return dataNascitaStudente
  **/

  public String getDataNascitaStudente() {
    return dataNascitaStudente;
  }

  public void setDataNascitaStudente(String dataNascitaStudente) {
    this.dataNascitaStudente = dataNascitaStudente;
  }

  public IscrizioneAppello sessoStudente(SessoStudenteEnum sessoStudente) {
    this.sessoStudente = sessoStudente;
    return this;
  }

   /**
   * sesso dello studente
   * @return sessoStudente
  **/

  public SessoStudenteEnum getSessoStudente() {
    return sessoStudente;
  }

  public void setSessoStudente(SessoStudenteEnum sessoStudente) {
    this.sessoStudente = sessoStudente;
  }

  public IscrizioneAppello comuNascCodIstat(String comuNascCodIstat) {
    this.comuNascCodIstat = comuNascCodIstat;
    return this;
  }

   /**
   * codice istat del comune di nascita dello studente
   * @return comuNascCodIstat
  **/

  public String getComuNascCodIstat() {
    return comuNascCodIstat;
  }

  public void setComuNascCodIstat(String comuNascCodIstat) {
    this.comuNascCodIstat = comuNascCodIstat;
  }

  public IscrizioneAppello cittStraNasc(String cittStraNasc) {
    this.cittStraNasc = cittStraNasc;
    return this;
  }

   /**
   * codice della cittadinanza straniera di nascita dello studente
   * @return cittStraNasc
  **/

  public String getCittStraNasc() {
    return cittStraNasc;
  }

  public void setCittStraNasc(String cittStraNasc) {
    this.cittStraNasc = cittStraNasc;
  }

  public IscrizioneAppello cittCod(String cittCod) {
    this.cittCod = cittCod;
    return this;
  }

   /**
   * codice della cittadinanza dello studente
   * @return cittCod
  **/

  public String getCittCod() {
    return cittCod;
  }

  public void setCittCod(String cittCod) {
    this.cittCod = cittCod;
  }

  public IscrizioneAppello cdsStuCod(String cdsStuCod) {
    this.cdsStuCod = cdsStuCod;
    return this;
  }

   /**
   * codice corso di studio di iscrizione dello studente
   * @return cdsStuCod
  **/

  public String getCdsStuCod() {
    return cdsStuCod;
  }

  public void setCdsStuCod(String cdsStuCod) {
    this.cdsStuCod = cdsStuCod;
  }

  public IscrizioneAppello cdsStuDes(String cdsStuDes) {
    this.cdsStuDes = cdsStuDes;
    return this;
  }

   /**
   * descrizione corso di studio di iscrizione dello studente
   * @return cdsStuDes
  **/

  public String getCdsStuDes() {
    return cdsStuDes;
  }

  public void setCdsStuDes(String cdsStuDes) {
    this.cdsStuDes = cdsStuDes;
  }

  public IscrizioneAppello cdsIdStu(Long cdsIdStu) {
    this.cdsIdStu = cdsIdStu;
    return this;
  }

   /**
   * id del corso di studio di iscrizione dello studente
   * @return cdsIdStu
  **/

  public Long getCdsIdStu() {
    return cdsIdStu;
  }

  public void setCdsIdStu(Long cdsIdStu) {
    this.cdsIdStu = cdsIdStu;
  }

  public IscrizioneAppello aaOrdStuId(Integer aaOrdStuId) {
    this.aaOrdStuId = aaOrdStuId;
    return this;
  }

   /**
   * anno di ordinamento di iscrizione dello studente
   * @return aaOrdStuId
  **/

  public Integer getAaOrdStuId() {
    return aaOrdStuId;
  }

  public void setAaOrdStuId(Integer aaOrdStuId) {
    this.aaOrdStuId = aaOrdStuId;
  }

  public IscrizioneAppello pdsStuCod(String pdsStuCod) {
    this.pdsStuCod = pdsStuCod;
    return this;
  }

   /**
   * codice percorso di studio di iscrizione dello studente
   * @return pdsStuCod
  **/

  public String getPdsStuCod() {
    return pdsStuCod;
  }

  public void setPdsStuCod(String pdsStuCod) {
    this.pdsStuCod = pdsStuCod;
  }

  public IscrizioneAppello pdsStuDes(String pdsStuDes) {
    this.pdsStuDes = pdsStuDes;
    return this;
  }

   /**
   * descrizione percorso di studio di iscrizione dello studente
   * @return pdsStuDes
  **/

  public String getPdsStuDes() {
    return pdsStuDes;
  }

  public void setPdsStuDes(String pdsStuDes) {
    this.pdsStuDes = pdsStuDes;
  }

  public IscrizioneAppello pdsIdStu(Long pdsIdStu) {
    this.pdsIdStu = pdsIdStu;
    return this;
  }

   /**
   * id del percorso di studio di iscrizione dello studente
   * @return pdsIdStu
  **/

  public Long getPdsIdStu() {
    return pdsIdStu;
  }

  public void setPdsIdStu(Long pdsIdStu) {
    this.pdsIdStu = pdsIdStu;
  }

  public IscrizioneAppello pubblId(Long pubblId) {
    this.pubblId = pubblId;
    return this;
  }

   /**
   * id della pubblicazione dell&#39;esito
   * @return pubblId
  **/

  public Long getPubblId() {
    return pubblId;
  }

  public void setPubblId(Long pubblId) {
    this.pubblId = pubblId;
  }

  public IscrizioneAppello presaVisione(PresaVisioneEnum presaVisione) {
    this.presaVisione = presaVisione;
    return this;
  }

   /**
   * stato di presa visione dell&#39;esito
   * @return presaVisione
  **/

  public PresaVisioneEnum getPresaVisione() {
    return presaVisione;
  }

  public void setPresaVisione(PresaVisioneEnum presaVisione) {
    this.presaVisione = presaVisione;
  }

  public IscrizioneAppello userIdPresaVisione(String userIdPresaVisione) {
    this.userIdPresaVisione = userIdPresaVisione;
    return this;
  }

   /**
   * utente che ha effettuato l&#39;ultimo cambio di stato della presa visione
   * @return userIdPresaVisione
  **/

  public String getUserIdPresaVisione() {
    return userIdPresaVisione;
  }

  public void setUserIdPresaVisione(String userIdPresaVisione) {
    this.userIdPresaVisione = userIdPresaVisione;
  }

  public IscrizioneAppello userGrpPresaVisione(Long userGrpPresaVisione) {
    this.userGrpPresaVisione = userGrpPresaVisione;
    return this;
  }

   /**
   * gruppo dell&#39;utente che ha effettuato l&#39;ultimo cambio di stato della presa visione
   * @return userGrpPresaVisione
  **/

  public Long getUserGrpPresaVisione() {
    return userGrpPresaVisione;
  }

  public void setUserGrpPresaVisione(Long userGrpPresaVisione) {
    this.userGrpPresaVisione = userGrpPresaVisione;
  }

  public IscrizioneAppello dataRifEsito(String dataRifEsito) {
    this.dataRifEsito = dataRifEsito;
    return this;
  }

   /**
   * data di ultimo rifiuto applicata al docente (DD/MM/YYYY)
   * @return dataRifEsito
  **/

  public String getDataRifEsito() {
    return dataRifEsito;
  }

  public void setDataRifEsito(String dataRifEsito) {
    this.dataRifEsito = dataRifEsito;
  }

  public IscrizioneAppello dataRifEsitoStu(String dataRifEsitoStu) {
    this.dataRifEsitoStu = dataRifEsitoStu;
    return this;
  }

   /**
   * data di ultimo rifiuto applicata allo studente (DD/MM/YYYY)
   * @return dataRifEsitoStu
  **/

  public String getDataRifEsitoStu() {
    return dataRifEsitoStu;
  }

  public void setDataRifEsitoStu(String dataRifEsitoStu) {
    this.dataRifEsitoStu = dataRifEsitoStu;
  }

  public IscrizioneAppello notaPubbl(String notaPubbl) {
    this.notaPubbl = notaPubbl;
    return this;
  }

   /**
   * nota inserita dal docente durante la pubblicazione
   * @return notaPubbl
  **/

  public String getNotaPubbl() {
    return notaPubbl;
  }

  public void setNotaPubbl(String notaPubbl) {
    this.notaPubbl = notaPubbl;
  }

  public IscrizioneAppello gruppoVotoCod(String gruppoVotoCod) {
    this.gruppoVotoCod = gruppoVotoCod;
    return this;
  }

   /**
   * codice della scala voti del libretto dello studente
   * @return gruppoVotoCod
  **/

  public String getGruppoVotoCod() {
    return gruppoVotoCod;
  }

  public void setGruppoVotoCod(String gruppoVotoCod) {
    this.gruppoVotoCod = gruppoVotoCod;
  }

  public IscrizioneAppello gruppoVotoMaxPunti(Integer gruppoVotoMaxPunti) {
    this.gruppoVotoMaxPunti = gruppoVotoMaxPunti;
    return this;
  }

   /**
   * massimo voto disponibile per la scala voti
   * minimum: 0
   * @return gruppoVotoMaxPunti
  **/

  public Integer getGruppoVotoMaxPunti() {
    return gruppoVotoMaxPunti;
  }

  public void setGruppoVotoMaxPunti(Integer gruppoVotoMaxPunti) {
    this.gruppoVotoMaxPunti = gruppoVotoMaxPunti;
  }

  public IscrizioneAppello esito(IscrizioneAppelloEsito esito) {
    this.esito = esito;
    return this;
  }

   /**
   * Get esito
   * @return esito
  **/

  public IscrizioneAppelloEsito getEsito() {
    return esito;
  }

  public void setEsito(IscrizioneAppelloEsito esito) {
    this.esito = esito;
  }

  public IscrizioneAppello manualeFlg(Integer manualeFlg) {
    this.manualeFlg = manualeFlg;
    return this;
  }

   /**
   * flag che indica ci ha effettuato la prenotazione (es. attori SEG, DOC manuale_flg&#x3D;1; attore STU manuale_flg&#x3D;0)
   * minimum: 0
   * maximum: 1
   * @return manualeFlg
  **/

  public Integer getManualeFlg() {
    return manualeFlg;
  }

  public void setManualeFlg(Integer manualeFlg) {
    this.manualeFlg = manualeFlg;
  }

  public IscrizioneAppello dataEsa(String dataEsa) {
    this.dataEsa = dataEsa;
    return this;
  }

   /**
   * data di sostenimento della prova (DD/MM/YYYY), se non valorizzata vale la data del turno
   * @return dataEsa
  **/

  public String getDataEsa() {
    return dataEsa;
  }

  public void setDataEsa(String dataEsa) {
    this.dataEsa = dataEsa;
  }

  public IscrizioneAppello domandeEsame(String domandeEsame) {
    this.domandeEsame = domandeEsame;
    return this;
  }

   /**
   * domade d&#39;esame effettuate durante la prova
   * @return domandeEsame
  **/

  public String getDomandeEsame() {
    return domandeEsame;
  }

  public void setDomandeEsame(String domandeEsame) {
    this.domandeEsame = domandeEsame;
  }

  public IscrizioneAppello notaStudente(String notaStudente) {
    this.notaStudente = notaStudente;
    return this;
  }

   /**
   * Nota inserita dallo studente in fase di prenotazione appello
   * @return notaStudente
  **/

  public String getNotaStudente() {
    return notaStudente;
  }

  public void setNotaStudente(String notaStudente) {
    this.notaStudente = notaStudente;
  }

  public IscrizioneAppello tipoSvolgimentoEsameCod(String tipoSvolgimentoEsameCod) {
    this.tipoSvolgimentoEsameCod = tipoSvolgimentoEsameCod;
    return this;
  }

   /**
   * codice del tipo svolgimento esame dello studente
   * @return tipoSvolgimentoEsameCod
  **/

  public String getTipoSvolgimentoEsameCod() {
    return tipoSvolgimentoEsameCod;
  }

  public void setTipoSvolgimentoEsameCod(String tipoSvolgimentoEsameCod) {
    this.tipoSvolgimentoEsameCod = tipoSvolgimentoEsameCod;
  }

  public IscrizioneAppello tipoSvolgimentoEsameDes(String tipoSvolgimentoEsameDes) {
    this.tipoSvolgimentoEsameDes = tipoSvolgimentoEsameDes;
    return this;
  }

   /**
   * descrizione del tipo svolgimento esame dello studente
   * @return tipoSvolgimentoEsameDes
  **/

  public String getTipoSvolgimentoEsameDes() {
    return tipoSvolgimentoEsameDes;
  }

  public void setTipoSvolgimentoEsameDes(String tipoSvolgimentoEsameDes) {
    this.tipoSvolgimentoEsameDes = tipoSvolgimentoEsameDes;
  }

  public IscrizioneAppello tipoSvolgimentoEsameRichiestaFlg(String tipoSvolgimentoEsameRichiestaFlg) {
    this.tipoSvolgimentoEsameRichiestaFlg = tipoSvolgimentoEsameRichiestaFlg;
    return this;
  }

   /**
   * indica se il tipo di svolgimento esame è una richiesta dell&#39;utente, cioè va convertita in un valore definitivo
   * @return tipoSvolgimentoEsameRichiestaFlg
  **/

  public String getTipoSvolgimentoEsameRichiestaFlg() {
    return tipoSvolgimentoEsameRichiestaFlg;
  }

  public void setTipoSvolgimentoEsameRichiestaFlg(String tipoSvolgimentoEsameRichiestaFlg) {
    this.tipoSvolgimentoEsameRichiestaFlg = tipoSvolgimentoEsameRichiestaFlg;
  }

  public IscrizioneAppello tagCod(String tagCod) {
    this.tagCod = tagCod;
    return this;
  }

   /**
   * tag selezionato dallo studente in fase di prenotazione
   * @return tagCod
  **/

  public String getTagCod() {
    return tagCod;
  }

  public void setTagCod(String tagCod) {
    this.tagCod = tagCod;
  }

  public IscrizioneAppello autoTagCod(String autoTagCod) {
    this.autoTagCod = autoTagCod;
    return this;
  }

   /**
   * classe di prenotazione associata allo studente in fase di prenotazione
   * @return autoTagCod
  **/

  public String getAutoTagCod() {
    return autoTagCod;
  }

  public void setAutoTagCod(String autoTagCod) {
    this.autoTagCod = autoTagCod;
  }

  public IscrizioneAppello livUscitaCod(String livUscitaCod) {
    this.livUscitaCod = livUscitaCod;
    return this;
  }

   /**
   * livello di uscita della lingua se l&#39;appello prevede le lingue, da valorizzare assieme a linguaUscitaCod
   * @return livUscitaCod
  **/

  public String getLivUscitaCod() {
    return livUscitaCod;
  }

  public void setLivUscitaCod(String livUscitaCod) {
    this.livUscitaCod = livUscitaCod;
  }

  public IscrizioneAppello linguaUscitaCod(String linguaUscitaCod) {
    this.linguaUscitaCod = linguaUscitaCod;
    return this;
  }

   /**
   * codice ISO6392 della lingua a cui si riferisce il livello di uscita, da valorizzare assieme a livUscitaCod
   * @return linguaUscitaCod
  **/

  public String getLinguaUscitaCod() {
    return linguaUscitaCod;
  }

  public void setLinguaUscitaCod(String linguaUscitaCod) {
    this.linguaUscitaCod = linguaUscitaCod;
  }

  public IscrizioneAppello dataIns(String dataIns) {
    this.dataIns = dataIns;
    return this;
  }

   /**
   * data di prenotazione (DD/MM/YYYY HH24:MI:SS)
   * @return dataIns
  **/

  public String getDataIns() {
    return dataIns;
  }

  public void setDataIns(String dataIns) {
    this.dataIns = dataIns;
  }

  public IscrizioneAppello tipoDefAppCod(String tipoDefAppCod) {
    this.tipoDefAppCod = tipoDefAppCod;
    return this;
  }

   /**
   * codice che identifica il tipoDefApp dell&#39;appello collegato alla prenotazione
   * @return tipoDefAppCod
  **/

  public String getTipoDefAppCod() {
    return tipoDefAppCod;
  }

  public void setTipoDefAppCod(String tipoDefAppCod) {
    this.tipoDefAppCod = tipoDefAppCod;
  }

  public IscrizioneAppello tipoGestPrenCod(String tipoGestPrenCod) {
    this.tipoGestPrenCod = tipoGestPrenCod;
    return this;
  }

   /**
   * codice che identifica il tipoGestPren dell&#39;appello collegato alla prenotazione
   * @return tipoGestPrenCod
  **/

  public String getTipoGestPrenCod() {
    return tipoGestPrenCod;
  }

  public void setTipoGestPrenCod(String tipoGestPrenCod) {
    this.tipoGestPrenCod = tipoGestPrenCod;
  }

  public IscrizioneAppello tipoGestAppCod(String tipoGestAppCod) {
    this.tipoGestAppCod = tipoGestAppCod;
    return this;
  }

   /**
   * codice che identifica il tipoGestApp dell&#39;appello collegato alla prenotazione
   * @return tipoGestAppCod
  **/

  public String getTipoGestAppCod() {
    return tipoGestAppCod;
  }

  public void setTipoGestAppCod(String tipoGestAppCod) {
    this.tipoGestAppCod = tipoGestAppCod;
  }

  public IscrizioneAppello tipoAppCod(String tipoAppCod) {
    this.tipoAppCod = tipoAppCod;
    return this;
  }

   /**
   * tipo di appello (PF&#x3D;Prova Finale, PP&#x3D;Prova Parziale)
   * @return tipoAppCod
  **/

  public String getTipoAppCod() {
    return tipoAppCod;
  }

  public void setTipoAppCod(String tipoAppCod) {
    this.tipoAppCod = tipoAppCod;
  }

  public IscrizioneAppello posiz(Integer posiz) {
    this.posiz = posiz;
    return this;
  }

   /**
   * progressivo di prenotazione all&#39;interno della lista iscritti
   * @return posiz
  **/

  public Integer getPosiz() {
    return posiz;
  }

  public void setPosiz(Integer posiz) {
    this.posiz = posiz;
  }

  public IscrizioneAppello posizApp(Integer posizApp) {
    this.posizApp = posizApp;
    return this;
  }

   /**
   * progressivo di prenotazione all&#39;interno della lista iscritti in ordine di data_ins
   * @return posizApp
  **/

  public Integer getPosizApp() {
    return posizApp;
  }

  public void setPosizApp(Integer posizApp) {
    this.posizApp = posizApp;
  }

  public IscrizioneAppello dataInizioIscr(String dataInizioIscr) {
    this.dataInizioIscr = dataInizioIscr;
    return this;
  }

   /**
   * data inizio iscrizioni (DD/MM/YYYY)
   * @return dataInizioIscr
  **/

  public String getDataInizioIscr() {
    return dataInizioIscr;
  }

  public void setDataInizioIscr(String dataInizioIscr) {
    this.dataInizioIscr = dataInizioIscr;
  }

  public IscrizioneAppello dataFineIscr(String dataFineIscr) {
    this.dataFineIscr = dataFineIscr;
    return this;
  }

   /**
   * data fine iscrizioni (DD/MM/YYYY)
   * @return dataFineIscr
  **/

  public String getDataFineIscr() {
    return dataFineIscr;
  }

  public void setDataFineIscr(String dataFineIscr) {
    this.dataFineIscr = dataFineIscr;
  }

  public IscrizioneAppello tipoIscrCod(String tipoIscrCod) {
    this.tipoIscrCod = tipoIscrCod;
    return this;
  }

   /**
   * modalità di iscrizione definita nell&#39;appello, i possibili valori sono  ( Scritto&#x3D;S, Orale&#x3D;O, Scritto e Orale&#x3D;SO). 
   * @return tipoIscrCod
  **/

  public String getTipoIscrCod() {
    return tipoIscrCod;
  }

  public void setTipoIscrCod(String tipoIscrCod) {
    this.tipoIscrCod = tipoIscrCod;
  }

  public IscrizioneAppello tipoEsaCod(String tipoEsaCod) {
    this.tipoEsaCod = tipoEsaCod;
    return this;
  }

   /**
   * modalità di iscrizione definita nell&#39;appello, i possibili valori sono  ( Scritto&#x3D;S, Orale&#x3D;O, Scritto e Orale Separato&#x3D;SOS, Scritto e Orale Congiunto&#x3D;SOC). 
   * @return tipoEsaCod
  **/

  public String getTipoEsaCod() {
    return tipoEsaCod;
  }

  public void setTipoEsaCod(String tipoEsaCod) {
    this.tipoEsaCod = tipoEsaCod;
  }

  public IscrizioneAppello aaCalId(Integer aaCalId) {
    this.aaCalId = aaCalId;
    return this;
  }

   /**
   * Anno di calendario dell&#39;appello 
   * @return aaCalId
  **/

  public Integer getAaCalId() {
    return aaCalId;
  }

  public void setAaCalId(Integer aaCalId) {
    this.aaCalId = aaCalId;
  }

  public IscrizioneAppello aaSesId(Integer aaSesId) {
    this.aaSesId = aaSesId;
    return this;
  }

   /**
   * anno della sessione
   * @return aaSesId
  **/

  public Integer getAaSesId() {
    return aaSesId;
  }

  public void setAaSesId(Integer aaSesId) {
    this.aaSesId = aaSesId;
  }

  public IscrizioneAppello sesDes(String sesDes) {
    this.sesDes = sesDes;
    return this;
  }

   /**
   * descrizione della sessione
   * @return sesDes
  **/

  public String getSesDes() {
    return sesDes;
  }

  public void setSesDes(String sesDes) {
    this.sesDes = sesDes;
  }


  public IscrizioneAppello warnings(List<ErroriIscrizioneAppello> warnings) {
    this.warnings = warnings;
    return this;
  }

  public IscrizioneAppello addWarningsItem(ErroriIscrizioneAppello warningsItem) {
    if (this.warnings == null) {
      this.warnings = new ArrayList<ErroriIscrizioneAppello>();
    }
    this.warnings.add(warningsItem);
    return this;
  }

   /**
   * Get warnings
   * @return warnings
  **/

  public List<ErroriIscrizioneAppello> getWarnings() {
    return warnings;
  }

  public void setWarnings(List<ErroriIscrizioneAppello> warnings) {
    this.warnings = warnings;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IscrizioneAppello iscrizioneAppello = (IscrizioneAppello) o;
    return Objects.equals(this.applistaId, iscrizioneAppello.applistaId) &&
        Objects.equals(this.cdsId, iscrizioneAppello.cdsId) &&
        Objects.equals(this.adId, iscrizioneAppello.adId) &&
        Objects.equals(this.appId, iscrizioneAppello.appId) &&
        Objects.equals(this.appLogId, iscrizioneAppello.appLogId) &&
        Objects.equals(this.stuId, iscrizioneAppello.stuId) &&
        Objects.equals(this.adregId, iscrizioneAppello.adregId) &&
        Objects.equals(this.adsceId, iscrizioneAppello.adsceId) &&
        Objects.equals(this.matId, iscrizioneAppello.matId) &&
        Objects.equals(this.adStuCod, iscrizioneAppello.adStuCod) &&
        Objects.equals(this.adStuDes, iscrizioneAppello.adStuDes) &&
        Objects.equals(this.cdsAdStuCod, iscrizioneAppello.cdsAdStuCod) &&
        Objects.equals(this.cdsAdStuDes, iscrizioneAppello.cdsAdStuDes) &&
        Objects.equals(this.cdsAdIdStu, iscrizioneAppello.cdsAdIdStu) &&
        Objects.equals(this.desAppello, iscrizioneAppello.desAppello) &&
        Objects.equals(this.desTurno, iscrizioneAppello.desTurno) &&
        Objects.equals(this.dataOraTurno, iscrizioneAppello.dataOraTurno) &&
        Objects.equals(this.aaFreqId, iscrizioneAppello.aaFreqId) &&
        Objects.equals(this.statoAdsce, iscrizioneAppello.statoAdsce) &&
        Objects.equals(this.pesoAd, iscrizioneAppello.pesoAd) &&
        Objects.equals(this.userId, iscrizioneAppello.userId) &&
        Objects.equals(this.matricola, iscrizioneAppello.matricola) &&
        Objects.equals(this.nomeStudente, iscrizioneAppello.nomeStudente) &&
        Objects.equals(this.nomeAlias, iscrizioneAppello.nomeAlias) &&
        Objects.equals(this.cognomeStudente, iscrizioneAppello.cognomeStudente) &&
        Objects.equals(this.codFisStudente, iscrizioneAppello.codFisStudente) &&
        Objects.equals(this.dataNascitaStudente, iscrizioneAppello.dataNascitaStudente) &&
        Objects.equals(this.sessoStudente, iscrizioneAppello.sessoStudente) &&
        Objects.equals(this.comuNascCodIstat, iscrizioneAppello.comuNascCodIstat) &&
        Objects.equals(this.cittStraNasc, iscrizioneAppello.cittStraNasc) &&
        Objects.equals(this.cittCod, iscrizioneAppello.cittCod) &&
        Objects.equals(this.cdsStuCod, iscrizioneAppello.cdsStuCod) &&
        Objects.equals(this.cdsStuDes, iscrizioneAppello.cdsStuDes) &&
        Objects.equals(this.cdsIdStu, iscrizioneAppello.cdsIdStu) &&
        Objects.equals(this.aaOrdStuId, iscrizioneAppello.aaOrdStuId) &&
        Objects.equals(this.pdsStuCod, iscrizioneAppello.pdsStuCod) &&
        Objects.equals(this.pdsStuDes, iscrizioneAppello.pdsStuDes) &&
        Objects.equals(this.pdsIdStu, iscrizioneAppello.pdsIdStu) &&
        Objects.equals(this.pubblId, iscrizioneAppello.pubblId) &&
        Objects.equals(this.presaVisione, iscrizioneAppello.presaVisione) &&
        Objects.equals(this.userIdPresaVisione, iscrizioneAppello.userIdPresaVisione) &&
        Objects.equals(this.userGrpPresaVisione, iscrizioneAppello.userGrpPresaVisione) &&
        Objects.equals(this.dataRifEsito, iscrizioneAppello.dataRifEsito) &&
        Objects.equals(this.dataRifEsitoStu, iscrizioneAppello.dataRifEsitoStu) &&
        Objects.equals(this.notaPubbl, iscrizioneAppello.notaPubbl) &&
        Objects.equals(this.gruppoVotoCod, iscrizioneAppello.gruppoVotoCod) &&
        Objects.equals(this.gruppoVotoMaxPunti, iscrizioneAppello.gruppoVotoMaxPunti) &&
        Objects.equals(this.esito, iscrizioneAppello.esito) &&
        Objects.equals(this.manualeFlg, iscrizioneAppello.manualeFlg) &&
        Objects.equals(this.dataEsa, iscrizioneAppello.dataEsa) &&
        Objects.equals(this.domandeEsame, iscrizioneAppello.domandeEsame) &&
        Objects.equals(this.notaStudente, iscrizioneAppello.notaStudente) &&
        Objects.equals(this.tipoSvolgimentoEsameCod, iscrizioneAppello.tipoSvolgimentoEsameCod) &&
        Objects.equals(this.tipoSvolgimentoEsameDes, iscrizioneAppello.tipoSvolgimentoEsameDes) &&
        Objects.equals(this.tipoSvolgimentoEsameRichiestaFlg, iscrizioneAppello.tipoSvolgimentoEsameRichiestaFlg) &&
        Objects.equals(this.tagCod, iscrizioneAppello.tagCod) &&
        Objects.equals(this.autoTagCod, iscrizioneAppello.autoTagCod) &&
        Objects.equals(this.livUscitaCod, iscrizioneAppello.livUscitaCod) &&
        Objects.equals(this.linguaUscitaCod, iscrizioneAppello.linguaUscitaCod) &&
        Objects.equals(this.dataIns, iscrizioneAppello.dataIns) &&
        Objects.equals(this.tipoDefAppCod, iscrizioneAppello.tipoDefAppCod) &&
        Objects.equals(this.tipoGestPrenCod, iscrizioneAppello.tipoGestPrenCod) &&
        Objects.equals(this.tipoGestAppCod, iscrizioneAppello.tipoGestAppCod) &&
        Objects.equals(this.tipoAppCod, iscrizioneAppello.tipoAppCod) &&
        Objects.equals(this.posiz, iscrizioneAppello.posiz) &&
        Objects.equals(this.posizApp, iscrizioneAppello.posizApp) &&
        Objects.equals(this.dataInizioIscr, iscrizioneAppello.dataInizioIscr) &&
        Objects.equals(this.dataFineIscr, iscrizioneAppello.dataFineIscr) &&
        Objects.equals(this.tipoIscrCod, iscrizioneAppello.tipoIscrCod) &&
        Objects.equals(this.tipoEsaCod, iscrizioneAppello.tipoEsaCod) &&
        Objects.equals(this.aaCalId, iscrizioneAppello.aaCalId) &&
        Objects.equals(this.aaSesId, iscrizioneAppello.aaSesId) &&
        Objects.equals(this.sesDes, iscrizioneAppello.sesDes) &&
        Objects.equals(this.warnings, iscrizioneAppello.warnings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applistaId, cdsId, adId, appId, appLogId, stuId, adregId, adsceId, matId, adStuCod, adStuDes, cdsAdStuCod, cdsAdStuDes, cdsAdIdStu, desAppello, desTurno, dataOraTurno, aaFreqId, statoAdsce, pesoAd, userId, matricola, nomeStudente, nomeAlias, cognomeStudente, codFisStudente, dataNascitaStudente, sessoStudente, comuNascCodIstat, cittStraNasc, cittCod, cdsStuCod, cdsStuDes, cdsIdStu, aaOrdStuId, pdsStuCod, pdsStuDes, pdsIdStu, pubblId, presaVisione, userIdPresaVisione, userGrpPresaVisione, dataRifEsito, dataRifEsitoStu, notaPubbl, gruppoVotoCod, gruppoVotoMaxPunti, esito, manualeFlg, dataEsa, domandeEsame, notaStudente, tipoSvolgimentoEsameCod, tipoSvolgimentoEsameDes, tipoSvolgimentoEsameRichiestaFlg, tagCod, autoTagCod, livUscitaCod, linguaUscitaCod, dataIns, tipoDefAppCod, tipoGestPrenCod, tipoGestAppCod, tipoAppCod, posiz, posizApp, dataInizioIscr, dataFineIscr, tipoIscrCod, tipoEsaCod, aaCalId, aaSesId, sesDes, warnings);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IscrizioneAppello {\n");
    
    sb.append("    applistaId: ").append(toIndentedString(applistaId)).append("\n");
    sb.append("    cdsId: ").append(toIndentedString(cdsId)).append("\n");
    sb.append("    adId: ").append(toIndentedString(adId)).append("\n");
    sb.append("    appId: ").append(toIndentedString(appId)).append("\n");
    sb.append("    appLogId: ").append(toIndentedString(appLogId)).append("\n");
    sb.append("    stuId: ").append(toIndentedString(stuId)).append("\n");
    sb.append("    adregId: ").append(toIndentedString(adregId)).append("\n");
    sb.append("    adsceId: ").append(toIndentedString(adsceId)).append("\n");
    sb.append("    matId: ").append(toIndentedString(matId)).append("\n");
    sb.append("    adStuCod: ").append(toIndentedString(adStuCod)).append("\n");
    sb.append("    adStuDes: ").append(toIndentedString(adStuDes)).append("\n");
    sb.append("    cdsAdStuCod: ").append(toIndentedString(cdsAdStuCod)).append("\n");
    sb.append("    cdsAdStuDes: ").append(toIndentedString(cdsAdStuDes)).append("\n");
    sb.append("    cdsAdIdStu: ").append(toIndentedString(cdsAdIdStu)).append("\n");
    sb.append("    desAppello: ").append(toIndentedString(desAppello)).append("\n");
    sb.append("    desTurno: ").append(toIndentedString(desTurno)).append("\n");
    sb.append("    dataOraTurno: ").append(toIndentedString(dataOraTurno)).append("\n");
    sb.append("    aaFreqId: ").append(toIndentedString(aaFreqId)).append("\n");
    sb.append("    statoAdsce: ").append(toIndentedString(statoAdsce)).append("\n");
    sb.append("    pesoAd: ").append(toIndentedString(pesoAd)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    matricola: ").append(toIndentedString(matricola)).append("\n");
    sb.append("    nomeStudente: ").append(toIndentedString(nomeStudente)).append("\n");
    sb.append("    nomeAlias: ").append(toIndentedString(nomeAlias)).append("\n");
    sb.append("    cognomeStudente: ").append(toIndentedString(cognomeStudente)).append("\n");
    sb.append("    codFisStudente: ").append(toIndentedString(codFisStudente)).append("\n");
    sb.append("    dataNascitaStudente: ").append(toIndentedString(dataNascitaStudente)).append("\n");
    sb.append("    sessoStudente: ").append(toIndentedString(sessoStudente)).append("\n");
    sb.append("    comuNascCodIstat: ").append(toIndentedString(comuNascCodIstat)).append("\n");
    sb.append("    cittStraNasc: ").append(toIndentedString(cittStraNasc)).append("\n");
    sb.append("    cittCod: ").append(toIndentedString(cittCod)).append("\n");
    sb.append("    cdsStuCod: ").append(toIndentedString(cdsStuCod)).append("\n");
    sb.append("    cdsStuDes: ").append(toIndentedString(cdsStuDes)).append("\n");
    sb.append("    cdsIdStu: ").append(toIndentedString(cdsIdStu)).append("\n");
    sb.append("    aaOrdStuId: ").append(toIndentedString(aaOrdStuId)).append("\n");
    sb.append("    pdsStuCod: ").append(toIndentedString(pdsStuCod)).append("\n");
    sb.append("    pdsStuDes: ").append(toIndentedString(pdsStuDes)).append("\n");
    sb.append("    pdsIdStu: ").append(toIndentedString(pdsIdStu)).append("\n");
    sb.append("    pubblId: ").append(toIndentedString(pubblId)).append("\n");
    sb.append("    presaVisione: ").append(toIndentedString(presaVisione)).append("\n");
    sb.append("    userIdPresaVisione: ").append(toIndentedString(userIdPresaVisione)).append("\n");
    sb.append("    userGrpPresaVisione: ").append(toIndentedString(userGrpPresaVisione)).append("\n");
    sb.append("    dataRifEsito: ").append(toIndentedString(dataRifEsito)).append("\n");
    sb.append("    dataRifEsitoStu: ").append(toIndentedString(dataRifEsitoStu)).append("\n");
    sb.append("    notaPubbl: ").append(toIndentedString(notaPubbl)).append("\n");
    sb.append("    gruppoVotoCod: ").append(toIndentedString(gruppoVotoCod)).append("\n");
    sb.append("    gruppoVotoMaxPunti: ").append(toIndentedString(gruppoVotoMaxPunti)).append("\n");
    sb.append("    esito: ").append(toIndentedString(esito)).append("\n");
    sb.append("    manualeFlg: ").append(toIndentedString(manualeFlg)).append("\n");
    sb.append("    dataEsa: ").append(toIndentedString(dataEsa)).append("\n");
    sb.append("    domandeEsame: ").append(toIndentedString(domandeEsame)).append("\n");
    sb.append("    notaStudente: ").append(toIndentedString(notaStudente)).append("\n");
    sb.append("    tipoSvolgimentoEsameCod: ").append(toIndentedString(tipoSvolgimentoEsameCod)).append("\n");
    sb.append("    tipoSvolgimentoEsameDes: ").append(toIndentedString(tipoSvolgimentoEsameDes)).append("\n");
    sb.append("    tipoSvolgimentoEsameRichiestaFlg: ").append(toIndentedString(tipoSvolgimentoEsameRichiestaFlg)).append("\n");
    sb.append("    tagCod: ").append(toIndentedString(tagCod)).append("\n");
    sb.append("    autoTagCod: ").append(toIndentedString(autoTagCod)).append("\n");
    sb.append("    livUscitaCod: ").append(toIndentedString(livUscitaCod)).append("\n");
    sb.append("    linguaUscitaCod: ").append(toIndentedString(linguaUscitaCod)).append("\n");
    sb.append("    dataIns: ").append(toIndentedString(dataIns)).append("\n");
    sb.append("    tipoDefAppCod: ").append(toIndentedString(tipoDefAppCod)).append("\n");
    sb.append("    tipoGestPrenCod: ").append(toIndentedString(tipoGestPrenCod)).append("\n");
    sb.append("    tipoGestAppCod: ").append(toIndentedString(tipoGestAppCod)).append("\n");
    sb.append("    tipoAppCod: ").append(toIndentedString(tipoAppCod)).append("\n");
    sb.append("    posiz: ").append(toIndentedString(posiz)).append("\n");
    sb.append("    posizApp: ").append(toIndentedString(posizApp)).append("\n");
    sb.append("    dataInizioIscr: ").append(toIndentedString(dataInizioIscr)).append("\n");
    sb.append("    dataFineIscr: ").append(toIndentedString(dataFineIscr)).append("\n");
    sb.append("    tipoIscrCod: ").append(toIndentedString(tipoIscrCod)).append("\n");
    sb.append("    tipoEsaCod: ").append(toIndentedString(tipoEsaCod)).append("\n");
    sb.append("    aaCalId: ").append(toIndentedString(aaCalId)).append("\n");
    sb.append("    aaSesId: ").append(toIndentedString(aaSesId)).append("\n");
    sb.append("    sesDes: ").append(toIndentedString(sesDes)).append("\n");
    sb.append("    warnings: ").append(toIndentedString(warnings)).append("\n");
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

