package co.edu.usbcali.pqrs.service;

import java.math.*;
import java.util.*;

import co.edu.usbcali.pqrs.dto.CompaniaDTO;
import co.edu.usbcali.pqrs.mapper.CompaniaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import co.edu.usbcali.pqrs.exception.*;
import co.edu.usbcali.pqrs.repository.*;
import co.edu.usbcali.pqrs.utility.Utilities;

import co.edu.usbcali.pqrs.domain.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@Scope("singleton")
@Service
public class AreaServiceImpl implements AreaService {

  private static final Logger log = LoggerFactory.getLogger(AreaServiceImpl.class);

  @Autowired private AreaRepository areaRepository;

  @Autowired private CompaniaRepository companiaRepository;

  @Autowired private Validator validator;

  @Override
  public void validate(Area area) throws Exception {
    try {
      Set<ConstraintViolation<Area>> constraintViolations = validator.validate(area);
      if (constraintViolations.size() > 0) {
        StringBuilder strMessage = new StringBuilder();
        for (ConstraintViolation<Area> constraintViolation : constraintViolations) {
          strMessage.append(constraintViolation.getPropertyPath().toString());
          strMessage.append(" - ");
          strMessage.append(constraintViolation.getMessage());
          strMessage.append(". \n");
        }
        throw new Exception(strMessage.toString());
      }
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Long count() {
    return areaRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Area> findAll() {
    log.debug("finding all Area instances");
    return areaRepository.findAll();
  }


  @Override
  @Transactional(readOnly = true)
  public List<Area> findAllByCompania(Compania compania) {
    log.debug("finding all Area instances");
    return areaRepository.findAllByCompania(compania);
  }

  @Override
  public List<Area> findAllByCompaniaExpeto(Compania compania,String area) {
    log.debug("finding all Area instances");
    List<Area> areaList =areaRepository.findAllByCompania(compania);
    for (int i = 0; i < areaList.size(); i++) {
      if (areaList.get(i).getAreaId().equals(area)){
        areaList.remove(i);
      }
    }
    return areaList;
  }
  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Area save(Area entity) throws Exception {
    log.debug("saving Area instance");
    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Area");
      }

      if (!companiaRepository.findById(entity.getCompania().getCompId()).isPresent()) {
        throw new Exception("La compañia asociada, no existe");
      }

      entity.setEstado("S");
      entity.setFechaCreacion(new Date());
      return areaRepository.save(entity);

    } catch (Exception e) {
      log.error("save Area failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void delete(Area entity) throws Exception {
    log.debug("deleting Area instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Area");
    }

    if (entity.getAreaId() == null) {
      throw new ZMessManager().new EmptyFieldException("areaId");
    }

    findById(entity.getAreaId())
        .ifPresent(
            entidad -> {
              List<Pqrs> pqrses = entidad.getPqrses();
              if (Utilities.validationsList(pqrses) == true) {
                throw new ZMessManager().new DeletingException("pqrses");
              }
              List<Proceso> procesos = entidad.getProcesos();
              if (Utilities.validationsList(procesos) == true) {
                throw new ZMessManager().new DeletingException("procesos");
              }
              List<UsuarioArea> usuarioAreas = entidad.getUsuarioAreas();
              if (Utilities.validationsList(usuarioAreas) == true) {
                throw new ZMessManager().new DeletingException("usuarioAreas");
              }
            });

    try {

      areaRepository.deleteById(entity.getAreaId());
      log.debug("delete Area successful");

    } catch (Exception e) {
      log.error("delete Area failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void deleteById(String id) throws Exception {
    log.debug("deleting Area instance");
    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("areaId");
    }
    if (areaRepository.findById(id).isPresent()) {
      delete(areaRepository.findById(id).get());
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Area update(Area entity) throws Exception {

    log.debug("updating Area instance");

    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Area");
      }

      validate(entity);
      entity.setFechaModificacion(new Date());
      return areaRepository.save(entity);

    } catch (Exception e) {
      log.error("update Area failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Area> findById(String areaId) throws Exception {
    log.debug("getting Area instance");
    return areaRepository.findById(areaId);
  }

  @Override
  public Area cambiarEstado(Area entity) throws Exception {
    String estado = entity.getEstado();
    if (entity == null) throw new Exception("El tipo usuario es nula");
    this.validate(entity);

    if (estado.equals("N")) {
      entity.setEstado("S");
    }
    if (estado.equals("S")) entity.setEstado("N");
    entity.setFechaModificacion(new Date());
    entity = areaRepository.save(entity);
    return entity;
  }
}
