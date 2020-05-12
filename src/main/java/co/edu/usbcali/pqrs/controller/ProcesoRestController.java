package co.edu.usbcali.pqrs.controller;

import co.edu.usbcali.pqrs.domain.*;
import co.edu.usbcali.pqrs.dto.ProcesoDTO;
import co.edu.usbcali.pqrs.mapper.ProcesoMapper;
import co.edu.usbcali.pqrs.service.AreaService;
import co.edu.usbcali.pqrs.service.CompaniaService;
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
@RequestMapping("/api/proceso")
@CrossOrigin(origins = "*")
public class ProcesoRestController {
  private static final Logger log = LoggerFactory.getLogger(ProcesoRestController.class);
  @Autowired private ProcesoService procesoService;
  @Autowired private ProcesoMapper procesoMapper;
  @Autowired private AreaService areaService;
  @Autowired private CompaniaService companiaService;

  @GetMapping(value = "/findById/{prosId}")
  public ResponseEntity<?> findById(@PathVariable("prosId") String prosId) {
    log.debug("Request to findById() Proceso");

    try {
      Proceso proceso = procesoService.findById(prosId).get();

      return ResponseEntity.ok().body(procesoMapper.procesoToProcesoDTO(proceso));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity<?> findAll() {
    log.debug("Request to findAll() Proceso");

    try {
      return ResponseEntity.ok()
              .body(procesoMapper.listProcesoToListProcesoDTO(procesoService.findAll()));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAllByCompania/{usuId}")
  public ResponseEntity<?> findAllByCompania(@PathVariable("usuId")  String usuId) {
    try {
      return ResponseEntity.ok()
          .body(procesoMapper.listProcesoToListProcesoDTO(procesoService.findAllByCompania(usuId)));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
  @GetMapping(value = "/findAllByArea/{areaId}")
  public ResponseEntity<?> findAllByArea(@PathVariable("areaId")  String areaId) {
    try {
      Area area = areaService.findById(areaId).get();
      return ResponseEntity.ok()
          .body(procesoMapper.listProcesoToListProcesoDTO(procesoService.findAllByArea(area)));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
  @PostMapping(value = "/save")
  public ResponseEntity<?> save(@RequestBody ProcesoDTO procesoDTO) {
    log.debug("Request to save Proceso: {}", procesoDTO);

    try {
      Proceso proceso = procesoMapper.procesoDTOToProceso(procesoDTO);
      proceso = procesoService.save(proceso);

      return ResponseEntity.ok().body(procesoMapper.procesoToProcesoDTO(proceso));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody ProcesoDTO procesoDTO) {
    log.debug("Request to update Proceso: {}", procesoDTO);

    try {
      Proceso proceso = procesoMapper.procesoDTOToProceso(procesoDTO);
      proceso = procesoService.update(proceso);

      return ResponseEntity.ok().body(procesoMapper.procesoToProcesoDTO(proceso));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping(value = "/delete/{prosId}")
  public ResponseEntity<?> delete(@PathVariable("prosId") String prosId) throws Exception {
    log.debug("Request to delete Proceso");

    try {
      Proceso proceso = procesoService.findById(prosId).get();

      procesoService.delete(proceso);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(procesoService.count());
  }

  @PutMapping(value = "/cambioEstado")
  public ResponseEntity<?> cambioEstado(@RequestBody ProcesoDTO entityDTO) {
    try {
      Proceso entity = procesoMapper.procesoDTOToProceso(entityDTO);
      entity = procesoService.cambiarEstado(entity);
      return ResponseEntity.ok().body(procesoMapper.procesoToProcesoDTO(entity));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
