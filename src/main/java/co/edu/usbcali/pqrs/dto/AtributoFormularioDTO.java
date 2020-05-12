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
public class AtributoFormularioDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final Logger log = LoggerFactory.getLogger(AtributoFormularioDTO.class);
  private String atrformId;
  private String atriId_Atributo;
  private String compId_Compania;

  public String getAtrformId() {
    return atrformId;
  }

  public void setAtrformId(String atrformId) {
    this.atrformId = atrformId;
  }

  public String getAtriId_Atributo() {
    return atriId_Atributo;
  }

  public void setAtriId_Atributo(String atriId_Atributo) {
    this.atriId_Atributo = atriId_Atributo;
  }

  public String getCompId_Compania() {
    return compId_Compania;
  }

  public void setCompId_Compania(String compId_Compania) {
    this.compId_Compania = compId_Compania;
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
