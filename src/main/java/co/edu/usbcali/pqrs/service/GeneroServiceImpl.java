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
public class GeneroServiceImpl implements GeneroService {

  private static final Logger log = LoggerFactory.getLogger(GeneroServiceImpl.class);

  @Autowired private GeneroRepository generoRepository;

  @Autowired private Validator validator;

  @Override
  public void validate(Genero genero) throws Exception {
    try {
      Set<ConstraintViolation<Genero>> constraintViolations = validator.validate(genero);
      if (constraintViolations.size() > 0) {
        StringBuilder strMessage = new StringBuilder();
        for (ConstraintViolation<Genero> constraintViolation : constraintViolations) {
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
    return generoRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Genero> findAll() {
    log.debug("finding all Genero instances");
    return generoRepository.findAll();
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Genero save(Genero entity) throws Exception {
    log.debug("saving Genero instance");
    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Genero");
      }

      return generoRepository.save(entity);

    } catch (Exception e) {
      log.error("save Genero failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void delete(Genero entity) throws Exception {
    log.debug("deleting Genero instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Genero");
    }

    if (entity.getGenId() == null) {
      throw new ZMessManager().new EmptyFieldException("genId");
    }

    findById(entity.getGenId())
        .ifPresent(
            entidad -> {
              List<Pqrs> pqrses = entidad.getPqrses();
              if (Utilities.validationsList(pqrses) == true) {
                throw new ZMessManager().new DeletingException("pqrses");
              }
            });

    try {

      generoRepository.deleteById(entity.getGenId());
      log.debug("delete Genero successful");

    } catch (Exception e) {
      log.error("delete Genero failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void deleteById(String id) throws Exception {
    log.debug("deleting Genero instance");
    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("genId");
    }
    if (generoRepository.findById(id).isPresent()) {
      delete(generoRepository.findById(id).get());
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Genero update(Genero entity) throws Exception {

    log.debug("updating Genero instance");

    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Genero");
      }

      validate(entity);

      return generoRepository.save(entity);

    } catch (Exception e) {
      log.error("update Genero failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Genero> findById(String genId) throws Exception {
    log.debug("getting Genero instance");
    return generoRepository.findById(genId);
  }
}
