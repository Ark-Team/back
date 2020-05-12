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
public class ProcesoDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final Logger log = LoggerFactory.getLogger(ProcesoDTO.class);
  private String estado;
  private Date fechaCreacion;
  private Date fechaModificacion;
  private String nombre;
  private String prosId;
  private String usuCreador;
  private String usuModificador;
  private String areaId_Area;

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

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getProsId() {
    return prosId;
  }

  public void setProsId(String prosId) {
    this.prosId = prosId;
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

  public String getAreaId_Area() {
    return areaId_Area;
  }

  public void setAreaId_Area(String areaId_Area) {
    this.areaId_Area = areaId_Area;
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
