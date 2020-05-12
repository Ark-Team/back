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
public class PrioridadServiceImpl implements PrioridadService {

  private static final Logger log = LoggerFactory.getLogger(PrioridadServiceImpl.class);

  @Autowired private PrioridadRepository prioridadRepository;

  @Autowired private Validator validator;

  @Override
  public void validate(Prioridad prioridad) throws Exception {
    try {
      Set<ConstraintViolation<Prioridad>> constraintViolations = validator.validate(prioridad);
      if (constraintViolations.size() > 0) {
        StringBuilder strMessage = new StringBuilder();
        for (ConstraintViolation<Prioridad> constraintViolation : constraintViolations) {
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
    return prioridadRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Prioridad> findAll() {
    log.debug("finding all Prioridad instances");
    return prioridadRepository.findAll();
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Prioridad save(Prioridad entity) throws Exception {
    log.debug("saving Prioridad instance");
    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Prioridad");
      }

      return prioridadRepository.save(entity);

    } catch (Exception e) {
      log.error("save Prioridad failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void delete(Prioridad entity) throws Exception {
    log.debug("deleting Prioridad instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Prioridad");
    }

    if (entity.getPrioId() == null) {
      throw new ZMessManager().new EmptyFieldException("prioId");
    }

    findById(entity.getPrioId())
        .ifPresent(
            entidad -> {
              List<DetallePqrs> detallePqrses = entidad.getDetallePqrses();
              if (Utilities.validationsList(detallePqrses) == true) {
                throw new ZMessManager().new DeletingException("detallePqrses");
              }
            });

    try {

      prioridadRepository.deleteById(entity.getPrioId());
      log.debug("delete Prioridad successful");

    } catch (Exception e) {
      log.error("delete Prioridad failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void deleteById(String id) throws Exception {
    log.debug("deleting Prioridad instance");
    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("prioId");
    }
    if (prioridadRepository.findById(id).isPresent()) {
      delete(prioridadRepository.findById(id).get());
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Prioridad update(Prioridad entity) throws Exception {

    log.debug("updating Prioridad instance");

    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Prioridad");
      }

      validate(entity);

      return prioridadRepository.save(entity);

    } catch (Exception e) {
      log.error("update Prioridad failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Prioridad> findById(String prioId) throws Exception {
    log.debug("getting Prioridad instance");
    return prioridadRepository.findById(prioId);
  }
}
