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
public class FormularioServiceImpl implements FormularioService {

  private static final Logger log = LoggerFactory.getLogger(FormularioServiceImpl.class);

  @Autowired private FormularioRepository formularioRepository;

  @Autowired private Validator validator;

  @Override
  public void validate(Formulario formulario) throws Exception {
    try {
      Set<ConstraintViolation<Formulario>> constraintViolations = validator.validate(formulario);
      if (constraintViolations.size() > 0) {
        StringBuilder strMessage = new StringBuilder();
        for (ConstraintViolation<Formulario> constraintViolation : constraintViolations) {
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
    return formularioRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Formulario> findAll() {
    log.debug("finding all Formulario instances");
    return formularioRepository.findAll();
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Formulario save(Formulario entity) throws Exception {
    log.debug("saving Formulario instance");
    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Formulario");
      }

      return formularioRepository.save(entity);

    } catch (Exception e) {
      log.error("save Formulario failed", e);
      throw e;
    }
  }
  @Override
  @Transactional(readOnly = true)
  public List<Formulario> findAllByCompania(Compania compania){
    return formularioRepository.findAllByCompania(compania);
  }

  @Override
  @Transactional(readOnly = true)
  public Formulario findByCompania(Compania compania) {
    return formularioRepository.findByCompania(compania);
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void delete(Formulario entity) throws Exception {
    log.debug("deleting Formulario instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Formulario");
    }

    if (entity.getFormId() == null) {
      throw new ZMessManager().new EmptyFieldException("formId");
    }

    findById(entity.getFormId())
        .ifPresent(
            entidad -> {
              List<Pqrs> pqrses = entidad.getPqrses();
              if (Utilities.validationsList(pqrses) == true) {
                throw new ZMessManager().new DeletingException("pqrses");
              }
            });

    try {

      formularioRepository.deleteById(entity.getFormId());
      log.debug("delete Formulario successful");

    } catch (Exception e) {
      log.error("delete Formulario failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void deleteById(String id) throws Exception {
    log.debug("deleting Formulario instance");
    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("formId");
    }
    if (formularioRepository.findById(id).isPresent()) {
      delete(formularioRepository.findById(id).get());
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Formulario update(Formulario entity) throws Exception {

    log.debug("updating Formulario instance");

    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Formulario");
      }

      validate(entity);

      return formularioRepository.save(entity);

    } catch (Exception e) {
      log.error("update Formulario failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Formulario> findById(String formId) throws Exception {
    log.debug("getting Formulario instance");
    return formularioRepository.findById(formId);
  }
}
