package co.edu.usbcali.pqrs.repository;

import co.edu.usbcali.pqrs.domain.Genero;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

/** Interface for GeneroRepository. */
public interface GeneroRepository extends JpaRepository<Genero, String> {}
