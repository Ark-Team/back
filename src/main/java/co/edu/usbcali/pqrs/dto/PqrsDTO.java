package co.edu.usbcali.pqrs.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
public class PqrsDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final Logger log = LoggerFactory.getLogger(PqrsDTO.class);
  private String asunto;
  private Long celular;
  private String cuidad;
  private String departamento;
  private String descripcion;
  private String direccion;
  private String email;
  private String estado;
  private Long fax;
  private Date fechaAsunto;
  private Date fechaCreacion;
  private Long identificacion;
  private String lugar;
  private String municipio;
  private String nombre;
  private String pqrsId;
  private Long telefono;
  private String areaId_Area;
  private String clieId_Cliente;
  private String compId_Compania;
  private String formId_Formulario;
  private String genId_Genero;
  private String prosId_Proceso;
  private String tcdocId_TipoDocumento;
  private String tpqrsId_TipoPqrs;

  public String getAsunto() {
    return asunto;
  }

  public void setAsunto(String asunto) {
    this.asunto = asunto;
  }

  public Long getCelular() {
    return celular;
  }

  public void setCelular(Long celular) {
    this.celular = celular;
  }

  public String getCuidad() {
    return cuidad;
  }

  public void setCuidad(String cuidad) {
    this.cuidad = cuidad;
  }

  public String getDepartamento() {
    return departamento;
  }

  public void setDepartamento(String departamento) {
    this.departamento = departamento;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public Long getFax() {
    return fax;
  }

  public void setFax(Long fax) {
    this.fax = fax;
  }

  public Date getFechaAsunto() {
    return fechaAsunto;
  }

  public void setFechaAsunto(Date fechaAsunto) {
    this.fechaAsunto = fechaAsunto;
  }

  public Date getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(Date fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public Long getIdentificacion() {
    return identificacion;
  }

  public void setIdentificacion(Long identificacion) {
    this.identificacion = identificacion;
  }

  public String getLugar() {
    return lugar;
  }

  public void setLugar(String lugar) {
    this.lugar = lugar;
  }

  public String getMunicipio() {
    return municipio;
  }

  public void setMunicipio(String municipio) {
    this.municipio = municipio;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getPqrsId() {
    return pqrsId;
  }

  public void setPqrsId(String pqrsId) {
    this.pqrsId = pqrsId;
  }

  public Long getTelefono() {
    return telefono;
  }

  public void setTelefono(Long telefono) {
    this.telefono = telefono;
  }

  public String getAreaId_Area() {
    return areaId_Area;
  }

  public void setAreaId_Area(String areaId_Area) {
    this.areaId_Area = areaId_Area;
  }

  public String getClieId_Cliente() {
    return clieId_Cliente;
  }

  public void setClieId_Cliente(String clieId_Cliente) {
    this.clieId_Cliente = clieId_Cliente;
  }

  public String getCompId_Compania() {
    return compId_Compania;
  }

  public void setCompId_Compania(String compId_Compania) {
    this.compId_Compania = compId_Compania;
  }

  public String getFormId_Formulario() {
    return formId_Formulario;
  }

  public void setFormId_Formulario(String formId_Formulario) {
    this.formId_Formulario = formId_Formulario;
  }

  public String getGenId_Genero() {
    return genId_Genero;
  }

  public void setGenId_Genero(String genId_Genero) {
    this.genId_Genero = genId_Genero;
  }

  public String getProsId_Proceso() {
    return prosId_Proceso;
  }

  public void setProsId_Proceso(String prosId_Proceso) {
    this.prosId_Proceso = prosId_Proceso;
  }

  public String getTcdocId_TipoDocumento() {
    return tcdocId_TipoDocumento;
  }

  public void setTcdocId_TipoDocumento(String tcdocId_TipoDocumento) {
    this.tcdocId_TipoDocumento = tcdocId_TipoDocumento;
  }

  public String getTpqrsId_TipoPqrs() {
    return tpqrsId_TipoPqrs;
  }

  public void setTpqrsId_TipoPqrs(String tpqrsId_TipoPqrs) {
    this.tpqrsId_TipoPqrs = tpqrsId_TipoPqrs;
  }

  @Override
  public String toString() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);

    try {
      return mapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      log.error(e.getMessage());

      return super.toString();
    }
  }
}
