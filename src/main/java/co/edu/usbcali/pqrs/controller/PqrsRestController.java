package co.edu.usbcali.pqrs.controller;

import co.edu.usbcali.pqrs.domain.Area;
import co.edu.usbcali.pqrs.domain.Pqrs;
import co.edu.usbcali.pqrs.dto.PqrsDTO;
import co.edu.usbcali.pqrs.mapper.PqrsMapper;
import co.edu.usbcali.pqrs.service.AreaService;
import co.edu.usbcali.pqrs.service.PqrsService;
import co.edu.usbcali.pqrs.service.ProcesoService;
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
@RequestMapping("/api/pqrs")
@CrossOrigin(origins = "*")
public class PqrsRestController {

  private static final Logger log = LoggerFactory.getLogger(PqrsRestController.class);
  @Autowired
  private PqrsService pqrsService;
  @Autowired
  private PqrsMapper pqrsMapper;
  @Autowired
  private AreaService areaService;
  @Autowired
  private ProcesoService procesoService;

  @GetMapping(value = "/findById/{pqrsId}")
  public ResponseEntity<?> findById(@PathVariable("pqrsId") String pqrsId) {
    log.debug("Request to findById() Pqrs");

    try {
      Pqrs pqrs = pqrsService.findById(pqrsId).get();

      return ResponseEntity.ok().body(pqrsMapper.pqrsToPqrsDTO(pqrs));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity<?> findAll() {
    log.debug("Request to findAll() Pqrs");

    try {
      return ResponseEntity.ok().body(pqrsMapper.listPqrsToListPqrsDTO(pqrsService.findAll()));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAllByArea/{areaId}")
  public ResponseEntity<?> findAllByArea(@PathVariable("areaId") String areaId) {
    log.debug("Request to findAll() Pqrs");

    try {
      Area area = areaService.findById(areaId).get();
      return ResponseEntity.ok()
          .body(pqrsMapper.listPqrsToListPqrsDTO(pqrsService.findAllByArea(area)));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping(value = "/save")
  public ResponseEntity<?> save(@RequestBody PqrsDTO pqrsDTO) {
    log.debug("Request to save Pqrs: {}", pqrsDTO);

    try {
      Pqrs pqrs = pqrsMapper.pqrsDTOToPqrs(pqrsDTO);
      pqrs = pqrsService.save(pqrs);

      return ResponseEntity.ok().body(pqrsMapper.pqrsToPqrsDTO(pqrs));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody PqrsDTO pqrsDTO) {
    log.debug("Request to update Pqrs: {}", pqrsDTO);

    try {
      Pqrs pqrs = pqrsMapper.pqrsDTOToPqrs(pqrsDTO);
      pqrs = pqrsService.update(pqrs);

      return ResponseEntity.ok().body(pqrsMapper.pqrsToPqrsDTO(pqrs));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping(value = "/delete/{pqrsId}")
  public ResponseEntity<?> delete(@PathVariable("pqrsId") String pqrsId) throws Exception {
    log.debug("Request to delete Pqrs");

    try {
      Pqrs pqrs = pqrsService.findById(pqrsId).get();

      pqrsService.delete(pqrs);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(pqrsService.count());
  }

  @GetMapping(value = "/countPQRS/{estado}/{companyId}")
  public ResponseEntity<?> countPQRS(@PathVariable("estado") String estado,
      @PathVariable("companyId") String companyId) {
    return ResponseEntity.ok()
        .body(pqrsService.countAllByEstadoAndCompania_CompId(estado, companyId));
  }

  @GetMapping(value = "/countPqrsByDates")
  public ResponseEntity<?> countPqrsByDates() {
    return ResponseEntity.ok()
        .body(pqrsService.countPqrsByDates());
  }

  @GetMapping(value = "/countAreaPQRS/{estado}/{areaId}")
  public ResponseEntity<?> countAllByEstadoAndAndArea_AreaId(@PathVariable("estado") String estado,
      @PathVariable("areaId") String areaId) {
    return ResponseEntity.ok()
        .body(pqrsService.countAllByEstadoAndAndArea_AreaId(estado,areaId));
  }
}
