package co.edu.usbcali.pqrs.service;

import co.edu.usbcali.pqrs.domain.Area;
import co.edu.usbcali.pqrs.domain.Proceso;
import org.springframework.transaction.annotation.Transactional;

import java.math.*;

import java.util.*;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
public interface ProcesoService extends GenericService<Proceso, String> {

    @Transactional
    List<Proceso> findAllByCompania(String userId);
  @Transactional(readOnly = true)
  List<Proceso> findAllByArea(Area area) throws Exception;

  Proceso cambiarEstado(Proceso entity) throws Exception;
}
