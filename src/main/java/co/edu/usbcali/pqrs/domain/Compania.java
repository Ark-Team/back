package co.edu.usbcali.pqrs.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.*;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@Entity
@Table(name = "compania", schema = "public")
public class Compania implements java.io.Serializable {
  @NotNull
  @Min(0)
  private String compId;

  @NotNull
  @Size(min = 4, max = 50)
  private String direccion;

  @NotNull @Email private String email;
  private String estado;
  private Date fechaCreacion;
  private Date fechaModificacion;

  @NotNull
  @Size(min = 3, max = 100)
  private String nombre;

  private Long telefono;
  private String usuCreador;
  private String usuModificador;
  private List<Area> areas = new ArrayList<Area>(0);
  private List<AtributoFormulario> atributoFormularios = new ArrayList<AtributoFormulario>(0);
  private List<Formulario> formularios = new ArrayList<Formulario>(0);
  private List<Pqrs> pqrses = new ArrayList<Pqrs>(0);
  private List<Usuario> usuarios = new ArrayList<Usuario>(0);

  public Compania() {}

  public Compania(
      String compId,
      List<Area> areas,
      List<AtributoFormulario> atributoFormularios,
      String direccion,
      String email,
      String estado,
      Date fechaCreacion,
      Date fechaModificacion,
      List<Formulario> formularios,
      String nombre,
      List<Pqrs> pqrses,
      Long telefono,
      String usuCreador,
      String usuModificador,
      List<Usuario> usuarios) {
    this.compId = compId;
    this.direccion = direccion;
    this.email = email;
    this.estado = estado;
    this.fechaCreacion = fechaCreacion;
    this.fechaModificacion = fechaModificacion;
    this.nombre = nombre;
    this.telefono = telefono;
    this.usuCreador = usuCreador;
    this.usuModificador = usuModificador;
    this.areas = areas;
    this.atributoFormularios = atributoFormularios;
    this.formularios = formularios;
    this.pqrses = pqrses;
    this.usuarios = usuarios;
  }

  @Id
  @Column(name = "comp_id", unique = true, nullable = false)
  public String getCompId() {
    return this.compId;
  }

  public void setCompId(String compId) {
    this.compId = compId;
  }

  @Column(name = "direccion")
  public String getDireccion() {
    return this.direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  @Column(name = "email")
  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  @Column(name = "telefono")
  public Long getTelefono() {
    return this.telefono;
  }

  public void setTelefono(Long telefono) {
    this.telefono = telefono;
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

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "compania")
  public List<Area> getAreas() {
    return this.areas;
  }

  public void setAreas(List<Area> areas) {
    this.areas = areas;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "compania")
  public List<AtributoFormulario> getAtributoFormularios() {
    return this.atributoFormularios;
  }

  public void setAtributoFormularios(List<AtributoFormulario> atributoFormularios) {
    this.atributoFormularios = atributoFormularios;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "compania")
  public List<Formulario> getFormularios() {
    return this.formularios;
  }

  public void setFormularios(List<Formulario> formularios) {
    this.formularios = formularios;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "compania")
  public List<Pqrs> getPqrses() {
    return this.pqrses;
  }

  public void setPqrses(List<Pqrs> pqrses) {
    this.pqrses = pqrses;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "compania")
  public List<Usuario> getUsuarios() {
    return this.usuarios;
  }

  public void setUsuarios(List<Usuario> usuarios) {
    this.usuarios = usuarios;
  }
}
