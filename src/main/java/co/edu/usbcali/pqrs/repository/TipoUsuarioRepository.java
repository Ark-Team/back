package co.edu.usbcali.pqrs.repository;

import co.edu.usbcali.pqrs.domain.TipoUsuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

/** Interface for TipoUsuarioRepository. */
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, String> {}
