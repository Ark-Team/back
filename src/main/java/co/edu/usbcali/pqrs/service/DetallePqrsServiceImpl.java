package co.edu.usbcali.pqrs.service;

import co.edu.usbcali.pqrs.domain.Area;
import co.edu.usbcali.pqrs.domain.Compania;
import co.edu.usbcali.pqrs.domain.DetallePqrs;
import co.edu.usbcali.pqrs.domain.Estado;
import co.edu.usbcali.pqrs.domain.Pqrs;
import co.edu.usbcali.pqrs.domain.Proceso;
import co.edu.usbcali.pqrs.domain.TipoUsuario;
import co.edu.usbcali.pqrs.domain.Usuario;
import co.edu.usbcali.pqrs.domain.UsuarioArea;
import co.edu.usbcali.pqrs.dto.DetallePqrsDTO;
import co.edu.usbcali.pqrs.exception.ZMessManager;
import co.edu.usbcali.pqrs.mapper.DetallePqrsMapper;
import co.edu.usbcali.pqrs.repository.DetallePqrsRepository;
import co.edu.usbcali.pqrs.repository.EstadoRepository;
import co.edu.usbcali.pqrs.repository.PqrsRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 */
@Scope("singleton")
@Service
public class DetallePqrsServiceImpl implements DetallePqrsService {

  private static final Logger log = LoggerFactory.getLogger(DetallePqrsServiceImpl.class);
  @Autowired
  private DetallePqrsRepository detallePqrsRepository;
  @Autowired
  private Validator validator;
  @Autowired
  private UsuarioService usuarioService;
  @Autowired
  private TipoUsuarioService tipoUsuarioService;
  @Autowired
  private EstadoService estadoService;
  @Autowired
  private UsuarioAreaService usuarioAreaService;
  @Autowired
  private DetallePqrsMapper detallePqrsMapper;
  @Autowired
  private PqrsRepository pqrsRepository;
  @Autowired
  private EstadoRepository estadoRepository;
  private String tipoUsuarioId = "5ea5008514da130060e5e422";
  private String estadoId = "1";
  private String estadoRedireccionado = "4";

  @Override
  public void validate(DetallePqrs detallePqrs) throws Exception {
    try {
      Set<ConstraintViolation<DetallePqrs>> constraintViolations = validator.validate(detallePqrs);

      if (constraintViolations.size() > 0) {
        StringBuilder strMessage = new StringBuilder();

        for (ConstraintViolation<DetallePqrs> constraintViolation : constraintViolations) {
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
    return detallePqrsRepository.count();
  }

  @Override
  @Transactional(readOnly = true)
  public List<DetallePqrs> findAll() {
    log.debug("finding all DetallePqrs instances");

    return detallePqrsRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public List<DetallePqrs> findAllByPqrs(Pqrs pqrs) {
    return detallePqrsRepository.findAllByPqrs(pqrs);
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public DetallePqrs findByUsuCreadorAndPqrs_PqrsId(String usuCreador, String pqrsId){
    return detallePqrsRepository.findByUsuCreadorAndPqrs_PqrsId(usuCreador, pqrsId);
  }
  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public DetallePqrs findByUsuCreadorAndPqrs_PqrsIdestadoRedireccionado(String usuCreador, String pqrsId){
    return detallePqrsRepository.findByUsuCreadorAndPqrs_PqrsIdAndEstado_EstaId(usuCreador, pqrsId,estadoRedireccionado);
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public DetallePqrs save(DetallePqrs entity) throws Exception {
    log.debug("saving DetallePqrs instance");

    try {
      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("DetallePqrs");
      }

      TipoUsuario tipoUsuario = tipoUsuarioService.findById(this.tipoUsuarioId).get();
      Compania compania = entity.getPqrs().getCompania();
      Area area = entity.getPqrs().getArea();
      Estado estado = estadoService.findById(this.estadoId).get();
      entity.setEstado(estado);

      return detallePqrsRepository.save(entity);

    } catch (Exception e) {
      log.error("save DetallePqrs failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void delete(DetallePqrs entity) throws Exception {
    log.debug("deleting DetallePqrs instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("DetallePqrs");
    }

    if (entity.getDpqrsId() == null) {
      throw new ZMessManager().new EmptyFieldException("dpqrsId");
    }

    try {
      detallePqrsRepository.deleteById(entity.getDpqrsId());
      log.debug("delete DetallePqrs successful");
    } catch (Exception e) {
      log.error("delete DetallePqrs failed", e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void deleteById(String id) throws Exception {
    log.debug("deleting DetallePqrs instance");

    if (id == null) {
      throw new ZMessManager().new EmptyFieldException("dpqrsId");
    }

    if (detallePqrsRepository.findById(id).isPresent()) {
      delete(detallePqrsRepository.findById(id).get());
    }
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public DetallePqrs update(DetallePqrs entity) throws Exception {
    log.debug("updating DetallePqrs instance");

    try {
      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("DetallePqrs");
      }
      Pqrs pqrs = pqrsRepository.findById(entity.getPqrs().getPqrsId()).get();
      Estado estado = estadoService.findById(entity.getEstado().getEstaId()).get();
      pqrs.setEstado(estado.getNombre());
      pqrsRepository.save(pqrs);
      entity.setFechaModificacion(new Date());
      return detallePqrsRepository.save(entity);
    } catch (Exception e) {
      log.error("update DetallePqrs failed", e);
      throw e;
    }
  }


  @Override
  @Transactional(readOnly = true)
  public Optional<DetallePqrs> findById(String dpqrsId) throws Exception {
    log.debug("getting DetallePqrs instance");

    return detallePqrsRepository.findById(dpqrsId);
  }
  @Override
  public DetallePqrs updateById(String entity) throws Exception {
    log.debug("updating DetallePqrs instance");

    try {
      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("DetallePqrs");
      }
      DetallePqrs detallePqrs = detallePqrsRepository.findById(entity).get();
      detallePqrs.setFechaModificacion(new Date());
      Pqrs pqrs = pqrsRepository.findById(detallePqrs.getPqrs().getPqrsId()).get();
      pqrs.setEstado(detallePqrs.getEstado().getNombre());
      pqrsRepository.save(pqrs);
      return detallePqrsRepository.save(detallePqrs);
    } catch (Exception e) {
      log.error("update DetallePqrs failed", e);
      throw e;
    }
  }

  @Override
  public DetallePqrs redireccionPqrs(DetallePqrs detallePqrs, Area area, Proceso proceso) throws Exception {
    Pqrs pqrs = pqrsRepository.findById(detallePqrs.getPqrs().getPqrsId()).get();
    pqrs.setArea(area);
    pqrs.setProceso(proceso);
    pqrsRepository.save(pqrs);
    detallePqrs.setEstado(estadoService.findById("4").get());
    detallePqrs.setFechaModificacion(new Date());
    update(detallePqrs);
    DetallePqrs detallePqrsBuild = buildDetalle(detallePqrs, area);
    if(detallePqrs.getUsuCreador().equals(detallePqrsBuild.getUsuCreador())){
      updateById(detallePqrs.getDpqrsId());
    }else{
      return this.save(detallePqrsBuild);
    }
    throw new ZMessManager().new NullEntityExcepcion("DetallePqrs");
  }

  @Override
  /*@Transactional(readOnly = true)*/
  public String asignarUsuario(Compania compania, TipoUsuario tipoUsuario, Area area) {
    LinkedHashMap<String, Integer> usuarios = this.cantidadPqrsAsesor(compania, tipoUsuario, area);
    String minEntry = Collections.min(usuarios.entrySet(), Map.Entry.comparingByValue()).getKey();
    return minEntry;
  }


  @Override
  @Transactional(readOnly = true)
  public List<Pqrs> findPqrsByUser(String user) {
    List<DetallePqrs> detallePqrs = detallePqrsRepository.findAllByUsuCreador(user);
    List<Pqrs> pqrsList = new ArrayList<>();
    for (int i = 0; i < detallePqrs.size(); i++) {
      if (!(detallePqrs.get(i).getEstado().getEstaId().equals(estadoRedireccionado) || detallePqrs.get(i).getEstado().getEstaId().equals("3") )){
        pqrsList.add(detallePqrs.get(i).getPqrs());
      }
    }
    return pqrsList;
  }

  @Override
  @Transactional(readOnly = true)
  public List<Pqrs> findHistorialPqrsByUser(String user) {
    List<DetallePqrs> detallePqrs = detallePqrsRepository.findAllByUsuCreador(user);
    List<Pqrs> pqrsList = new ArrayList<>();
    for (int i = 0; i < detallePqrs.size(); i++) {
      if (detallePqrs.get(i).getEstado().getEstaId().equals("3")||detallePqrs.get(i).getEstado().getEstaId().equals("4"))
        pqrsList.add(detallePqrs.get(i).getPqrs());
    }
    return pqrsList;
  }
  /*@Override
  @Transactional(readOnly = true)*/
  public LinkedHashMap<String, Integer> cantidadPqrsAsesor(
      Compania compania, TipoUsuario tipoUsuario, Area area) {
    List<Usuario> usuarios =
        usuarioService.findAllByTipoUsuarioAndCompaniaAndUsuarioArea(tipoUsuario, compania);
    List<UsuarioArea> usuarioAreas = usuarioAreaService.findAllByArea(area);
    LinkedHashMap<String, Integer> usucantidad = new LinkedHashMap<String, Integer>();
    for (int i = 0; i < usuarios.size(); i++) {
      for (int j = 0; j < usuarioAreas.size(); j++) {
        if ((usuarios.get(i).getUsuId().equals(usuarioAreas.get(j).getUsuario().getUsuId()))
            && (usuarioAreas.get(j).getArea().getAreaId().equals(area.getAreaId()))) {
          List<DetallePqrs> detallePqrs =
              detallePqrsRepository.findAllByUsuCreador(usuarios.get(i).getUsuId());
          usucantidad.put(usuarios.get(i).getUsuId(), detallePqrs.size());
        }
      }
    }
    return usucantidad;
  }

  public DetallePqrs buildDetalle(DetallePqrs detallePqrs, Area area) throws Exception {
    TipoUsuario tipoUsuario = tipoUsuarioService.findById(this.tipoUsuarioId).get();
    Pqrs pqrs = pqrsRepository.findById(detallePqrs.getPqrs().getPqrsId()).get();
    Compania compania = pqrs.getCompania();
    DetallePqrsDTO detallePqrsDTO = new DetallePqrsDTO();
    detallePqrsDTO.setFechaCreacion(pqrs.getFechaCreacion());
    detallePqrsDTO.setFechaModificacion(new Date());
    detallePqrsDTO.setUsuCreador(this.asignarUsuario(compania, tipoUsuario, area));
    detallePqrsDTO.setEstaId_Estado(detallePqrs.getEstado().getEstaId());
    detallePqrsDTO.setPrioId_Prioridad("1");
    detallePqrsDTO.setPqrsId_Pqrs(pqrs.getPqrsId());
    detallePqrsDTO.setDpqrsId("");
    detallePqrsDTO.setDescripcion("");
    detallePqrsDTO.setUsuModificador("");
    DetallePqrs detallePqrs1 = detallePqrsMapper.detallePqrsDTOToDetallePqrs(detallePqrsDTO);

    return detallePqrs1;
  }

  @Override
  @Transactional(readOnly = true)
  public Long findAllByEstado_EstaIdAndUsuCreador(String estado, String usuId){
    return detallePqrsRepository.countAllByEstado_EstaIdAndUsuCreador(estado,usuId);
  }
}
