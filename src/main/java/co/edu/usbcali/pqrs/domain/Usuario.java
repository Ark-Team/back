package co.edu.usbcali.pqrs.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.*;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@Entity
@Table(name = "usuario", schema = "public")
public class Usuario implements java.io.Serializable {
  @NotNull private String usuId;
  @NotNull private Compania compania;
  @NotNull private TipoUsuario tipoUsuario;
  private String clave;
  private String email;
  private String estado;
  private Date fechaCreacion;
  private Date fechaModificacion;
  private Long identificacion;
  private String nombre;
  private Long telefono;
  private String usuCreador;
  private String usuModificador;
  private List<UsuarioArea> usuarioAreas = new ArrayList<UsuarioArea>(0);

  public Usuario() {}

  public Usuario(
      String usuId,
      String clave,
      Compania compania,
      String email,
      String estado,
      Date fechaCreacion,
      Date fechaModificacion,
      Long identificacion,
      String nombre,
      Long telefono,
      TipoUsuario tipoUsuario,
      String usuCreador,
      String usuModificador,
      List<UsuarioArea> usuarioAreas) {
    this.usuId = usuId;
    this.compania = compania;
    this.tipoUsuario = tipoUsuario;
    this.clave = clave;
    this.email = email;
    this.estado = estado;
    this.fechaCreacion = fechaCreacion;
    this.fechaModificacion = fechaModificacion;
    this.identificacion = identificacion;
    this.nombre = nombre;
    this.telefono = telefono;
    this.usuCreador = usuCreador;
    this.usuModificador = usuModificador;
    this.usuarioAreas = usuarioAreas;
  }

  @Id
  @Column(name = "usu_id", unique = true, nullable = false)
  public String getUsuId() {
    return this.usuId;
  }

  public void setUsuId(String usuId) {
    this.usuId = usuId;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "comp_id")
  public Compania getCompania() {
    return this.compania;
  }

  public void setCompania(Compania compania) {
    this.compania = compania;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tusu_id")
  public TipoUsuario getTipoUsuario() {
    return this.tipoUsuario;
  }

  public void setTipoUsuario(TipoUsuario tipoUsuario) {
    this.tipoUsuario = tipoUsuario;
  }

  @Column(name = "clave")
  public String getClave() {
    return this.clave;
  }

  public void setClave(String clave) {
    this.clave = clave;
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

  @Column(name = "identificacion")
  public Long getIdentificacion() {
    return this.identificacion;
  }

  public void setIdentificacion(Long identificacion) {
    this.identificacion = identificacion;
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

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
  public List<UsuarioArea> getUsuarioAreas() {
    return this.usuarioAreas;
  }

  public void setUsuarioAreas(List<UsuarioArea> usuarioAreas) {
    this.usuarioAreas = usuarioAreas;
  }
}
