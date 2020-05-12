package co.edu.usbcali.pqrs.controller;

import co.edu.usbcali.pqrs.domain.*;
import co.edu.usbcali.pqrs.dto.AtributoFormularioDTO;
import co.edu.usbcali.pqrs.mapper.AtributoFormularioMapper;
import co.edu.usbcali.pqrs.service.AtributoFormularioService;

import co.edu.usbcali.pqrs.service.CompaniaService;
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
@RequestMapping("/api/atributoFormulario")
@CrossOrigin(origins = "*")
public class AtributoFormularioRestController {
  private static final Logger log = LoggerFactory.getLogger(AtributoFormularioRestController.class);
  @Autowired private AtributoFormularioService atributoFormularioService;
  @Autowired private AtributoFormularioMapper atributoFormularioMapper;
  @Autowired private CompaniaService companiaService;

  @GetMapping(value = "/findById/{atrformId}")
  public ResponseEntity<?> findById(@PathVariable("atrformId") String atrformId) {
    log.debug("Request to findById() AtributoFormulario");

    try {
      AtributoFormulario atributoFormulario = atributoFormularioService.findById(atrformId).get();

      return ResponseEntity.ok()
          .body(
              atributoFormularioMapper.atributoFormularioToAtributoFormularioDTO(
                  atributoFormulario));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity<?> findAll() {
    log.debug("Request to findAll() AtributoFormulario");

    try {
      return ResponseEntity.ok()
          .body(
              atributoFormularioMapper.listAtributoFormularioToListAtributoFormularioDTO(
                  atributoFormularioService.findAll()));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAllByCompania/{compId}")
  public ResponseEntity<?> findAllByCompania(@PathVariable("compId") String compId) {
    log.debug("Request to findAll() AtributoFormulario");

    try {

      Compania compania = companiaService.findById(compId).get();
      return ResponseEntity.ok()
              .body(
                      atributoFormularioMapper.listAtributoFormularioToListAtributoFormularioDTO(
                              atributoFormularioService.findAllByCompania(compania)));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
  @PostMapping(value = "/save")
  public ResponseEntity<?> save(@RequestBody AtributoFormularioDTO atributoFormularioDTO) {
    log.debug("Request to save AtributoFormulario: {}", atributoFormularioDTO);

    try {
      AtributoFormulario atributoFormulario =
          atributoFormularioMapper.atributoFormularioDTOToAtributoFormulario(atributoFormularioDTO);
      atributoFormulario = atributoFormularioService.save(atributoFormulario);

      return ResponseEntity.ok()
          .body(
              atributoFormularioMapper.atributoFormularioToAtributoFormularioDTO(
                  atributoFormulario));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody AtributoFormularioDTO atributoFormularioDTO) {
    log.debug("Request to update AtributoFormulario: {}", atributoFormularioDTO);

    try {
      AtributoFormulario atributoFormulario =
          atributoFormularioMapper.atributoFormularioDTOToAtributoFormulario(atributoFormularioDTO);
      atributoFormulario = atributoFormularioService.update(atributoFormulario);

      return ResponseEntity.ok()
          .body(
              atributoFormularioMapper.atributoFormularioToAtributoFormularioDTO(
                  atributoFormulario));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping(value = "/delete/{atrformId}")
  public ResponseEntity<?> delete(@PathVariable("atrformId") String atrformId) throws Exception {
    log.debug("Request to delete AtributoFormulario");

    try {
      AtributoFormulario atributoFormulario = atributoFormularioService.findById(atrformId).get();

      atributoFormularioService.delete(atributoFormulario);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(atributoFormularioService.count());
  }
}
