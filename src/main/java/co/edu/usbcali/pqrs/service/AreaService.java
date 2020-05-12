package co.edu.usbcali.pqrs.service;

import co.edu.usbcali.pqrs.domain.Area;
import co.edu.usbcali.pqrs.domain.Compania;
import org.springframework.transaction.annotation.Transactional;

import java.math.*;

import java.util.*;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
public interface AreaService extends GenericService<Area, String> {
    @Transactional(readOnly = true)
    List<Area> findAllByCompania(Compania compania);
    public List<Area> findAllByCompaniaExpeto(Compania compania,String area);
    Area cambiarEstado(Area entity) throws Exception;
}
