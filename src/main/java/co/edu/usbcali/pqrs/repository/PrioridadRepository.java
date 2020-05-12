package co.edu.usbcali.pqrs.repository;

import co.edu.usbcali.pqrs.domain.Prioridad;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

/** Interface for PrioridadRepository. */
public interface PrioridadRepository extends JpaRepository<Prioridad, String> {}
