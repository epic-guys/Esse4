package org.epic_guys.esse4.models;

public class Carriera implements ApiResource {
    public static final int FULL_TIME = 0;
    public static final int PART_TIME = 1;

    private long persId;
    private String cognome;
    private String nome;
    private String dataNascita;
    private String sesso;
    private String userId;
    private String codFis;
    private String email;
    private String emailAte;
    private String staStuCod;
    private String motStastuCod;
    private int aaId;
    private String dataImm;
    private String statiStuDes;
    private String motStastuDes;
    private String numProtocollo;
    private String dataIns;
    private String dataMod;
    private String domCtStato;
    private String statiDomCtDes;
    private String aaDes;
    private int aaIscrId;
    private String matricola;
    private int sedeId;
    private String sediDes;
    private int annoCorso;
    private String lingue;
    private String dataIscr;
    private String settCod;
    private String settDes;
    private String areaCod;
    private String areaDes;
    private String areaCodStatMiur;
    private String sdrCod;
    private String sdrDes;
    private int sdrCsaCod;
    private String facCod;
    private String facDes;
    private String facCsaCod;
    private int idAb;
    private String extStuCod;
    private int attlauFlg;
    private String dataAttlau;
    private int tipoCatAmmId;
    private String tipoCatAmmDes;
    private String profstuCod;
    private String profstuDes;
    private String staMatCod;
    private String motStamatCod;
    private String tipoIscrCod;
    private int ptFlg;
    private int sospFlg;
    private String p06CdsCod;
    private String p06CdsDes;
    private long matId;
    private long aaOrdId;
    private long pdsId;
    private long iscrId;
    private long cdsId;
    private long stuId;

    public long getPersId() {
        return persId;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public String getSesso() {
        return sesso;
    }

    public String getUserId() {
        return userId;
    }

    public String getCodFis() {
        return codFis;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailAte() {
        return emailAte;
    }

    public String getStaStuCod() {
        return staStuCod;
    }

    public String getMotStastuCod() {
        return motStastuCod;
    }

    public int getAaId() {
        return aaId;
    }

    public String getDataImm() {
        return dataImm;
    }

    public String getStatiStuDes() {
        return statiStuDes;
    }

    public String getMotStastuDes() {
        return motStastuDes;
    }

    public String getNumProtocollo() {
        return numProtocollo;
    }

    public String getDataIns() {
        return dataIns;
    }

    public String getDataMod() {
        return dataMod;
    }

    public String getDomCtStato() {
        return domCtStato;
    }

    public String getStatiDomCtDes() {
        return statiDomCtDes;
    }

    public String getAaDes() {
        return aaDes;
    }

    public int getAaIscrId() {
        return aaIscrId;
    }

    public String getMatricola() {
        return matricola;
    }

    public int getSedeId() {
        return sedeId;
    }

    public String getSediDes() {
        return sediDes;
    }

    public int getAnnoCorso() {
        return annoCorso;
    }

    public String getLingue() {
        return lingue;
    }

    public String getDataIscr() {
        return dataIscr;
    }

    public String getSettCod() {
        return settCod;
    }

    public String getSettDes() {
        return settDes;
    }

    public String getAreaCod() {
        return areaCod;
    }

    public String getAreaDes() {
        return areaDes;
    }

    public String getAreaCodStatMiur() {
        return areaCodStatMiur;
    }

    public String getSdrCod() {
        return sdrCod;
    }

    public String getSdrDes() {
        return sdrDes;
    }

    public int getSdrCsaCod() {
        return sdrCsaCod;
    }

    public String getFacCod() {
        return facCod;
    }

    public String getFacDes() {
        return facDes;
    }

    public String getFacCsaCod() {
        return facCsaCod;
    }

    public int getIdAb() {
        return idAb;
    }

    public String getExtStuCod() {
        return extStuCod;
    }

    public int getAttlauFlg() {
        return attlauFlg;
    }

    public String getDataAttlau() {
        return dataAttlau;
    }

    public int getTipoCatAmmId() {
        return tipoCatAmmId;
    }

    public String getTipoCatAmmDes() {
        return tipoCatAmmDes;
    }

    public String getProfstuCod() {
        return profstuCod;
    }

    public String getProfstuDes() {
        return profstuDes;
    }

    public String getStaMatCod() {
        return staMatCod;
    }

    public String getMotStamatCod() {
        return motStamatCod;
    }

    public String getTipoIscrCod() {
        return tipoIscrCod;
    }

    public int getPartTimeFlag() {
        return ptFlg;
    }

    public boolean isPartTime() {
        return ptFlg == PART_TIME;
    }

    public int getSospFlg() {
        return sospFlg;
    }

    public String getCodiceCorsoDiLaurea() {
        return p06CdsCod;
    }

    public String getDescrizioneCorsoDiLaurea() {
        return p06CdsDes;
    }

    public long getIdCarriera() {
        return matId;
    }

    public long getAaOrdId() {
        return aaOrdId;
    }

    public long getPdsId() {
        return pdsId;
    }

    public long getIscrId() {
        return iscrId;
    }

    public long getCdsId() {
        return cdsId;
    }

    public long getIdStudente() {
        return stuId;
    }
}
