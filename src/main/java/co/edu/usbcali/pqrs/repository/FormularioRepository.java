package co.edu.usbcali.pqrs.repository;

import co.edu.usbcali.pqrs.domain.Compania;
import co.edu.usbcali.pqrs.domain.Formulario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

/** Interface for FormularioRepository. */
public interface FormularioRepository extends JpaRepository<Formulario, String> {
    List<Formulario> findAllByCompania(Compania compania);
    Formulario findByCompania(Compania compania);
}
