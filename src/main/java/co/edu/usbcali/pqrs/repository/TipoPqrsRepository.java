package co.edu.usbcali.pqrs.repository;

import co.edu.usbcali.pqrs.domain.TipoPqrs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

/** Interface for TipoPqrsRepository. */
public interface TipoPqrsRepository extends JpaRepository<TipoPqrs, String> {}
