package co.edu.usbcali.pqrs.service;

import java.math.*;
import java.util.*;

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
public class ProcesoServiceImpl implements ProcesoService {

  private static final Logger log = LoggerFactory.getLogger(ProcesoServiceImpl.class);

  @Autowired private ProcesoRepository procesoRepository;

  @Autowired private UsuarioRepository usuarioRepository;

  @Autowired private Validator validator;


  @Override
  public void validate(Proceso proceso) throws Exception {
    try {
      Set<ConstraintViolation<Proceso>> constraintViolations = validator.validate(proceso);
      if (constraintViolations.size() > 0) {
        StringBuilder strMessage = new StringBuilder();
        for (ConstraintViolation<Proceso> constraintViolation : constraintViolations) {
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
    return procesoRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Proceso> findAll() {
    log.debug("finding all Proceso instances");
    return procesoRepository.findAll();
  }
  @Override
  @Transactional
  public List<Proceso> findAllByCompania(String userId){
    Usuario usuario = usuarioRepository.findById(userId).get();
    return procesoRepository.findAllByCompania(usuario.getCompania().getCompId());
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Proceso save(Proceso entity) throws Exception {
    log.debug("saving Proceso instance");
    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Proceso");
      }

      validate(entity);

      if (procesoRepository.findById(entity.getProsId()).isPresent()) {
        throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
      }
      entity.setEstado("S");
      entity.setFechaCreacion(new Date());
      return procesoRepository.save(entity);

    } catch (Exception e) {
      log.error("save Proceso failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void delete(Proceso entity) throws Exception {
    log.debug("deleting Proceso instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Proceso");
    }

    if (entity.getProsId() == null) {
      throw new ZMessManager().new EmptyFieldException("prosId");
    }

    findById(entity.getProsId())
        .ifPresent(
            entidad -> {
              List<Pqrs> pqrses = entidad.getPqrses();
              if (Utilities.validationsList(pqrses) == true) {
                throw new ZMessManager().new DeletingException("pqrses");
              }
            });

    try {

      procesoRepository.deleteById(entity.getProsId());
      log.debug("delete Proceso successful");

    } catch (Exception e) {
      log.error("delete Proceso failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void deleteById(String id) throws Exception {
    log.debug("deleting Proceso instance");
    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("prosId");
    }
    if (procesoRepository.findById(id).isPresent()) {
      delete(procesoRepository.findById(id).get());
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Proceso update(Proceso entity) throws Exception {

    log.debug("updating Proceso instance");

    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Proceso");
      }

      entity.setFechaModificacion(new Date());
      return procesoRepository.save(entity);

    } catch (Exception e) {
      log.error("update Proceso failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Proceso> findById(String prosId) throws Exception {
    log.debug("getting Proceso instance");
    return procesoRepository.findById(prosId);
  }


  @Override
  @Transactional(readOnly = true)
  public List<Proceso> findAllByArea(Area area) throws Exception {
    log.debug("getting Proceso instance");
    return procesoRepository.findAllByArea(area);
  }

  @Override
  public Proceso cambiarEstado(Proceso entity) throws Exception {
    String estado = entity.getEstado();
    if (entity == null) throw new Exception("El proceso es nula");
    this.validate(entity);

    if (estado.equals("N")) {
      entity.setEstado("S");
    }
    if (estado.equals("S")) entity.setEstado("N");

    entity = procesoRepository.save(entity);
    return entity;
  }
}
