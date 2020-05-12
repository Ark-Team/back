package co.edu.usbcali.pqrs.domain;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.*;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@Entity
@Table(name = "atributo_formulario", schema = "public")
public class AtributoFormulario implements java.io.Serializable {
  private String atrformId;
  private Atributo atributo;
  private Compania compania;

  public AtributoFormulario() {}

  public AtributoFormulario(String atrformId, Atributo atributo, Compania compania) {
    this.atrformId = atrformId;
    this.atributo = atributo;
    this.compania = compania;
  }

  @Id
  @GeneratedValue(generator = "objectId")
  @GenericGenerator(name = "objectId", strategy = "co.edu.usbcali.pqrs.utility.ObjectIdGenerator")
  @Column(name = "atrform_id", unique = true, nullable = false)
  public String getAtrformId() {
    return this.atrformId;
  }

  public void setAtrformId(String atrformId) {
    this.atrformId = atrformId;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "atri_id")
  public Atributo getAtributo() {
    return this.atributo;
  }

  public void setAtributo(Atributo atributo) {
    this.atributo = atributo;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "comp_id")
  public Compania getCompania() {
    return this.compania;
  }

  public void setCompania(Compania compania) {
    this.compania = compania;
  }
}
