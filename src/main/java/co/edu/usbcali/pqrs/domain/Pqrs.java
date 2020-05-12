package co.edu.usbcali.pqrs.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.*;
import org.hibernate.annotations.GenericGenerator;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@Entity
@Table(name = "pqrs", schema = "public")
public class Pqrs implements java.io.Serializable {
  @NotNull private String pqrsId;

  private Area area;
  private Cliente cliente;
  private Compania compania;
  private Formulario formulario;
  private Genero genero;
  private Proceso proceso;
  private TipoDocumento tipoDocumento;
  private TipoPqrs tipoPqrs;
  private String asunto;
  private Long celular;
  private String cuidad;
  private String departamento;

  @Size(max = 200)
  private String descripcion;

  private String direccion;

  @Size(max = 30)
  private String email;

  private String estado;
  private Long fax;
  private Date fechaAsunto;
  private Date fechaCreacion;
  private Long identificacion;
  private String lugar;
  private String municipio;

  @Size(max = 30)
  private String nombre;

  private Long telefono;
  private List<DetallePqrs> detallePqrses = new ArrayList<DetallePqrs>(0);

  public Pqrs() {}

  public Pqrs(
      String pqrsId,
      Area area,
      String asunto,
      Long celular,
      Cliente cliente,
      Compania compania,
      String cuidad,
      String departamento,
      String descripcion,
      List<DetallePqrs> detallePqrses,
      String direccion,
      String email,
      String estado,
      Long fax,
      Date fechaAsunto,
      Date fechaCreacion,
      Formulario formulario,
      Genero genero,
      Long identificacion,
      String lugar,
      String municipio,
      String nombre,
      Proceso proceso,
      Long telefono,
      TipoDocumento tipoDocumento,
      TipoPqrs tipoPqrs) {
    this.pqrsId = pqrsId;
    this.area = area;
    this.cliente = cliente;
    this.compania = compania;
    this.formulario = formulario;
    this.genero = genero;
    this.proceso = proceso;
    this.tipoDocumento = tipoDocumento;
    this.tipoPqrs = tipoPqrs;
    this.asunto = asunto;
    this.celular = celular;
    this.cuidad = cuidad;
    this.departamento = departamento;
    this.descripcion = descripcion;
    this.direccion = direccion;
    this.email = email;
    this.estado = estado;
    this.fax = fax;
    this.fechaAsunto = fechaAsunto;
    this.fechaCreacion = fechaCreacion;
    this.identificacion = identificacion;
    this.lugar = lugar;
    this.municipio = municipio;
    this.nombre = nombre;
    this.telefono = telefono;
    this.detallePqrses = detallePqrses;
  }

  @Id
  @GeneratedValue(generator = "objectId")
  @GenericGenerator(name = "objectId", strategy = "co.edu.usbcali.pqrs.utility.ObjectIdGenerator")
  @Column(name = "pqrs_id", unique = true, nullable = false)
  public String getPqrsId() {
    return this.pqrsId;
  }

  public void setPqrsId(String pqrsId) {
    this.pqrsId = pqrsId;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "area_id")
  public Area getArea() {
    return this.area;
  }

  public void setArea(Area area) {
    this.area = area;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "clie_id")
  public Cliente getCliente() {
    return this.cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
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
  @JoinColumn(name = "form_id")
  public Formulario getFormulario() {
    return this.formulario;
  }

  public void setFormulario(Formulario formulario) {
    this.formulario = formulario;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "gen_id")
  public Genero getGenero() {
    return this.genero;
  }

  public void setGenero(Genero genero) {
    this.genero = genero;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pros_id")
  public Proceso getProceso() {
    return this.proceso;
  }

  public void setProceso(Proceso proceso) {
    this.proceso = proceso;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tcdoc_id")
  public TipoDocumento getTipoDocumento() {
    return this.tipoDocumento;
  }

  public void setTipoDocumento(TipoDocumento tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tpqrs_id")
  public TipoPqrs getTipoPqrs() {
    return this.tipoPqrs;
  }

  public void setTipoPqrs(TipoPqrs tipoPqrs) {
    this.tipoPqrs = tipoPqrs;
  }

  @Column(name = "asunto")
  public String getAsunto() {
    return this.asunto;
  }

  public void setAsunto(String asunto) {
    this.asunto = asunto;
  }

  @Column(name = "celular")
  public Long getCelular() {
    return this.celular;
  }

  public void setCelular(Long celular) {
    this.celular = celular;
  }

  @Column(name = "cuidad")
  public String getCuidad() {
    return this.cuidad;
  }

  public void setCuidad(String cuidad) {
    this.cuidad = cuidad;
  }

  @Column(name = "departamento")
  public String getDepartamento() {
    return this.departamento;
  }

  public void setDepartamento(String departamento) {
    this.departamento = departamento;
  }

  @Column(name = "descripcion", nullable = false)
  public String getDescripcion() {
    return this.descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  @Column(name = "direccion")
  public String getDireccion() {
    return this.direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  @Column(name = "email", nullable = false)
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

  @Column(name = "fax")
  public Long getFax() {
    return this.fax;
  }

  public void setFax(Long fax) {
    this.fax = fax;
  }

  @Column(name = "fecha_asunto")
  public Date getFechaAsunto() {
    return this.fechaAsunto;
  }

  public void setFechaAsunto(Date fechaAsunto) {
    this.fechaAsunto = fechaAsunto;
  }

  @Column(name = "fecha_creacion")
  public Date getFechaCreacion() {
    return this.fechaCreacion;
  }

  public void setFechaCreacion(Date fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  @Column(name = "identificacion")
  public Long getIdentificacion() {
    return this.identificacion;
  }

  public void setIdentificacion(Long identificacion) {
    this.identificacion = identificacion;
  }

  @Column(name = "lugar")
  public String getLugar() {
    return this.lugar;
  }

  public void setLugar(String lugar) {
    this.lugar = lugar;
  }

  @Column(name = "municipio")
  public String getMunicipio() {
    return this.municipio;
  }

  public void setMunicipio(String municipio) {
    this.municipio = municipio;
  }

  @Column(name = "nombre", nullable = false)
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

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "pqrs")
  public List<DetallePqrs> getDetallePqrses() {
    return this.detallePqrses;
  }

  public void setDetallePqrses(List<DetallePqrs> detallePqrses) {
    this.detallePqrses = detallePqrses;
  }
}
