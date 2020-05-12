package co.edu.usbcali.pqrs.service;

import co.edu.usbcali.pqrs.domain.*;
import co.edu.usbcali.pqrs.exception.*;
import co.edu.usbcali.pqrs.repository.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@Scope("singleton")
@Service
public class UsuarioAreaServiceImpl implements UsuarioAreaService {
  private static final Logger log = LoggerFactory.getLogger(UsuarioAreaServiceImpl.class);
  @Autowired private UsuarioAreaRepository usuarioAreaRepository;
  @Autowired private Validator validator;
  @Autowired private UsuarioRepository usuarioRepository;

  @Override
  public void validate(UsuarioArea usuarioArea) throws Exception {
    try {
      Set<ConstraintViolation<UsuarioArea>> constraintViolations = validator.validate(usuarioArea);

      if (constraintViolations.size() > 0) {
        StringBuilder strMessage = new StringBuilder();

        for (ConstraintViolation<UsuarioArea> constraintViolation : constraintViolations) {
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
    return usuarioAreaRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public List<UsuarioArea> findAll() {
    log.debug("finding all UsuarioArea instances");

    return usuarioAreaRepository.findAll();
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public UsuarioArea save(UsuarioArea entity) throws Exception {
    log.debug("saving UsuarioArea instance");

    try {
      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("UsuarioArea");
      }

      return usuarioAreaRepository.save(entity);
    } catch (Exception e) {
      log.error("save UsuarioArea failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void delete(UsuarioArea entity) throws Exception {
    log.debug("deleting UsuarioArea instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("UsuarioArea");
    }

    if (entity.getUsuAreaId() == null) {
      throw new ZMessManager().new EmptyFieldException("usuAreaId");
    }

    try {
      usuarioAreaRepository.deleteById(entity.getUsuAreaId());
      log.debug("delete UsuarioArea successful");
    } catch (Exception e) {
      log.error("delete UsuarioArea failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void deleteById(String id) throws Exception {
    log.debug("deleting UsuarioArea instance");

    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("usuAreaId");
    }

    if (usuarioAreaRepository.findById(id).isPresent()) {
      delete(usuarioAreaRepository.findById(id).get());
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public UsuarioArea update(UsuarioArea entity) throws Exception {
    log.debug("updating UsuarioArea instance");

    try {
      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("UsuarioArea");
      }

      validate(entity);

      return usuarioAreaRepository.save(entity);
    } catch (Exception e) {
      log.error("update UsuarioArea failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<UsuarioArea> findById(String usuAreaId) throws Exception {
    log.debug("getting UsuarioArea instance");

    return usuarioAreaRepository.findById(usuAreaId);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<UsuarioArea> findByUsuario(String usuAreaId) throws Exception {
    log.debug("getting UsuarioArea instance");
    Usuario usuario = usuarioRepository.findById(usuAreaId).get();
    return usuarioAreaRepository.findByUsuario(usuario);
  }

  @Override
  @Transactional(readOnly = true)
  public List<UsuarioArea> findAllByArea(Area area) {
    return usuarioAreaRepository.findAllByArea(area);
  }
}
