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
public class DetallePqrsDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final Logger log = LoggerFactory.getLogger(DetallePqrsDTO.class);
  private String descripcion;
  private String dpqrsId;
  private Date fechaCreacion;
  private Date fechaModificacion;
  private String usuCreador;
  private String usuModificador;
  private String estaId_Estado;
  private String pqrsId_Pqrs;
  private String prioId_Prioridad;

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getDpqrsId() {
    return dpqrsId;
  }

  public void setDpqrsId(String dpqrsId) {
    this.dpqrsId = dpqrsId;
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

  public String getUsuCreador() {
    return usuCreador;
  }

  public void setUsuCreador(String usuCreador) {
    this.usuCreador = usuCreador;
  }

  public String getUsuModificador() {
    return usuModificador;
  }

  public void setUsuModificador(String usuModificador) {
    this.usuModificador = usuModificador;
  }

  public String getEstaId_Estado() {
    return estaId_Estado;
  }

  public void setEstaId_Estado(String estaId_Estado) {
    this.estaId_Estado = estaId_Estado;
  }

  public String getPqrsId_Pqrs() {
    return pqrsId_Pqrs;
  }

  public void setPqrsId_Pqrs(String pqrsId_Pqrs) {
    this.pqrsId_Pqrs = pqrsId_Pqrs;
  }

  public String getPrioId_Prioridad() {
    return prioId_Prioridad;
  }

  public void setPrioId_Prioridad(String prioId_Prioridad) {
    this.prioId_Prioridad = prioId_Prioridad;
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
