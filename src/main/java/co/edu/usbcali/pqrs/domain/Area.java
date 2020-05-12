package co.edu.usbcali.pqrs.domain;

import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.*;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@Entity
@Table(name = "area", schema = "public")
public class Area implements java.io.Serializable {
  @NotNull private String areaId;
  @NotNull private Compania compania;
  private String estado;
  private Date fechaCreacion;
  private Date fechaModificacion;

  @NotNull
  @Size(min = 3, max = 100)
  private String nombre;

  private String usuCreador;
  private String usuModificador;
  private List<Pqrs> pqrses = new ArrayList<Pqrs>(0);
  private List<Proceso> procesos = new ArrayList<Proceso>(0);
  private List<UsuarioArea> usuarioAreas = new ArrayList<UsuarioArea>(0);

  public Area() {}

  public Area(
      String areaId,
      Compania compania,
      String estado,
      Date fechaCreacion,
      Date fechaModificacion,
      String nombre,
      List<Pqrs> pqrses,
      List<Proceso> procesos,
      String usuCreador,
      String usuModificador,
      List<UsuarioArea> usuarioAreas) {
    this.areaId = areaId;
    this.compania = compania;
    this.estado = estado;
    this.fechaCreacion = fechaCreacion;
    this.fechaModificacion = fechaModificacion;
    this.nombre = nombre;
    this.usuCreador = usuCreador;
    this.usuModificador = usuModificador;
    this.pqrses = pqrses;
    this.procesos = procesos;
    this.usuarioAreas = usuarioAreas;
  }

  @Id
  @GeneratedValue(generator = "objectId")
  @GenericGenerator(name = "objectId", strategy = "co.edu.usbcali.pqrs.utility.ObjectIdGenerator")
  @Column(name = "area_id", unique = true, nullable = false)
  public String getAreaId() {
    return this.areaId;
  }

  public void setAreaId(String areaId) {
    this.areaId = areaId;
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

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
  public List<Pqrs> getPqrses() {
    return this.pqrses;
  }

  public void setPqrses(List<Pqrs> pqrses) {
    this.pqrses = pqrses;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
  public List<Proceso> getProcesos() {
    return this.procesos;
  }

  public void setProcesos(List<Proceso> procesos) {
    this.procesos = procesos;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
  public List<UsuarioArea> getUsuarioAreas() {
    return this.usuarioAreas;
  }

  public void setUsuarioAreas(List<UsuarioArea> usuarioAreas) {
    this.usuarioAreas = usuarioAreas;
  }
}
