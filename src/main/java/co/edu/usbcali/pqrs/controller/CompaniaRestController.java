package co.edu.usbcali.pqrs.controller;

import co.edu.usbcali.pqrs.domain.*;
import co.edu.usbcali.pqrs.dto.CompaniaDTO;
import co.edu.usbcali.pqrs.mapper.CompaniaMapper;
import co.edu.usbcali.pqrs.service.CompaniaService;

import co.edu.usbcali.pqrs.service.UsuarioService;
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
@RequestMapping("/api/compania")
@CrossOrigin(origins = "*")
public class CompaniaRestController {
  private static final Logger log = LoggerFactory.getLogger(CompaniaRestController.class);
  @Autowired private CompaniaService companiaService;
  @Autowired private CompaniaMapper companiaMapper;

  @GetMapping(value = "/findById/{compId}")
  public ResponseEntity<?> findById(@PathVariable("compId") String compId) {
    log.debug("Request to findById() Compania");

    try {
      Compania compania = companiaService.findById(compId).get();

      return ResponseEntity.ok().body(companiaMapper.companiaToCompaniaDTO(compania));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity<?> findAll() {
    log.debug("Request to findAll() Compania");

    try {
      return ResponseEntity.ok()
          .body(companiaMapper.listCompaniaToListCompaniaDTO(companiaService.findAll()));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping(value = "/save")
  public ResponseEntity<?> save(@RequestBody CompaniaDTO companiaDTO) {
    log.debug("Request to save Compania: {}", companiaDTO);

    try {
      Compania compania = companiaMapper.companiaDTOToCompania(companiaDTO);
      compania = companiaService.save(compania);
      return ResponseEntity.ok().body(companiaMapper.companiaToCompaniaDTO(compania));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody CompaniaDTO companiaDTO) {
    log.debug("Request to update Compania: {}", companiaDTO);

    try {
      Compania compania = companiaMapper.companiaDTOToCompania(companiaDTO);
      compania = companiaService.update(compania);

      return ResponseEntity.ok().body(companiaMapper.companiaToCompaniaDTO(compania));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping(value = "/delete/{compId}")
  public ResponseEntity<?> delete(@PathVariable("compId") String compId) throws Exception {
    log.debug("Request to delete Compania");

    try {
      Compania compania = companiaService.findById(compId).get();

      companiaService.delete(compania);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(companiaService.count());
  }

  @PutMapping(value = "/cambioEstado")
  public ResponseEntity<?> cambioEstado(@RequestBody CompaniaDTO companiaDTO) {
    try {
      Compania compania = companiaMapper.companiaDTOToCompania(companiaDTO);
      compania = companiaService.cambiarEstado(compania);
      return ResponseEntity.ok().body(companiaMapper.companiaToCompaniaDTO(compania));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
