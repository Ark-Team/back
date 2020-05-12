package co.edu.usbcali.pqrs.domain;

import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.*;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@Entity
@Table(name = "tipo_documento", schema = "public")
public class TipoDocumento implements java.io.Serializable {
  @NotNull private String tcdocId;

  @NotNull
  @Size(min = 3, max = 100)
  private String nombre;

  private List<Pqrs> pqrses = new ArrayList<Pqrs>(0);

  public TipoDocumento() {}

  public TipoDocumento(String tcdocId, String nombre, List<Pqrs> pqrses) {
    this.tcdocId = tcdocId;
    this.nombre = nombre;
    this.pqrses = pqrses;
  }

  @Id
  @GeneratedValue(generator = "objectId")
  @GenericGenerator(name = "objectId", strategy = "co.edu.usbcali.pqrs.utility.ObjectIdGenerator")
  @Column(name = "tcdoc_id", unique = true, nullable = false)
  public String getTcdocId() {
    return this.tcdocId;
  }

  public void setTcdocId(String tcdocId) {
    this.tcdocId = tcdocId;
  }

  @Column(name = "nombre")
  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoDocumento")
  public List<Pqrs> getPqrses() {
    return this.pqrses;
  }

  public void setPqrses(List<Pqrs> pqrses) {
    this.pqrses = pqrses;
  }
}
