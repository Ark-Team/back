package co.edu.usbcali.pqrs.service;

import co.edu.usbcali.pqrs.domain.Area;
import co.edu.usbcali.pqrs.domain.UsuarioArea;

import java.math.*;

import java.util.*;
import org.springframework.transaction.annotation.Transactional;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
public interface UsuarioAreaService extends GenericService<UsuarioArea, String> {

  @Transactional(readOnly = true)
  Optional<UsuarioArea> findByUsuario(String usuAreaId) throws Exception;

  @Transactional(readOnly = true)
  List<UsuarioArea> findAllByArea(Area area);
}
