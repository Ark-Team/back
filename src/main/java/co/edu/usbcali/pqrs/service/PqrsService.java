package co.edu.usbcali.pqrs.service;

import co.edu.usbcali.pqrs.domain.Area;
import co.edu.usbcali.pqrs.domain.Pqrs;

import co.edu.usbcali.pqrs.domain.Proceso;

import co.edu.usbcali.pqrs.dto.FechaCantidad;
import java.util.*;
import org.springframework.transaction.annotation.Transactional;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
public interface PqrsService extends GenericService<Pqrs, String> {

  @Transactional(readOnly = true)
  Long countAllByEstadoAndAndArea_AreaId(String estado, String areaId);

  List<FechaCantidad> countPqrsByDates();

  @Transactional(readOnly = true)
  List<Pqrs> findAllByArea(Area area);

  @Transactional(readOnly = true)
  List<Pqrs> findAllByAreaAndProceso(Area area, Proceso proceso);

  Long countAllByEstadoAndCompania_CompId(String estado, String companyId);
}
