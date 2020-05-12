package co.edu.usbcali.pqrs.repository;

import co.edu.usbcali.pqrs.domain.Estado;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

/** Interface for EstadoRepository. */
public interface EstadoRepository extends JpaRepository<Estado, String> {}
