package co.edu.usbcali.pqrs.service;

import co.edu.usbcali.pqrs.domain.*;
import co.edu.usbcali.pqrs.exception.*;
import co.edu.usbcali.pqrs.repository.*;
import co.edu.usbcali.pqrs.utility.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.*;

import java.util.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@Scope("singleton")
@Service
public class AtributoFormularioServiceImpl implements AtributoFormularioService {
  private static final Logger log = LoggerFactory.getLogger(AtributoFormularioServiceImpl.class);
  @Autowired private AtributoFormularioRepository atributoFormularioRepository;
  @Autowired private Validator validator;


  @Override
  public void validate(AtributoFormulario atributoFormulario) throws Exception {
    try {
      Set<ConstraintViolation<AtributoFormulario>> constraintViolations =
          validator.validate(atributoFormulario);

      if (constraintViolations.size() > 0) {
        StringBuilder strMessage = new StringBuilder();

        for (ConstraintViolation<AtributoFormulario> constraintViolation : constraintViolations) {
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
    return atributoFormularioRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public List<AtributoFormulario> findAll() {
    log.debug("finding all AtributoFormulario instances");

    return atributoFormularioRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public List<AtributoFormulario> findAllByCompania(Compania compania) {

    return atributoFormularioRepository.findAllByCompania(compania);
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public AtributoFormulario save(AtributoFormulario entity) throws Exception {
    log.debug("saving AtributoFormulario instance");

    try {
      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("AtributoFormulario");
      }

      return atributoFormularioRepository.save(entity);
    } catch (Exception e) {
      log.error("save AtributoFormulario failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void delete(AtributoFormulario entity) throws Exception {
    log.debug("deleting AtributoFormulario instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("AtributoFormulario");
    }

    if (entity.getAtrformId() == null) {
      throw new ZMessManager().new EmptyFieldException("atrformId");
    }

    try {
      atributoFormularioRepository.deleteById(entity.getAtrformId());
      log.debug("delete AtributoFormulario successful");
    } catch (Exception e) {
      log.error("delete AtributoFormulario failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void deleteById(String id) throws Exception {
    log.debug("deleting AtributoFormulario instance");

    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("atrformId");
    }

    if (atributoFormularioRepository.findById(id).isPresent()) {
      delete(atributoFormularioRepository.findById(id).get());
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public AtributoFormulario update(AtributoFormulario entity) throws Exception {
    log.debug("updating AtributoFormulario instance");

    try {
      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("AtributoFormulario");
      }

      validate(entity);

      return atributoFormularioRepository.save(entity);
    } catch (Exception e) {
      log.error("update AtributoFormulario failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<AtributoFormulario> findById(String atrformId) throws Exception {
    log.debug("getting AtributoFormulario instance");

    return atributoFormularioRepository.findById(atrformId);
  }
}
