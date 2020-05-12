package co.edu.usbcali.pqrs.domain;

import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.*;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@Entity
@Table(name = "genero", schema = "public")
public class Genero implements java.io.Serializable {
  @NotNull private String genId;

  @NotNull
  @Size(min = 3, max = 100)
  private String nombre;

  private List<Pqrs> pqrses = new ArrayList<Pqrs>(0);

  public Genero() {}

  public Genero(String genId, String nombre, List<Pqrs> pqrses) {
    this.genId = genId;
    this.nombre = nombre;
    this.pqrses = pqrses;
  }

  @Id
  @GeneratedValue(generator = "objectId")
  @GenericGenerator(name = "objectId", strategy = "co.edu.usbcali.pqrs.utility.ObjectIdGenerator")
  @Column(name = "gen_id", unique = true, nullable = false)
  public String getGenId() {
    return this.genId;
  }

  public void setGenId(String genId) {
    this.genId = genId;
  }

  @Column(name = "nombre")
  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "genero")
  public List<Pqrs> getPqrses() {
    return this.pqrses;
  }

  public void setPqrses(List<Pqrs> pqrses) {
    this.pqrses = pqrses;
  }
}
