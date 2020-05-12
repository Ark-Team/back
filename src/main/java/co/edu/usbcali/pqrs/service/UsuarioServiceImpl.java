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
public class UsuarioServiceImpl implements UsuarioService {

  private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

  @Autowired private UsuarioRepository usuarioRepository;

  @Autowired private Validator validator;

  @Autowired private  UsuarioAreaService usuarioAreaService;


  @Override
  public void validate(Usuario usuario) throws Exception {
    try {
      Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
      if (constraintViolations.size() > 0) {
        StringBuilder strMessage = new StringBuilder();
        for (ConstraintViolation<Usuario> constraintViolation : constraintViolations) {
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
    return usuarioRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Usuario> findAll() {
    log.debug("finding all Usuario instances");
    return usuarioRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Usuario> findAllByCompania(Compania compania) {
    log.debug("finding all Usuario instances");
    return usuarioRepository.findAllByCompania(compania);
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Usuario save(Usuario entity) throws Exception {
    log.debug("saving Usuario instance");
    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Usuario");
      }

      validate(entity);

      if (usuarioRepository.findById(entity.getUsuId()).isPresent()) {
        throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
      }
      entity.setEstado("S");
      entity.setFechaCreacion(new Date());
      return usuarioRepository.save(entity);

    } catch (Exception e) {
      log.error("save Usuario failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void delete(Usuario entity) throws Exception {
    log.debug("deleting Usuario instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Usuario");
    }

    if (entity.getUsuId() == null) {
      throw new ZMessManager().new EmptyFieldException("usuId");
    }

    findById(entity.getUsuId())
        .ifPresent(
            entidad -> {
              List<UsuarioArea> usuarioAreas = entidad.getUsuarioAreas();
              if (Utilities.validationsList(usuarioAreas) == true) {
                throw new ZMessManager().new DeletingException("usuarioAreas");
              }
            });

    try {

      usuarioRepository.deleteById(entity.getUsuId());
      log.debug("delete Usuario successful");

    } catch (Exception e) {
      log.error("delete Usuario failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void deleteById(String id) throws Exception {
    log.debug("deleting Usuario instance");
    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("usuId");
    }
    if (usuarioRepository.findById(id).isPresent()) {
      delete(usuarioRepository.findById(id).get());
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Usuario update(Usuario entity) throws Exception {

    log.debug("updating Usuario instance");

    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Usuario");
      }

      validate(entity);
      entity.setFechaModificacion(new Date());
      return usuarioRepository.save(entity);

    } catch (Exception e) {
      log.error("update Usuario failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Usuario> findById(String usuId) throws Exception {
    log.debug("getting Usuario instance");
    return usuarioRepository.findById(usuId);
  }

  @Override
  public Usuario cambiarEstado(Usuario entity) throws Exception {
    String estado = entity.getEstado();
    if (entity == null) throw new Exception("El tipo usuario es nula");
    this.validate(entity);

    if (estado.equals("N")) {
      entity.setEstado("S");
    }
    if (estado.equals("S")) entity.setEstado("N");
    entity.setFechaModificacion(new Date());
    entity = usuarioRepository.save(entity);
    return entity;
  }
  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Usuario saveConUsuArea(Usuario entity, Area area) throws Exception {
    log.debug("saving Usuario instance");
    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Usuario");
      }

      entity.setEstado("S");
      entity.setFechaCreacion(new Date());
      usuarioRepository.save(entity);
      usuarioAreaService.save(this.buildUsuarioArea(entity,area));
      return entity;


    } catch (Exception e) {
      log.error("save Usuario failed", e);
      throw e;
    }
  }

  @Override
  public List<Usuario> findAllByTipoUsuarioAndCompaniaAndUsuarioArea(TipoUsuario tipoUsuario,
      Compania compania){
    return usuarioRepository.findAllByTipoUsuarioAndCompania(tipoUsuario,compania);
  }

  @Override
  public List<Usuario> findAllUserByArea(Area area){
    List<Usuario> usuarioList = new ArrayList<>();
    List<UsuarioArea> usuarioAreaList = usuarioAreaService.findAllByArea(area);
    for (int i = 0; i < usuarioAreaList.size() ; i++) {
      usuarioList.add(usuarioAreaList.get(i).getUsuario());
    }
    return usuarioList ;
  }
  public UsuarioArea buildUsuarioArea(Usuario usuario,Area area){
    UsuarioArea usuarioArea = new UsuarioArea();
    usuarioArea.setArea(area);
    usuarioArea.setUsuAreaId("");
    usuarioArea.setUsuario(usuario);
    return  usuarioArea;
  }

}

