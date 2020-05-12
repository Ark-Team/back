package co.edu.usbcali.pqrs.controller;

import co.edu.usbcali.pqrs.domain.Area;
import co.edu.usbcali.pqrs.domain.DetallePqrs;
import co.edu.usbcali.pqrs.domain.Pqrs;
import co.edu.usbcali.pqrs.domain.Proceso;
import co.edu.usbcali.pqrs.dto.DetallePqrsDTO;
import co.edu.usbcali.pqrs.dto.PqrsDTO;
import co.edu.usbcali.pqrs.mapper.DetallePqrsMapper;
import co.edu.usbcali.pqrs.mapper.PqrsMapper;
import co.edu.usbcali.pqrs.service.AreaService;
import co.edu.usbcali.pqrs.service.DetallePqrsService;
import co.edu.usbcali.pqrs.service.PqrsService;
import co.edu.usbcali.pqrs.service.ProcesoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/detallePqrs")
@CrossOrigin(origins = "*")
public class DetallePqrsRestController {

  private static final Logger log = LoggerFactory.getLogger(DetallePqrsRestController.class);
  @Autowired
  private DetallePqrsService detallePqrsService;
  @Autowired
  private DetallePqrsMapper detallePqrsMapper;
  @Autowired
  private PqrsMapper pqrsMapper;
  @Autowired
  private AreaService areaService;

  @Autowired
  private ProcesoService procesoService;
  @Autowired
  private PqrsService pqrsService;



  @GetMapping(value = "/findById/{dpqrsId}")
  public ResponseEntity<?> findById(@PathVariable("dpqrsId") String dpqrsId) {
    log.debug("Request to findById() DetallePqrs");

    try {
      DetallePqrs detallePqrs = detallePqrsService.findById(dpqrsId).get();

      return ResponseEntity.ok().body(detallePqrsMapper.detallePqrsToDetallePqrsDTO(detallePqrs));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/findByUsuPqrsId/{user}/{pqrsId}")
  public ResponseEntity<?> findByUsuPqrsIdRedireccionado(@PathVariable("pqrsId") String pqrsId,
      @PathVariable("user") String usuCreador) throws Exception {
    DetallePqrs detallePqrs = detallePqrsService.findByUsuCreadorAndPqrs_PqrsIdestadoRedireccionado(usuCreador,pqrsId);
    return ResponseEntity.ok().body(detallePqrsMapper.detallePqrsToDetallePqrsDTO(detallePqrs));
  }

  @GetMapping("/findDetalleByUsuPqrsId/{user}/{pqrsId}")
  public ResponseEntity<?> findByUsuPqrsId(@PathVariable("pqrsId") String pqrsId,
      @PathVariable("user") String usuCreador) throws Exception {
    DetallePqrs detallePqrs = detallePqrsService.findByUsuCreadorAndPqrs_PqrsId(usuCreador,pqrsId);
    return ResponseEntity.ok().body(detallePqrsMapper.detallePqrsToDetallePqrsDTO(detallePqrs));
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity<?> findAll() {
    log.debug("Request to findAll() DetallePqrs");

    try {
      return ResponseEntity.ok()
          .body(
              detallePqrsMapper.listDetallePqrsToListDetallePqrsDTO(detallePqrsService.findAll()));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAllByPqrs/{pqrsId}")
  public ResponseEntity<?> findAllByPqrs(@PathVariable("pqrsId") String pqrsId) {
    log.debug("Request to findAllByPqrs() DetallePqrs");
    try {
      Pqrs pqrs = pqrsService.findById(pqrsId).get();
      return ResponseEntity.ok()
          .body(
              detallePqrsMapper.listDetallePqrsToListDetallePqrsDTO(detallePqrsService.findAllByPqrs(pqrs)));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }


  @PostMapping(value = "/save")
  public ResponseEntity<?> save(@RequestBody DetallePqrsDTO detallePqrsDTO) {
    log.debug("Request to save DetallePqrs: {}", detallePqrsDTO);

    try {
      DetallePqrs detallePqrs = detallePqrsMapper.detallePqrsDTOToDetallePqrs(detallePqrsDTO);
      detallePqrs = detallePqrsService.save(detallePqrs);

      return ResponseEntity.ok().body(detallePqrsMapper.detallePqrsToDetallePqrsDTO(detallePqrs));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping(value = "/redireccionPqrs/{areaId}/{prosId}")
  public ResponseEntity<?> redireccionPqrs(@RequestBody DetallePqrsDTO detallePqrs,
      @PathVariable("areaId") String areaId, @PathVariable("prosId") String prosId) {

    try {
      DetallePqrs detallePqrsMapeado = detallePqrsMapper.detallePqrsDTOToDetallePqrs(detallePqrs);
      Area area = areaService.findById(areaId).get();
      Proceso proceso = procesoService.findById(prosId).get();
      return ResponseEntity.ok().body(detallePqrsMapper.detallePqrsToDetallePqrsDTO(detallePqrsService.redireccionPqrs(detallePqrsMapeado, area,proceso)));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody DetallePqrsDTO detallePqrsDTO) {
    log.debug("Request to update DetallePqrs: {}", detallePqrsDTO);

    try {
      DetallePqrs detallePqrs = detallePqrsMapper.detallePqrsDTOToDetallePqrs(detallePqrsDTO);
      detallePqrs = detallePqrsService.update(detallePqrs);

      return ResponseEntity.ok().body(detallePqrsMapper.detallePqrsToDetallePqrsDTO(detallePqrs));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping(value = "/delete/{dpqrsId}")
  public ResponseEntity<?> delete(@PathVariable("dpqrsId") String dpqrsId) throws Exception {
    log.debug("Request to delete DetallePqrs");

    try {
      DetallePqrs detallePqrs = detallePqrsService.findById(dpqrsId).get();

      detallePqrsService.delete(detallePqrs);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(detallePqrsService.count());
  }

  @GetMapping(value = "/findPqrsByUser/{userName}")
  public ResponseEntity<?> findPqrsByUser(@PathVariable("userName") String userName)
      throws Exception {
    List<PqrsDTO> pqrsDTOList = pqrsMapper
        .listPqrsToListPqrsDTO(detallePqrsService.findPqrsByUser(userName));
    return ResponseEntity.ok().body(pqrsDTOList);
  }

  @GetMapping(value = "/findHistorialPqrsByUser/{userName}")
  public ResponseEntity<?> findHistorialPqrsByUser(@PathVariable("userName") String userName)
      throws Exception {
    List<PqrsDTO> pqrsDTOList = pqrsMapper
        .listPqrsToListPqrsDTO(detallePqrsService.findHistorialPqrsByUser(userName));
    return ResponseEntity.ok().body(pqrsDTOList);
  }
  @GetMapping(value = "/countUsuarioPQRS/{estado}/{usuId}")
  public ResponseEntity<?> findAllByEstado_EstaIdAndUsuCreador(@PathVariable("estado") String estado,
      @PathVariable("usuId") String usuId) {
    return ResponseEntity.ok()
        .body(detallePqrsService.findAllByEstado_EstaIdAndUsuCreador(estado,usuId));
  }
}
