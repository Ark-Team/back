package co.edu.usbcali.pqrs.service;

import co.edu.usbcali.pqrs.domain.AtributoFormulario;
import co.edu.usbcali.pqrs.domain.Compania;
import org.springframework.transaction.annotation.Transactional;

import java.math.*;

import java.util.*;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
public interface AtributoFormularioService extends GenericService<AtributoFormulario, String> {
    @Transactional(readOnly = true)
    List<AtributoFormulario> findAllByCompania(Compania compania);
}
