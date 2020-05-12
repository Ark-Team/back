package co.edu.usbcali.pqrs.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.*;
import org.hibernate.annotations.GenericGenerator;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@Entity
@Table(name = "cliente", schema = "public")
public class Cliente implements java.io.Serializable {
  @NotNull private String clieId;
  private String email;
  private String nombre;
  private Long telefono;
  private List<Pqrs> pqrses = new ArrayList<Pqrs>(0);

  public Cliente() {}

  public Cliente(String clieId, String email, String nombre, List<Pqrs> pqrses, Long telefono) {
    this.clieId = clieId;
    this.email = email;
    this.nombre = nombre;
    this.telefono = telefono;
    this.pqrses = pqrses;
  }

  @Id
  @GeneratedValue(generator = "objectId")
  @GenericGenerator(name = "objectId", strategy = "co.edu.usbcali.pqrs.utility.ObjectIdGenerator")
  @Column(name = "clie_id", unique = true, nullable = false)
  public String getClieId() {
    return this.clieId;
  }

  public void setClieId(String clieId) {
    this.clieId = clieId;
  }

  @Column(name = "email")
  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "nombre")
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

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
  public List<Pqrs> getPqrses() {
    return this.pqrses;
  }

  public void setPqrses(List<Pqrs> pqrses) {
    this.pqrses = pqrses;
  }
}
