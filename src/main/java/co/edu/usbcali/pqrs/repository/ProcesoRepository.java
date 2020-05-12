package co.edu.usbcali.pqrs.repository;

import co.edu.usbcali.pqrs.domain.Area;
import co.edu.usbcali.pqrs.domain.Proceso;

import co.edu.usbcali.pqrs.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

/** Interface for ProcesoRepository. */
public interface ProcesoRepository extends JpaRepository<Proceso, String> {

  @Query("select distinct p from Proceso p, Usuario u, Area a where p.area.areaId = a.areaId " +
      "and u.compania.compId = ?1 and a.compania.compId = ?1 ")
  List<Proceso> findAllByCompania(String usuId);
  List<Proceso> findAllByArea(Area area);

}
