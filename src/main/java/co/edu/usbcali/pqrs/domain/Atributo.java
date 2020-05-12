package co.edu.usbcali.pqrs.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.*;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@Entity
@Table(name = "atributo", schema = "public")
public class Atributo implements java.io.Serializable {
  @NotNull private String atriId;
  private String descripcion;
  private String estado;
  private Date fechaCreacion;
  private Date fechaModificacion;
  private String nombre;
  private String tipo;
  private String usuCreador;
  private String usuModificador;
  private List<AtributoFormulario> atributoFormularios = new ArrayList<AtributoFormulario>(0);

  public Atributo() {}

  public Atributo(
      String atriId,
      List<AtributoFormulario> atributoFormularios,
      String descripcion,
      String estado,
      Date fechaCreacion,
      Date fechaModificacion,
      String nombre,
      String tipo,
      String usuCreador,
      String usuModificador) {
    this.atriId = atriId;
    this.descripcion = descripcion;
    this.estado = estado;
    this.fechaCreacion = fechaCreacion;
    this.fechaModificacion = fechaModificacion;
    this.nombre = nombre;
    this.tipo = tipo;
    this.usuCreador = usuCreador;
    this.usuModificador = usuModificador;
    this.atributoFormularios = atributoFormularios;
  }

  @Id
  @Column(name = "atri_id", unique = true, nullable = false)
  public String getAtriId() {
    return this.atriId;
  }

  public void setAtriId(String atriId) {
    this.atriId = atriId;
  }

  @Column(name = "descripcion")
  public String getDescripcion() {
    return this.descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
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

  @Column(name = "tipo")
  public String getTipo() {
    return this.tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
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

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "atributo")
  public List<AtributoFormulario> getAtributoFormularios() {
    return this.atributoFormularios;
  }

  public void setAtributoFormularios(List<AtributoFormulario> atributoFormularios) {
    this.atributoFormularios = atributoFormularios;
  }
}
