package co.edu.usbcali.pqrs.controller;

import co.edu.usbcali.pqrs.domain.*;
import co.edu.usbcali.pqrs.dto.EstadoDTO;
import co.edu.usbcali.pqrs.mapper.EstadoMapper;
import co.edu.usbcali.pqrs.service.EstadoService;

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
@RequestMapping("/api/estado")
@CrossOrigin(origins = "*")
public class EstadoRestController {
  private static final Logger log = LoggerFactory.getLogger(EstadoRestController.class);
  @Autowired private EstadoService estadoService;
  @Autowired private EstadoMapper estadoMapper;

  @GetMapping(value = "/findById/{estaId}")
  public ResponseEntity<?> findById(@PathVariable("estaId") String estaId) {
    log.debug("Request to findById() Estado");

    try {
      Estado estado = estadoService.findById(estaId).get();

      return ResponseEntity.ok().body(estadoMapper.estadoToEstadoDTO(estado));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity<?> findAll() {
    log.debug("Request to findAll() Estado");

    try {
      return ResponseEntity.ok()
          .body(estadoMapper.listEstadoToListEstadoDTO(estadoService.findAll()));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping(value = "/save")
  public ResponseEntity<?> save(@RequestBody EstadoDTO estadoDTO) {
    log.debug("Request to save Estado: {}", estadoDTO);

    try {
      Estado estado = estadoMapper.estadoDTOToEstado(estadoDTO);
      estado = estadoService.save(estado);

      return ResponseEntity.ok().body(estadoMapper.estadoToEstadoDTO(estado));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody EstadoDTO estadoDTO) {
    log.debug("Request to update Estado: {}", estadoDTO);

    try {
      Estado estado = estadoMapper.estadoDTOToEstado(estadoDTO);
      estado = estadoService.update(estado);

      return ResponseEntity.ok().body(estadoMapper.estadoToEstadoDTO(estado));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping(value = "/delete/{estaId}")
  public ResponseEntity<?> delete(@PathVariable("estaId") String estaId) throws Exception {
    log.debug("Request to delete Estado");

    try {
      Estado estado = estadoService.findById(estaId).get();

      estadoService.delete(estado);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(estadoService.count());
  }

  @PutMapping(value = "/cambioEstado")
  public ResponseEntity<?> cambioEstado(@RequestBody EstadoDTO entityDTO) {
    try {
      Estado entity = estadoMapper.estadoDTOToEstado(entityDTO);
      entity = estadoService.cambiarEstado(entity);
      return ResponseEntity.ok().body(estadoMapper.estadoToEstadoDTO(entity));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
