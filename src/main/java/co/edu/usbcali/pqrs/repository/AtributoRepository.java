package co.edu.usbcali.pqrs.repository;

import co.edu.usbcali.pqrs.domain.Atributo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

/** Interface for AtributoRepository. */
public interface AtributoRepository extends JpaRepository<Atributo, String> {
}
