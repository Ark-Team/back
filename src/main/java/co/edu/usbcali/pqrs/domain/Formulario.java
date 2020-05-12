package co.edu.usbcali.pqrs.domain;

import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.*;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@Entity
@Table(name = "formulario", schema = "public")
public class Formulario implements java.io.Serializable {
  @NotNull private String formId;
  @NotNull private Compania compania;
  private String estado;
  private Date fechaCreacion;
  private Date fechaModificacion;
  private String nombre;
  private String usuCreador;
  private String usuModificador;
  private List<Pqrs> pqrses = new ArrayList<Pqrs>(0);

  public Formulario() {}

  public Formulario(
      String formId,
      Compania compania,
      String estado,
      Date fechaCreacion,
      Date fechaModificacion,
      String nombre,
      List<Pqrs> pqrses,
      String usuCreador,
      String usuModificador) {
    this.formId = formId;
    this.compania = compania;
    this.estado = estado;
    this.fechaCreacion = fechaCreacion;
    this.fechaModificacion = fechaModificacion;
    this.nombre = nombre;
    this.usuCreador = usuCreador;
    this.usuModificador = usuModificador;
    this.pqrses = pqrses;
  }

  @Id
  @GeneratedValue(generator = "objectId")
  @GenericGenerator(name = "objectId", strategy = "co.edu.usbcali.pqrs.utility.ObjectIdGenerator")
  @Column(name = "form_id", unique = true, nullable = false)
  public String getFormId() {
    return this.formId;
  }

  public void setFormId(String formId) {
    this.formId = formId;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "comp_id")
  public Compania getCompania() {
    return this.compania;
  }

  public void setCompania(Compania compania) {
    this.compania = compania;
  }

  @Column(name = "estado")
  public String getEstado() {
    return this.estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  @Column(name = "fecha_creacion")
  public Date getFechaCreacion() {
    return this.fechaCreacion;
  }

  public void setFechaCreacion(Date fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  @Column(name = "fecha_modificacion")
  public Date getFechaModificacion() {
    return this.fechaModificacion;
  }

  public void setFechaModificacion(Date fechaModificacion) {
    this.fechaModificacion = fechaModificacion;
  }

  @Column(name = "nombre")
  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Column(name = "usu_creador")
  public String getUsuCreador() {
    return this.usuCreador;
  }

  public void setUsuCreador(String usuCreador) {
    this.usuCreador = usuCreador;
  }

  @Column(name = "usu_modificador")
  public String getUsuModificador() {
    return this.usuModificador;
  }

  public void setUsuModificador(String usuModificador) {
    this.usuModificador = usuModificador;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "formulario")
  public List<Pqrs> getPqrses() {
    return this.pqrses;
  }

  public void setPqrses(List<Pqrs> pqrses) {
    this.pqrses = pqrses;
  }
}
