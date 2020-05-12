package co.edu.usbcali.pqrs.controller;

import co.edu.usbcali.pqrs.domain.*;
import co.edu.usbcali.pqrs.dto.PrioridadDTO;
import co.edu.usbcali.pqrs.mapper.PrioridadMapper;
import co.edu.usbcali.pqrs.service.PrioridadService;

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
@RequestMapping("/api/prioridad")
@CrossOrigin(origins = "*")
public class PrioridadRestController {
  private static final Logger log = LoggerFactory.getLogger(PrioridadRestController.class);
  @Autowired private PrioridadService prioridadService;
  @Autowired private PrioridadMapper prioridadMapper;

  @GetMapping(value = "/findById/{prioId}")
  public ResponseEntity<?> findById(@PathVariable("prioId") String prioId) {
    log.debug("Request to findById() Prioridad");

    try {
      Prioridad prioridad = prioridadService.findById(prioId).get();

      return ResponseEntity.ok().body(prioridadMapper.prioridadToPrioridadDTO(prioridad));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity<?> findAll() {
    log.debug("Request to findAll() Prioridad");

    try {
      return ResponseEntity.ok()
          .body(prioridadMapper.listPrioridadToListPrioridadDTO(prioridadService.findAll()));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping(value = "/save")
  public ResponseEntity<?> save(@RequestBody PrioridadDTO prioridadDTO) {
    log.debug("Request to save Prioridad: {}", prioridadDTO);

    try {
      Prioridad prioridad = prioridadMapper.prioridadDTOToPrioridad(prioridadDTO);
      prioridad = prioridadService.save(prioridad);

      return ResponseEntity.ok().body(prioridadMapper.prioridadToPrioridadDTO(prioridad));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody PrioridadDTO prioridadDTO) {
    log.debug("Request to update Prioridad: {}", prioridadDTO);

    try {
      Prioridad prioridad = prioridadMapper.prioridadDTOToPrioridad(prioridadDTO);
      prioridad = prioridadService.update(prioridad);

      return ResponseEntity.ok().body(prioridadMapper.prioridadToPrioridadDTO(prioridad));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping(value = "/delete/{prioId}")
  public ResponseEntity<?> delete(@PathVariable("prioId") String prioId) throws Exception {
    log.debug("Request to delete Prioridad");

    try {
      Prioridad prioridad = prioridadService.findById(prioId).get();

      prioridadService.delete(prioridad);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(prioridadService.count());
  }
}
