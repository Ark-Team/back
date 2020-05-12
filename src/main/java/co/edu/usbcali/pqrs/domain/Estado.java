package co.edu.usbcali.pqrs.domain;

import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.*;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@Entity
@Table(name = "estado", schema = "public")
public class Estado implements java.io.Serializable {
  @NotNull private String estaId;
  private String estado;
  private Date fechaCreacion;
  private Date fechaModificacion;

  @NotNull
  @Size(min = 3, max = 100)
  private String nombre;

  private String usuCreador;
  private String usuModificador;
  private List<DetallePqrs> detallePqrses = new ArrayList<DetallePqrs>(0);

  public Estado() {}

  public Estado(
      String estaId,
      List<DetallePqrs> detallePqrses,
      String estado,
      Date fechaCreacion,
      Date fechaModificacion,
      String nombre,
      String usuCreador,
      String usuModificador) {
    this.estaId = estaId;
    this.estado = estado;
    this.fechaCreacion = fechaCreacion;
    this.fechaModificacion = fechaModificacion;
    this.nombre = nombre;
    this.usuCreador = usuCreador;
    this.usuModificador = usuModificador;
    this.detallePqrses = detallePqrses;
  }

  @Id
  @GeneratedValue(generator = "objectId")
  @GenericGenerator(name = "objectId", strategy = "co.edu.usbcali.pqrs.utility.ObjectIdGenerator")
  @Column(name = "esta_id", unique = true, nullable = false)
  public String getEstaId() {
    return this.estaId;
  }

  public void setEstaId(String estaId) {
    this.estaId = estaId;
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

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "estado")
  public List<DetallePqrs> getDetallePqrses() {
    return this.detallePqrses;
  }

  public void setDetallePqrses(List<DetallePqrs> detallePqrses) {
    this.detallePqrses = detallePqrses;
  }
}
