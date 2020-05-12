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
public class UsuarioAreaDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final Logger log = LoggerFactory.getLogger(UsuarioAreaDTO.class);
  private String usuAreaId;
  private String areaId_Area;
  private String usuId_Usuario;

  public String getUsuAreaId() {
    return usuAreaId;
  }

  public void setUsuAreaId(String usuAreaId) {
    this.usuAreaId = usuAreaId;
  }

  public String getAreaId_Area() {
    return areaId_Area;
  }

  public void setAreaId_Area(String areaId_Area) {
    this.areaId_Area = areaId_Area;
  }

  public String getUsuId_Usuario() {
    return usuId_Usuario;
  }

  public void setUsuId_Usuario(String usuId_Usuario) {
    this.usuId_Usuario = usuId_Usuario;
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
