package co.edu.usbcali.pqrs.service;

import co.edu.usbcali.pqrs.domain.TipoUsuario;

import java.math.*;

import java.util.*;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
public interface TipoUsuarioService extends GenericService<TipoUsuario, String> {
  TipoUsuario cambiarEstado(TipoUsuario entity) throws Exception;
}
