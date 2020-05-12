package co.edu.usbcali.pqrs.service;

import co.edu.usbcali.pqrs.domain.Area;
import co.edu.usbcali.pqrs.domain.Compania;
import co.edu.usbcali.pqrs.domain.TipoUsuario;
import co.edu.usbcali.pqrs.domain.Usuario;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.*;

import java.util.*;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
public interface UsuarioService extends GenericService<Usuario, String> {
    @Transactional(readOnly = true)
    List<Usuario> findAllByCompania(Compania compania);

    Usuario cambiarEstado(Usuario entity) throws Exception;

  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  Usuario saveConUsuArea(Usuario entity, Area area) throws Exception;

  List<Usuario> findAllByTipoUsuarioAndCompaniaAndUsuarioArea(TipoUsuario tipoUsuario,
      Compania compania);

  List<Usuario> findAllUserByArea(Area area);
}
