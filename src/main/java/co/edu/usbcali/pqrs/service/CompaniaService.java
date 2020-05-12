package co.edu.usbcali.pqrs.service;

import co.edu.usbcali.pqrs.domain.Compania;

import java.math.*;

import java.util.*;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
public interface CompaniaService extends GenericService<Compania, String> {
  Compania cambiarEstado(Compania entity) throws Exception;
}
