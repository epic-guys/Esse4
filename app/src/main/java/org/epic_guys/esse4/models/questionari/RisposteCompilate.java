/*
 * ESSE3 Questionari API
 * API REST di ESSE3 per l'accesso ai questionari
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package org.epic_guys.esse4.models.questionari;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;

/**
 * RisposteCompilate
 */
public class RisposteCompilate {
  @SerializedName("domandaId")
  private Integer domandaId = null;

  @SerializedName("quesitoId")
  private Integer quesitoId = null;

  @SerializedName("rispostaCompilataId")
  private Integer rispostaCompilataId = null;

  @SerializedName("testoLibero")
  private String testoLibero = null;

  @SerializedName("rispostaDominio")
  private DominiRisposte rispostaDominio = null;

  public RisposteCompilate domandaId(Integer domandaId) {
    this.domandaId = domandaId;
    return this;
  }

   /**
   * Get domandaId
   * @return domandaId
  **/

  public Integer getDomandaId() {
    return domandaId;
  }

  public void setDomandaId(Integer domandaId) {
    this.domandaId = domandaId;
  }

  public RisposteCompilate quesitoId(Integer quesitoId) {
    this.quesitoId = quesitoId;
    return this;
  }

   /**
   * Get quesitoId
   * @return quesitoId
  **/

  public Integer getQuesitoId() {
    return quesitoId;
  }

  public void setQuesitoId(Integer quesitoId) {
    this.quesitoId = quesitoId;
  }

  public RisposteCompilate rispostaCompilataId(Integer rispostaCompilataId) {
    this.rispostaCompilataId = rispostaCompilataId;
    return this;
  }

   /**
   * Get rispostaCompilataId
   * @return rispostaCompilataId
  **/

  public Integer getRispostaCompilataId() {
    return rispostaCompilataId;
  }

  public void setRispostaCompilataId(Integer rispostaCompilataId) {
    this.rispostaCompilataId = rispostaCompilataId;
  }

  public RisposteCompilate testoLibero(String testoLibero) {
    this.testoLibero = testoLibero;
    return this;
  }

   /**
   * Get testoLibero
   * @return testoLibero
  **/

  public String getTestoLibero() {
    return testoLibero;
  }

  public void setTestoLibero(String testoLibero) {
    this.testoLibero = testoLibero;
  }

  public RisposteCompilate rispostaDominio(DominiRisposte rispostaDominio) {
    this.rispostaDominio = rispostaDominio;
    return this;
  }

   /**
   * Get rispostaDominio
   * @return rispostaDominio
  **/

  public DominiRisposte getRispostaDominio() {
    return rispostaDominio;
  }

  public void setRispostaDominio(DominiRisposte rispostaDominio) {
    this.rispostaDominio = rispostaDominio;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RisposteCompilate risposteCompilate = (RisposteCompilate) o;
    return Objects.equals(this.domandaId, risposteCompilate.domandaId) &&
        Objects.equals(this.quesitoId, risposteCompilate.quesitoId) &&
        Objects.equals(this.rispostaCompilataId, risposteCompilate.rispostaCompilataId) &&
        Objects.equals(this.testoLibero, risposteCompilate.testoLibero) &&
        Objects.equals(this.rispostaDominio, risposteCompilate.rispostaDominio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(domandaId, quesitoId, rispostaCompilataId, testoLibero, rispostaDominio);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RisposteCompilate {\n");
    
    sb.append("    domandaId: ").append(toIndentedString(domandaId)).append("\n");
    sb.append("    quesitoId: ").append(toIndentedString(quesitoId)).append("\n");
    sb.append("    rispostaCompilataId: ").append(toIndentedString(rispostaCompilataId)).append("\n");
    sb.append("    testoLibero: ").append(toIndentedString(testoLibero)).append("\n");
    sb.append("    rispostaDominio: ").append(toIndentedString(rispostaDominio)).append("\n");
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

