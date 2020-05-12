package co.edu.usbcali.pqrs.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.*;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@Entity
@Table(name = "tipo_pqrs", schema = "public")
public class TipoPqrs implements java.io.Serializable {
  @NotNull private String tpqrsId;
  private String estado;
  private Date fechaCreacion;
  private Date fechaModificacion;
  private String nombre;
  private String usuCreador;
  private String usuModificador;
  private List<Pqrs> pqrses = new ArrayList<Pqrs>(0);

  public TipoPqrs() {}

  public TipoPqrs(
      String tpqrsId,
      String estado,
      Date fechaCreacion,
      Date fechaModificacion,
      String nombre,
      List<Pqrs> pqrses,
      String usuCreador,
      String usuModificador) {
    this.tpqrsId = tpqrsId;
    this.estado = estado;
    this.fechaCreacion = fechaCreacion;
    this.fechaModificacion = fechaModificacion;
    this.nombre = nombre;
    this.usuCreador = usuCreador;
    this.usuModificador = usuModificador;
    this.pqrses = pqrses;
  }

  @Id
  @Column(name = "tpqrs_id", unique = true, nullable = false)
  public String getTpqrsId() {
    return this.tpqrsId;
  }

  public void setTpqrsId(String tpqrsId) {
    this.tpqrsId = tpqrsId;
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

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoPqrs")
  public List<Pqrs> getPqrses() {
    return this.pqrses;
  }

  public void setPqrses(List<Pqrs> pqrses) {
    this.pqrses = pqrses;
  }
}
