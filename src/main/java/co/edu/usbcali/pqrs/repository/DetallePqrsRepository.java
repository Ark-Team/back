package co.edu.usbcali.pqrs.repository;

import co.edu.usbcali.pqrs.domain.DetallePqrs;

import co.edu.usbcali.pqrs.domain.Pqrs;
import co.edu.usbcali.pqrs.domain.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

/** Interface for DetallePqrsRepository. */
public interface DetallePqrsRepository extends JpaRepository<DetallePqrs, String> {
  List<DetallePqrs> findAllByUsuCreador(String usuCreador);
  DetallePqrs findByUsuCreadorAndPqrs_PqrsIdAndEstado_EstaId(String usuCreador, String pqrs_Id,String estaId);
  DetallePqrs findByUsuCreadorAndPqrs_PqrsId(String usuCreador, String pqrs_Id);
  List<DetallePqrs> findAllByPqrs(Pqrs pqrs);
  Long countAllByEstado_EstaIdAndUsuCreador(String estaId, String usuCreador);
}
