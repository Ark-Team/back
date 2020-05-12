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
public class AtributoServiceImpl implements AtributoService {

  private static final Logger log = LoggerFactory.getLogger(AtributoServiceImpl.class);

  @Autowired private AtributoRepository atributoRepository;

  @Autowired private Validator validator;

  @Override
  public void validate(Atributo atributo) throws Exception {
    try {
      Set<ConstraintViolation<Atributo>> constraintViolations = validator.validate(atributo);
      if (constraintViolations.size() > 0) {
        StringBuilder strMessage = new StringBuilder();
        for (ConstraintViolation<Atributo> constraintViolation : constraintViolations) {
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
    return atributoRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Atributo> findAll() {
    log.debug("finding all Atributo instances");
    return atributoRepository.findAll();
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Atributo save(Atributo entity) throws Exception {
    log.debug("saving Atributo instance");
    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Atributo");
      }

      validate(entity);

      if (atributoRepository.findById(entity.getAtriId()).isPresent()) {
        throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
      }

      return atributoRepository.save(entity);

    } catch (Exception e) {
      log.error("save Atributo failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void delete(Atributo entity) throws Exception {
    log.debug("deleting Atributo instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Atributo");
    }

    if (entity.getAtriId() == null) {
      throw new ZMessManager().new EmptyFieldException("atriId");
    }

    findById(entity.getAtriId())
        .ifPresent(
            entidad -> {
              List<AtributoFormulario> atributoFormularios = entidad.getAtributoFormularios();
              if (Utilities.validationsList(atributoFormularios) == true) {
                throw new ZMessManager().new DeletingException("atributoFormularios");
              }
            });

    try {

      atributoRepository.deleteById(entity.getAtriId());
      log.debug("delete Atributo successful");

    } catch (Exception e) {
      log.error("delete Atributo failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void deleteById(String id) throws Exception {
    log.debug("deleting Atributo instance");
    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("atriId");
    }
    if (atributoRepository.findById(id).isPresent()) {
      delete(atributoRepository.findById(id).get());
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Atributo update(Atributo entity) throws Exception {

    log.debug("updating Atributo instance");

    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Atributo");
      }

      validate(entity);

      return atributoRepository.save(entity);

    } catch (Exception e) {
      log.error("update Atributo failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Atributo> findById(String atriId) throws Exception {
    log.debug("getting Atributo instance");
    return atributoRepository.findById(atriId);
  }
}
