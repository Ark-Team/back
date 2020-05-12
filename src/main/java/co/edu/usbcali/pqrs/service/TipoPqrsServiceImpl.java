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
public class TipoPqrsServiceImpl implements TipoPqrsService {

  private static final Logger log = LoggerFactory.getLogger(TipoPqrsServiceImpl.class);

  @Autowired private TipoPqrsRepository tipoPqrsRepository;

  @Autowired private Validator validator;

  @Override
  public void validate(TipoPqrs tipoPqrs) throws Exception {
    try {
      Set<ConstraintViolation<TipoPqrs>> constraintViolations = validator.validate(tipoPqrs);
      if (constraintViolations.size() > 0) {
        StringBuilder strMessage = new StringBuilder();
        for (ConstraintViolation<TipoPqrs> constraintViolation : constraintViolations) {
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
    return tipoPqrsRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public List<TipoPqrs> findAll() {
    log.debug("finding all TipoPqrs instances");
    return tipoPqrsRepository.findAll();
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public TipoPqrs save(TipoPqrs entity) throws Exception {
    log.debug("saving TipoPqrs instance");
    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("TipoPqrs");
      }

      validate(entity);

      if (tipoPqrsRepository.findById(entity.getTpqrsId()).isPresent()) {
        throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
      }

      return tipoPqrsRepository.save(entity);

    } catch (Exception e) {
      log.error("save TipoPqrs failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void delete(TipoPqrs entity) throws Exception {
    log.debug("deleting TipoPqrs instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("TipoPqrs");
    }

    if (entity.getTpqrsId() == null) {
      throw new ZMessManager().new EmptyFieldException("tpqrsId");
    }

    findById(entity.getTpqrsId())
        .ifPresent(
            entidad -> {
              List<Pqrs> pqrses = entidad.getPqrses();
              if (Utilities.validationsList(pqrses) == true) {
                throw new ZMessManager().new DeletingException("pqrses");
              }
            });

    try {

      tipoPqrsRepository.deleteById(entity.getTpqrsId());
      log.debug("delete TipoPqrs successful");

    } catch (Exception e) {
      log.error("delete TipoPqrs failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void deleteById(String id) throws Exception {
    log.debug("deleting TipoPqrs instance");
    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("tpqrsId");
    }
    if (tipoPqrsRepository.findById(id).isPresent()) {
      delete(tipoPqrsRepository.findById(id).get());
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public TipoPqrs update(TipoPqrs entity) throws Exception {

    log.debug("updating TipoPqrs instance");

    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("TipoPqrs");
      }

      validate(entity);

      return tipoPqrsRepository.save(entity);

    } catch (Exception e) {
      log.error("update TipoPqrs failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<TipoPqrs> findById(String tpqrsId) throws Exception {
    log.debug("getting TipoPqrs instance");
    return tipoPqrsRepository.findById(tpqrsId);
  }
}
