package co.edu.usbcali.pqrs.repository;

import co.edu.usbcali.pqrs.domain.Area;

import co.edu.usbcali.pqrs.domain.Compania;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

/** Interface for AreaRepository. */
public interface AreaRepository extends JpaRepository<Area, String> {
    List<Area> findAllByCompania(Compania compania);
}
