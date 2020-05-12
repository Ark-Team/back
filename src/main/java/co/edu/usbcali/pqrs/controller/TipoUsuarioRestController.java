package co.edu.usbcali.pqrs.controller;

import co.edu.usbcali.pqrs.domain.*;
import co.edu.usbcali.pqrs.dto.TipoUsuarioDTO;
import co.edu.usbcali.pqrs.mapper.TipoUsuarioMapper;
import co.edu.usbcali.pqrs.service.TipoUsuarioService;

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
@RequestMapping("/api/tipoUsuario")
@CrossOrigin(origins = "*")
public class TipoUsuarioRestController {
  private static final Logger log = LoggerFactory.getLogger(TipoUsuarioRestController.class);
  @Autowired private TipoUsuarioService tipoUsuarioService;
  @Autowired private TipoUsuarioMapper tipoUsuarioMapper;

  @GetMapping(value = "/findById/{tusuId}")
  public ResponseEntity<?> findById(@PathVariable("tusuId") String tusuId) {
    log.debug("Request to findById() TipoUsuario");

    try {
      TipoUsuario tipoUsuario = tipoUsuarioService.findById(tusuId).get();

      return ResponseEntity.ok().body(tipoUsuarioMapper.tipoUsuarioToTipoUsuarioDTO(tipoUsuario));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity<?> findAll() {
    log.debug("Request to findAll() TipoUsuario");

    try {
      return ResponseEntity.ok()
          .body(
              tipoUsuarioMapper.listTipoUsuarioToListTipoUsuarioDTO(tipoUsuarioService.findAll()));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping(value = "/save")
  public ResponseEntity<?> save(@RequestBody TipoUsuarioDTO tipoUsuarioDTO) {
    log.debug("Request to save TipoUsuario: {}", tipoUsuarioDTO);

    try {
      TipoUsuario tipoUsuario = tipoUsuarioMapper.tipoUsuarioDTOToTipoUsuario(tipoUsuarioDTO);
      tipoUsuario = tipoUsuarioService.save(tipoUsuario);

      return ResponseEntity.ok().body(tipoUsuarioMapper.tipoUsuarioToTipoUsuarioDTO(tipoUsuario));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody TipoUsuarioDTO tipoUsuarioDTO) {
    log.debug("Request to update TipoUsuario: {}", tipoUsuarioDTO);

    try {
      TipoUsuario tipoUsuario = tipoUsuarioMapper.tipoUsuarioDTOToTipoUsuario(tipoUsuarioDTO);
      tipoUsuario = tipoUsuarioService.update(tipoUsuario);

      return ResponseEntity.ok().body(tipoUsuarioMapper.tipoUsuarioToTipoUsuarioDTO(tipoUsuario));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping(value = "/delete/{tusuId}")
  public ResponseEntity<?> delete(@PathVariable("tusuId") String tusuId) throws Exception {
    log.debug("Request to delete TipoUsuario");

    try {
      TipoUsuario tipoUsuario = tipoUsuarioService.findById(tusuId).get();

      tipoUsuarioService.delete(tipoUsuario);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(tipoUsuarioService.count());
  }

  @PutMapping(value = "/cambioEstado")
  public ResponseEntity<?> cambioEstado(@RequestBody TipoUsuarioDTO tipoUsuarioDTO) {
    try {
      TipoUsuario tipoUsuario = tipoUsuarioMapper.tipoUsuarioDTOToTipoUsuario(tipoUsuarioDTO);
      tipoUsuario = tipoUsuarioService.cambiarEstado(tipoUsuario);
      return ResponseEntity.ok().body(tipoUsuarioMapper.tipoUsuarioToTipoUsuarioDTO(tipoUsuario));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
