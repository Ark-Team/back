package co.edu.usbcali.pqrs.service;

import co.edu.usbcali.pqrs.domain.Area;
import co.edu.usbcali.pqrs.domain.Compania;
import co.edu.usbcali.pqrs.domain.DetallePqrs;

import co.edu.usbcali.pqrs.domain.Pqrs;
import co.edu.usbcali.pqrs.domain.Proceso;
import co.edu.usbcali.pqrs.domain.TipoUsuario;
import co.edu.usbcali.pqrs.domain.Usuario;
import java.math.*;

import java.util.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
public interface DetallePqrsService extends GenericService<DetallePqrs, String> {

 @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
 DetallePqrs findByUsuCreadorAndPqrs_PqrsId(String usuCreador, String pqrsId);

 DetallePqrs updateById(String entity) throws Exception;

 DetallePqrs redireccionPqrs(DetallePqrs detallePqrs,Area area, Proceso proceso) throws Exception;

 String asignarUsuario(Compania compania, TipoUsuario tipoUsuario,Area area);

 @Transactional(readOnly = true)
 List<DetallePqrs> findAllByPqrs(Pqrs pqrs);

 DetallePqrs findByUsuCreadorAndPqrs_PqrsIdestadoRedireccionado(String usuCreador, String pqrsId);

  @Transactional(readOnly = true)
  List<Pqrs> findPqrsByUser(String user);

 @Transactional(readOnly = true)
 List<Pqrs> findHistorialPqrsByUser(String user);

 @Transactional(readOnly = true)
 Long findAllByEstado_EstaIdAndUsuCreador(String estado, String usuId);
/*
  @Transactional(readOnly = true)
  String asignarUsuario(Compania compania, TipoUsuario tipoUsuario);

  @Transactional(readOnly = true)
  HashMap<String, Integer> cantidadPqrsAsesor(Compania compania, TipoUsuario tipoUsuario);
 */
}
