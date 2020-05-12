package co.edu.usbcali.pqrs.controller;

import co.edu.usbcali.pqrs.domain.*;
import co.edu.usbcali.pqrs.dto.UsuarioAreaDTO;
import co.edu.usbcali.pqrs.mapper.UsuarioAreaMapper;
import co.edu.usbcali.pqrs.service.UsuarioAreaService;

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
@RequestMapping("/api/usuarioArea")
@CrossOrigin(origins = "*")
public class UsuarioAreaRestController {
  private static final Logger log = LoggerFactory.getLogger(UsuarioAreaRestController.class);
  @Autowired private UsuarioAreaService usuarioAreaService;
  @Autowired private UsuarioAreaMapper usuarioAreaMapper;

  @GetMapping(value = "/findById/{usuAreaId}")
  public ResponseEntity<?> findById(@PathVariable("usuAreaId") String usuAreaId) {
    log.debug("Request to findById() UsuarioArea");

    try {
      UsuarioArea usuarioArea = usuarioAreaService.findById(usuAreaId).get();

      return ResponseEntity.ok().body(usuarioAreaMapper.usuarioAreaToUsuarioAreaDTO(usuarioArea));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findByUsuario/{usuId}")
  public ResponseEntity<?> findByUsuario(@PathVariable("usuId") String usuId) {
    log.debug("Request to findById() UsuarioArea");
    try {
      UsuarioArea usuarioArea = usuarioAreaService.findByUsuario(usuId).get();
      return ResponseEntity.ok().body(usuarioAreaMapper.usuarioAreaToUsuarioAreaDTO(usuarioArea));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity<?> findAll() {
    log.debug("Request to findAll() UsuarioArea");

    try {
      return ResponseEntity.ok()
          .body(
              usuarioAreaMapper.listUsuarioAreaToListUsuarioAreaDTO(usuarioAreaService.findAll()));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping(value = "/save")
  public ResponseEntity<?> save(@RequestBody UsuarioAreaDTO usuarioAreaDTO) {
    log.debug("Request to save UsuarioArea: {}", usuarioAreaDTO);

    try {
      UsuarioArea usuarioArea = usuarioAreaMapper.usuarioAreaDTOToUsuarioArea(usuarioAreaDTO);
      usuarioArea = usuarioAreaService.save(usuarioArea);

      return ResponseEntity.ok().body(usuarioAreaMapper.usuarioAreaToUsuarioAreaDTO(usuarioArea));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody UsuarioAreaDTO usuarioAreaDTO) {
    log.debug("Request to update UsuarioArea: {}", usuarioAreaDTO);

    try {
      UsuarioArea usuarioArea = usuarioAreaMapper.usuarioAreaDTOToUsuarioArea(usuarioAreaDTO);
      usuarioArea = usuarioAreaService.update(usuarioArea);

      return ResponseEntity.ok().body(usuarioAreaMapper.usuarioAreaToUsuarioAreaDTO(usuarioArea));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping(value = "/delete/{usuAreaId}")
  public ResponseEntity<?> delete(@PathVariable("usuAreaId") String usuAreaId) throws Exception {
    log.debug("Request to delete UsuarioArea");

    try {
      UsuarioArea usuarioArea = usuarioAreaService.findById(usuAreaId).get();

      usuarioAreaService.delete(usuarioArea);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(usuarioAreaService.count());
  }
}
