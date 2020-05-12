package co.edu.usbcali.pqrs.repository;

import co.edu.usbcali.pqrs.domain.TipoDocumento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

/** Interface for TipoDocumentoRepository. */
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, String> {}
