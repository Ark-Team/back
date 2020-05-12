package co.edu.usbcali.pqrs.repository;

import co.edu.usbcali.pqrs.domain.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

/** Interface for ClienteRepository. */
public interface ClienteRepository extends JpaRepository<Cliente, String> {}
