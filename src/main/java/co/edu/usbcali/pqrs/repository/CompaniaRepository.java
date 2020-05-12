package co.edu.usbcali.pqrs.repository;

import co.edu.usbcali.pqrs.domain.Compania;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

/** Interface for CompaniaRepository. */
public interface CompaniaRepository extends JpaRepository<Compania, String> {
}
