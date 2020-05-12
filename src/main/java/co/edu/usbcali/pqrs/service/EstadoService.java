package co.edu.usbcali.pqrs.service;

import co.edu.usbcali.pqrs.domain.Estado;

import java.math.*;

import java.util.*;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
public interface EstadoService extends GenericService<Estado, String> {
  Estado cambiarEstado(Estado entity) throws Exception;
}
