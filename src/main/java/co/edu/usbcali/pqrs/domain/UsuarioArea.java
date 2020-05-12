package co.edu.usbcali.pqrs.domain;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.*;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@Entity
@Table(name = "usuario_area", schema = "public")
public class UsuarioArea implements java.io.Serializable {
  @NotNull private String usuAreaId;
  @NotNull private Area area;
  @NotNull private Usuario usuario;

  public UsuarioArea() {}

  public UsuarioArea(String usuAreaId, Area area, Usuario usuario) {
    this.usuAreaId = usuAreaId;
    this.area = area;
    this.usuario = usuario;
  }

  @Id
  @GeneratedValue(generator = "objectId")
  @GenericGenerator(name = "objectId", strategy = "co.edu.usbcali.pqrs.utility.ObjectIdGenerator")
  @Column(name = "usu_area_id", unique = true, nullable = false)
  public String getUsuAreaId() {
    return this.usuAreaId;
  }

  public void setUsuAreaId(String usuAreaId) {
    this.usuAreaId = usuAreaId;
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
  @JoinColumn(name = "usu_id")
  public Usuario getUsuario() {
    return this.usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
}
