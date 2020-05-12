package co.edu.usbcali.pqrs.repository;

import co.edu.usbcali.pqrs.domain.AtributoFormulario;

import co.edu.usbcali.pqrs.domain.Compania;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

/** Interface for AtributoFormularioRepository. */
public interface AtributoFormularioRepository extends JpaRepository<AtributoFormulario, String> {
    List<AtributoFormulario> findAllByCompania(Compania compania);
}
