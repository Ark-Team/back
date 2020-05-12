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
public class UsuarioDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final Logger log = LoggerFactory.getLogger(UsuarioDTO.class);
  private String clave;
  private String email;
  private String estado;
  private Date fechaCreacion;
  private Date fechaModificacion;
  private Long identificacion;
  private String nombre;
  private Long telefono;
  private String usuCreador;
  private String usuId;
  private String usuModificador;
  private String compId_Compania;
  private String tusuId_TipoUsuario;

  public String getClave() {
    return clave;
  }

  public void setClave(String clave) {
    this.clave = clave;
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

  public Date getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(Date fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public Date getFechaModificacion() {
    return fechaModificacion;
  }

  public void setFechaModificacion(Date fechaModificacion) {
    this.fechaModificacion = fechaModificacion;
  }

  public Long getIdentificacion() {
    return identificacion;
  }

  public void setIdentificacion(Long identificacion) {
    this.identificacion = identificacion;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Long getTelefono() {
    return telefono;
  }

  public void setTelefono(Long telefono) {
    this.telefono = telefono;
  }

  public String getUsuCreador() {
    return usuCreador;
  }

  public void setUsuCreador(String usuCreador) {
    this.usuCreador = usuCreador;
  }

  public String getUsuId() {
    return usuId;
  }

  public void setUsuId(String usuId) {
    this.usuId = usuId;
  }

  public String getUsuModificador() {
    return usuModificador;
  }

  public void setUsuModificador(String usuModificador) {
    this.usuModificador = usuModificador;
  }

  public String getCompId_Compania() {
    return compId_Compania;
  }

  public void setCompId_Compania(String compId_Compania) {
    this.compId_Compania = compId_Compania;
  }

  public String getTusuId_TipoUsuario() {
    return tusuId_TipoUsuario;
  }

  public void setTusuId_TipoUsuario(String tusuId_TipoUsuario) {
    this.tusuId_TipoUsuario = tusuId_TipoUsuario;
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
