package co.edu.usbcali.pqrs.service;

import co.edu.usbcali.pqrs.domain.Compania;
import co.edu.usbcali.pqrs.domain.Formulario;
import org.springframework.transaction.annotation.Transactional;

import java.math.*;

import java.util.*;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
public interface FormularioService extends GenericService<Formulario, String> {
    @Transactional(readOnly = true)
    List<Formulario> findAllByCompania(Compania compania);

  @Transactional(readOnly = true)
  Formulario findByCompania(Compania compania);
}
