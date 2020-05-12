package co.edu.usbcali.pqrs.repository;

import co.edu.usbcali.pqrs.domain.Area;
import co.edu.usbcali.pqrs.domain.Usuario;
import co.edu.usbcali.pqrs.domain.UsuarioArea;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

/** Interface for UsuarioAreaRepository. */
public interface UsuarioAreaRepository extends JpaRepository<UsuarioArea, String> {
  List<UsuarioArea> findAllByArea(Area area);
  Optional<UsuarioArea> findByUsuario(Usuario usuario);
}
