package co.edu.usbcali.pqrs.repository;

import co.edu.usbcali.pqrs.domain.Area;
import co.edu.usbcali.pqrs.domain.Pqrs;

import co.edu.usbcali.pqrs.domain.Proceso;
import java.util.List;
import java.util.Optional;
import javax.persistence.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import org.springframework.data.jpa.repository.Query;

/** Interface for PqrsRepository. */
public interface PqrsRepository extends JpaRepository<Pqrs, String> {

  List<Pqrs> findAllByArea(Area area);
  List<Pqrs> findAllByAreaAndProceso(Area area, Proceso proceso);
  @Query("select distinct p from Proceso p, Usuario u, Area a where p.area.areaId = a.areaId " +
      "and u.compania.compId = ?1 and a.compania.compId = ?1 ")
  Long findAllByCompania(String usuId);
  Long countAllByEstadoAndCompania_CompId(String estado, String companyId);
  Long countAllByEstadoAndArea_AreaId(String estado, String areaId);
}
