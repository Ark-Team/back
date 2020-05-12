package co.edu.usbcali.pqrs.service;

import co.edu.usbcali.pqrs.domain.Area;
import co.edu.usbcali.pqrs.domain.Cliente;
import co.edu.usbcali.pqrs.domain.Compania;
import co.edu.usbcali.pqrs.domain.DetallePqrs;
import co.edu.usbcali.pqrs.domain.Pqrs;
import co.edu.usbcali.pqrs.domain.Proceso;
import co.edu.usbcali.pqrs.domain.TipoUsuario;
import co.edu.usbcali.pqrs.dto.ClienteDTO;
import co.edu.usbcali.pqrs.dto.DetallePqrsDTO;
import co.edu.usbcali.pqrs.dto.FechaCantidad;
import co.edu.usbcali.pqrs.exception.ZMessManager;
import co.edu.usbcali.pqrs.mapper.ClienteMapper;
import co.edu.usbcali.pqrs.mapper.DetallePqrsMapper;
import co.edu.usbcali.pqrs.repository.PqrsRepository;
import co.edu.usbcali.pqrs.utility.Utilities;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@Scope("singleton")
@Service
public class PqrsServiceImpl implements PqrsService {

  private static final Logger log = LoggerFactory.getLogger(PqrsServiceImpl.class);

  @Autowired private TipoUsuarioService tipoUsuarioService;
  @Autowired private DetallePqrsService detallePqrsService;
  @Autowired private DetallePqrsMapper detallePqrsMapper;
  @Autowired private ClienteMapper clienteMapper;
  @Autowired private ClienteService clienteService;
  @Autowired
  private JavaMailSender mailSender;
  private String tipoUsuarioId = "5ea5008514da130060e5e422";
  private String estadoId = "1";
  @PersistenceContext
  EntityManager em;
  @Autowired private PqrsRepository pqrsRepository;

  @Autowired private Validator validator;

  @Override
  public void validate(Pqrs pqrs) throws Exception {
    try {
      Set<ConstraintViolation<Pqrs>> constraintViolations = validator.validate(pqrs);
      if (constraintViolations.size() > 0) {
        StringBuilder strMessage = new StringBuilder();
        for (ConstraintViolation<Pqrs> constraintViolation : constraintViolations) {
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
    return pqrsRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public Long countAllByEstadoAndCompania_CompId(String estado, String companyId){
    return pqrsRepository.countAllByEstadoAndCompania_CompId(estado,companyId);
  }
  @Override
  @Transactional(readOnly = true)
  public Long countAllByEstadoAndAndArea_AreaId(String estado, String areaId){
    return pqrsRepository.countAllByEstadoAndArea_AreaId(estado,areaId);
  }

  @Override
  public List<FechaCantidad> countPqrsByDates(){
    String q =("select dates.day_fmonth as dia,CASE WHEN r.n is NULL THEN 0 ELSE r.n END as cantidad  from dates left join (select count(p) as n,p.fecha_creacion from pqrs p\n"
        + "GROUP BY EXTRACT(YEAR FROM fecha_creacion),EXTRACT(MONTH FROM fecha_creacion),\n"
        + " EXTRACT(DAY FROM fecha_creacion),fecha_creacion) as r\n"
        + " ON dates.year = EXTRACT(YEAR FROM fecha_creacion) \n"
        + " AND dates.month_fyear = EXTRACT(MONTH FROM fecha_creacion)\n"
        + " AND dates.day_fmonth = EXTRACT(DAY FROM fecha_creacion)\n"
        + " where dates.date_id between NOW() - INTERVAL '7' day and now()\n"
        + " ORDER BY dates.date_id");


    return  em.createNativeQuery(q).getResultList();
  }
  @Override
  @Transactional(readOnly = true)
  public List<Pqrs> findAll() {
    log.debug("finding all Pqrs instances");
    return pqrsRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Pqrs> findAllByArea(Area area) {
    log.debug("finding all Pqrs instances");
    return pqrsRepository.findAllByArea(area);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Pqrs> findAllByAreaAndProceso(Area area, Proceso proceso) {
    log.debug("finding all Pqrs instances");
    return pqrsRepository.findAllByAreaAndProceso(area, proceso);
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Pqrs save(Pqrs entity) throws Exception {
    log.debug("saving Pqrs instance");
    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Pqrs");
      }

      Cliente cliente = clienteService.save(this.buildCliente(entity));
      entity.setCliente(cliente);
      entity.setEstado("Abierta");
      Pqrs pqrs = pqrsRepository.save(entity);
      DetallePqrs detallePqrs = this.buildDetalle(pqrs);
      detallePqrsService.save(detallePqrs);
      this.sendMail(entity.getEmail(),"Seguimiento de pqrs" + "\n",
          "Radicado: " + entity.getPqrsId() + "\n" + "\n" +
              "Los datos son los siguientes: " + "\n" +
              "Nombre: " + entity.getNombre()+ "\n" +
              "Telefono: "+ entity.getTelefono() + "\n" + "\n" +
              "Ingresa con el radicado a la siguiente direccion: https://seguimiento-arkteam.web.app/ "+ "\n" + "\n" +
              "Si no hiciste esto, por favor contactanos de inmediato.");
      return entity;

    } catch (Exception e) {
      log.error("save Pqrs failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void delete(Pqrs entity) throws Exception {
    log.debug("deleting Pqrs instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Pqrs");
    }

    if (entity.getPqrsId() == null) {
      throw new ZMessManager().new EmptyFieldException("pqrsId");
    }

    findById(entity.getPqrsId())
        .ifPresent(
            entidad -> {
              List<DetallePqrs> detallePqrses = entidad.getDetallePqrses();
              if (Utilities.validationsList(detallePqrses) == true) {
                throw new ZMessManager().new DeletingException("detallePqrses");
              }
            });

    try {

      pqrsRepository.deleteById(entity.getPqrsId());
      log.debug("delete Pqrs successful");

    } catch (Exception e) {
      log.error("delete Pqrs failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void deleteById(String id) throws Exception {
    log.debug("deleting Pqrs instance");
    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("pqrsId");
    }
    if (pqrsRepository.findById(id).isPresent()) {
      delete(pqrsRepository.findById(id).get());
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Pqrs update(Pqrs entity) throws Exception {

    log.debug("updating Pqrs instance");

    try {

      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Pqrs");
      }

      validate(entity);

      return pqrsRepository.save(entity);

    } catch (Exception e) {
      log.error("update Pqrs failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Pqrs> findById(String pqrsId) throws Exception {
    log.debug("getting Pqrs instance");
    if (!pqrsRepository.findById(pqrsId).isPresent()){
      throw new ZMessManager().new EmptyFieldException("pqrsId No existe");
    }
    return pqrsRepository.findById(pqrsId);
  }

  public DetallePqrs buildDetalle(Pqrs pqrs) throws Exception {
    TipoUsuario tipoUsuario = tipoUsuarioService.findById(this.tipoUsuarioId).get();
    Compania compania = pqrs.getCompania();
    Area area = pqrs.getArea();
    DetallePqrsDTO detallePqrsDTO = new DetallePqrsDTO();
    detallePqrsDTO.setFechaCreacion(pqrs.getFechaCreacion());
    detallePqrsDTO.setUsuCreador(detallePqrsService.asignarUsuario(compania, tipoUsuario,area));
    detallePqrsDTO.setEstaId_Estado(estadoId);
    detallePqrsDTO.setPrioId_Prioridad("1");
    detallePqrsDTO.setPqrsId_Pqrs(pqrs.getPqrsId());
    DetallePqrs detallePqrs = detallePqrsMapper.detallePqrsDTOToDetallePqrs(detallePqrsDTO);

    return detallePqrs;
  }

  public void sendMail(String to, String subject, String content) {
    SimpleMailMessage email = new SimpleMailMessage();
    email.setFrom("arkgroupteam@gmail.com");
    email.setTo(to);
    email.setSubject(subject);
    email.setText(content);
    mailSender.send(email);
  }
  public Cliente buildCliente(Pqrs pqrs) throws Exception {
    ClienteDTO clienteDTO = new ClienteDTO();
    clienteDTO.setNombre(pqrs.getNombre());
    clienteDTO.setEmail(pqrs.getEmail());
    clienteDTO.setTelefono(pqrs.getTelefono());
    Cliente cliente = clienteMapper.clienteDTOToCliente(clienteDTO);
    return cliente;
  }
}
