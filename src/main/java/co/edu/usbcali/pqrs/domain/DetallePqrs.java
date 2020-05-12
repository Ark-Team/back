package co.edu.usbcali.pqrs.domain;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.*;
import org.hibernate.annotations.GenericGenerator;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@Entity
@Table(name = "detalle_pqrs", schema = "public")
public class DetallePqrs implements java.io.Serializable {
  @NotNull private String dpqrsId;
  private Estado estado;
  private Pqrs pqrs;
  private Prioridad prioridad;
  private String descripcion;
  private Date fechaCreacion;
  private Date fechaModificacion;
  private String usuCreador;
  private String usuModificador;

  public DetallePqrs() {}

  public DetallePqrs(
      String dpqrsId,
      String descripcion,
      Estado estado,
      Date fechaCreacion,
      Date fechaModificacion,
      Pqrs pqrs,
      Prioridad prioridad,
      String usuCreador,
      String usuModificador) {
    this.dpqrsId = dpqrsId;
    this.estado = estado;
    this.pqrs = pqrs;
    this.prioridad = prioridad;
    this.descripcion = descripcion;
    this.fechaCreacion = fechaCreacion;
    this.fechaModificacion = fechaModificacion;
    this.usuCreador = usuCreador;
    this.usuModificador = usuModificador;
  }

  @Id
  @GeneratedValue(generator = "objectId")
  @GenericGenerator(name = "objectId", strategy = "co.edu.usbcali.pqrs.utility.ObjectIdGenerator")
  @Column(name = "dpqrs_id", unique = true, nullable = false)
  public String getDpqrsId() {
    return this.dpqrsId;
  }

  public void setDpqrsId(String dpqrsId) {
    this.dpqrsId = dpqrsId;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "esta_id")
  public Estado getEstado() {
    return this.estado;
  }

  public void setEstado(Estado estado) {
    this.estado = estado;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pqrs_id")
  public Pqrs getPqrs() {
    return this.pqrs;
  }

  public void setPqrs(Pqrs pqrs) {
    this.pqrs = pqrs;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "prio_id")
  public Prioridad getPrioridad() {
    return this.prioridad;
  }

  public void setPrioridad(Prioridad prioridad) {
    this.prioridad = prioridad;
  }

  @Column(name = "descripcion")
  public String getDescripcion() {
    return this.descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
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
}
