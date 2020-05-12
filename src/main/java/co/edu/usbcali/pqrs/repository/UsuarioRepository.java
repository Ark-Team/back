package co.edu.usbcali.pqrs.repository;

import co.edu.usbcali.pqrs.domain.Area;
import co.edu.usbcali.pqrs.domain.Compania;
import co.edu.usbcali.pqrs.domain.TipoUsuario;
import co.edu.usbcali.pqrs.domain.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

/** Interface for UsuarioRepository. */
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    List<Usuario> findAllByCompania(Compania compania);
    List<Usuario> findAllByTipoUsuarioAndCompania(TipoUsuario tipoUsuario, Compania compania);
}
