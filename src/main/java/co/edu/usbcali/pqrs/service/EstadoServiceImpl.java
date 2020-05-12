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
public class EstadoServiceImpl implements EstadoService {

  private static final Logger log = LoggerFactory.getLogger(EstadoServiceImpl.class);

  @Autowired private EstadoRepository estadoRepository;

  @Autowired private Validator validator;

  @Override
  public void validate(Estado estado) throws Exception {
    try {
      Set<ConstraintViolation<Estado>> constraintViolations = validator.validate(estado);
      if (constraintViolations.size() > 0) {
        StringBuilder strMessage = new StringBuilder();
        for (ConstraintViolation<Estado> constraintViolation : constraintViolations) {
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
    return estadoRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Estado> findAll() {
    log.debug("finding all Estado instances");
    return estadoRepository.findAll();
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Estado save(Estado entity) throws Exception {
    log.debug("saving Estado instance");
    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Estado");
      }

      entity.setEstado("S");
      entity.setFechaCreacion(new Date());
      return estadoRepository.save(entity);

    } catch (Exception e) {
      log.error("save Estado failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void delete(Estado entity) throws Exception {
    log.debug("deleting Estado instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Estado");
    }

    if (entity.getEstaId() == null) {
      throw new ZMessManager().new EmptyFieldException("estaId");
    }

    findById(entity.getEstaId())
        .ifPresent(
            entidad -> {
              List<DetallePqrs> detallePqrses = entidad.getDetallePqrses();
              if (Utilities.validationsList(detallePqrses) == true) {
                throw new ZMessManager().new DeletingException("detallePqrses");
              }
            });

    try {

      estadoRepository.deleteById(entity.getEstaId());
      log.debug("delete Estado successful");

    } catch (Exception e) {
      log.error("delete Estado failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void deleteById(String id) throws Exception {
    log.debug("deleting Estado instance");
    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("estaId");
    }
    if (estadoRepository.findById(id).isPresent()) {
      delete(estadoRepository.findById(id).get());
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Estado update(Estado entity) throws Exception {

    log.debug("updating Estado instance");

    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Estado");
      }

      validate(entity);

      entity.setFechaModificacion(new Date());
      return estadoRepository.save(entity);

    } catch (Exception e) {
      log.error("update Estado failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Estado> findById(String estaId) throws Exception {
    log.debug("getting Estado instance");
    return estadoRepository.findById(estaId);
  }

  @Override
  public Estado cambiarEstado(Estado entity) throws Exception {
    String estado = entity.getEstado();
    if (entity == null) throw new Exception("El Estado es nula");
    this.validate(entity);

    if (estado.equals("N")) {
      entity.setEstado("S");
    }
    if (estado.equals("S")) entity.setEstado("N");

    entity = estadoRepository.save(entity);
    return entity;
  }
}
