package co.edu.usbcali.pqrs.service;

import java.io.IOException;
import java.math.*;
import java.util.*;

import co.edu.usbcali.pqrs.dto.UsuarioDTO;
import co.edu.usbcali.pqrs.mapper.UsuarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import co.edu.usbcali.pqrs.exception.*;
import co.edu.usbcali.pqrs.repository.*;
import co.edu.usbcali.pqrs.utility.Utilities;

import co.edu.usbcali.pqrs.domain.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@Scope("singleton")
@Service
public class CompaniaServiceImpl implements CompaniaService {

  private static final Logger log = LoggerFactory.getLogger(CompaniaServiceImpl.class);

  @Autowired private UsuarioService usuarioService;

  @Autowired private UsuarioMapper usuarioMapper;

  @Autowired private CompaniaRepository companiaRepository;

  @Autowired private Validator validator;

  @Autowired
  private JavaMailSender mailSender;

  public void sendMail(String to, String subject, String content) {
    SimpleMailMessage email = new SimpleMailMessage();
    email.setFrom("arkgroupteam@gmail.com");
    email.setTo(to);
    email.setSubject(subject);
    email.setText(content);
    mailSender.send(email);
  }

  @Override
  public void validate(Compania compania) throws Exception {
    try {
      Set<ConstraintViolation<Compania>> constraintViolations = validator.validate(compania);
      if (constraintViolations.size() > 0) {
        StringBuilder strMessage = new StringBuilder();
        for (ConstraintViolation<Compania> constraintViolation : constraintViolations) {
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
    return companiaRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Compania> findAll() {
    log.debug("finding all Compania instances");
    return companiaRepository.findAll();
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Compania save(Compania entity) throws Exception {
    log.debug("saving Compania instance");
    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Compania");
      }

      validate(entity);

      if (companiaRepository.findById(entity.getCompId()).isPresent()) {
        throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
      }

      entity.setEstado("S");
      entity.setFechaCreacion(new Date());
      companiaRepository.save(entity);
      Usuario usuario = buildUser(entity);
      usuarioService.save(usuario);
      this.sendMail(entity.getEmail(),"Creacion de cuenta" + "\n",
              "¡Queremos felicitarte por querer ser parte de nuestro grupo!"+ "\n"+
                      "NIT: " + entity.getCompId() + "\n" + "\n" +
                      "Los datos son los siguientes: " + "\n" +
                      "Nombre Compañia: " + entity.getNombre()+ "\n" +
                      "Direccion: "+ entity.getDireccion() + "\n" +
                      "Telefono: "+ entity.getTelefono() + "\n" + "\n" +
                      "Este es tu usuario y contraseña: " + "\n" + "\n" +
                      "UserName: "+ usuario.getUsuId() + "\n" +
                      "Contraseña: "+ usuario.getClave() + "\n" + "\n" +
                      "Si no hiciste esto, por favor contactanos de inmediato.");
      return entity;

    } catch (Exception e) {
      log.error("save Compania failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void delete(Compania entity) throws Exception {
    log.debug("deleting Compania instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Compania");
    }

    if (entity.getCompId() == null) {
      throw new ZMessManager().new EmptyFieldException("compId");
    }

    findById(entity.getCompId())
        .ifPresent(
            entidad -> {
              List<Area> areas = entidad.getAreas();
              if (Utilities.validationsList(areas) == true) {
                throw new ZMessManager().new DeletingException("areas");
              }
              List<AtributoFormulario> atributoFormularios = entidad.getAtributoFormularios();
              if (Utilities.validationsList(atributoFormularios) == true) {
                throw new ZMessManager().new DeletingException("atributoFormularios");
              }
              List<Formulario> formularios = entidad.getFormularios();
              if (Utilities.validationsList(formularios) == true) {
                throw new ZMessManager().new DeletingException("formularios");
              }
              List<Pqrs> pqrses = entidad.getPqrses();
              if (Utilities.validationsList(pqrses) == true) {
                throw new ZMessManager().new DeletingException("pqrses");
              }
              List<Usuario> usuarios = entidad.getUsuarios();
              if (Utilities.validationsList(usuarios) == true) {
                throw new ZMessManager().new DeletingException("usuarios");
              }
            });

    try {

      companiaRepository.deleteById(entity.getCompId());
      log.debug("delete Compania successful");

    } catch (Exception e) {
      log.error("delete Compania failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void deleteById(String id) throws Exception {
    log.debug("deleting Compania instance");
    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("compId");
    }
    if (companiaRepository.findById(id).isPresent()) {
      delete(companiaRepository.findById(id).get());
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Compania update(Compania entity) throws Exception {

    log.debug("updating Compania instance");

    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Compania");
      }

      validate(entity);
      entity.setFechaModificacion(new Date());
      return companiaRepository.save(entity);

    } catch (Exception e) {
      log.error("update Compania failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Compania> findById(String compId) throws Exception {
    log.debug("getting Compania instance");
    return companiaRepository.findById(compId);
  }

  @Override
  public Compania cambiarEstado(Compania entity) throws Exception {
    String estado = entity.getEstado();
    if (entity == null) throw new Exception("La compañia es nula");
    this.validate(entity);

    if (estado.equals("N")) {
      entity.setEstado("S");
    }
    if (estado.equals("S")) entity.setEstado("N");

    entity = companiaRepository.save(entity);
    return entity;
  }

  public Usuario buildUser(Compania compania) throws Exception {
    UsuarioDTO usuarioDTO = new UsuarioDTO();
    usuarioDTO.setCompId_Compania(compania.getCompId());
    usuarioDTO.setNombre("User " + compania.getNombre());
    usuarioDTO.setUsuId(compania.getNombre().replaceAll(" ", ""));
    usuarioDTO.setClave(compania.getNombre().replaceAll(" ", ""));
    usuarioDTO.setFechaCreacion(new Date());
    usuarioDTO.setEstado("S");
    usuarioDTO.setTusuId_TipoUsuario("5ea5003214da130060e5e421");
    Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);

    return usuario;
  }
}
