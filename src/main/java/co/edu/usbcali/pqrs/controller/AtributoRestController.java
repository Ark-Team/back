package co.edu.usbcali.pqrs.controller;

import co.edu.usbcali.pqrs.domain.*;
import co.edu.usbcali.pqrs.dto.AtributoDTO;
import co.edu.usbcali.pqrs.mapper.AtributoMapper;
import co.edu.usbcali.pqrs.service.AtributoService;

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
@RequestMapping("/api/atributo")
@CrossOrigin(origins = "*")
public class AtributoRestController {
  private static final Logger log = LoggerFactory.getLogger(AtributoRestController.class);
  @Autowired private AtributoService atributoService;
  @Autowired private AtributoMapper atributoMapper;

  @GetMapping(value = "/findById/{atriId}")
  public ResponseEntity<?> findById(@PathVariable("atriId") String atriId) {
    log.debug("Request to findById() Atributo");

    try {
      Atributo atributo = atributoService.findById(atriId).get();

      return ResponseEntity.ok().body(atributoMapper.atributoToAtributoDTO(atributo));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity<?> findAll() {
    log.debug("Request to findAll() Atributo");

    try {
      return ResponseEntity.ok()
          .body(atributoMapper.listAtributoToListAtributoDTO(atributoService.findAll()));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping(value = "/save")
  public ResponseEntity<?> save(@RequestBody AtributoDTO atributoDTO) {
    log.debug("Request to save Atributo: {}", atributoDTO);

    try {
      Atributo atributo = atributoMapper.atributoDTOToAtributo(atributoDTO);
      atributo = atributoService.save(atributo);

      return ResponseEntity.ok().body(atributoMapper.atributoToAtributoDTO(atributo));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody AtributoDTO atributoDTO) {
    log.debug("Request to update Atributo: {}", atributoDTO);

    try {
      Atributo atributo = atributoMapper.atributoDTOToAtributo(atributoDTO);
      atributo = atributoService.update(atributo);

      return ResponseEntity.ok().body(atributoMapper.atributoToAtributoDTO(atributo));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping(value = "/delete/{atriId}")
  public ResponseEntity<?> delete(@PathVariable("atriId") String atriId) throws Exception {
    log.debug("Request to delete Atributo");

    try {
      Atributo atributo = atributoService.findById(atriId).get();

      atributoService.delete(atributo);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(atributoService.count());
  }
}
