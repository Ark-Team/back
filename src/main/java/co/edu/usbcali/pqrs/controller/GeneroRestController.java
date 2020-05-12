package co.edu.usbcali.pqrs.controller;

import co.edu.usbcali.pqrs.domain.*;
import co.edu.usbcali.pqrs.dto.GeneroDTO;
import co.edu.usbcali.pqrs.mapper.GeneroMapper;
import co.edu.usbcali.pqrs.service.GeneroService;

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
@RequestMapping("/api/genero")
@CrossOrigin(origins = "*")
public class GeneroRestController {
  private static final Logger log = LoggerFactory.getLogger(GeneroRestController.class);
  @Autowired private GeneroService generoService;
  @Autowired private GeneroMapper generoMapper;

  @GetMapping(value = "/findById/{genId}")
  public ResponseEntity<?> findById(@PathVariable("genId") String genId) {
    log.debug("Request to findById() Genero");

    try {
      Genero genero = generoService.findById(genId).get();

      return ResponseEntity.ok().body(generoMapper.generoToGeneroDTO(genero));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity<?> findAll() {
    log.debug("Request to findAll() Genero");

    try {
      return ResponseEntity.ok()
          .body(generoMapper.listGeneroToListGeneroDTO(generoService.findAll()));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping(value = "/save")
  public ResponseEntity<?> save(@RequestBody GeneroDTO generoDTO) {
    log.debug("Request to save Genero: {}", generoDTO);

    try {
      Genero genero = generoMapper.generoDTOToGenero(generoDTO);
      genero = generoService.save(genero);

      return ResponseEntity.ok().body(generoMapper.generoToGeneroDTO(genero));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody GeneroDTO generoDTO) {
    log.debug("Request to update Genero: {}", generoDTO);

    try {
      Genero genero = generoMapper.generoDTOToGenero(generoDTO);
      genero = generoService.update(genero);

      return ResponseEntity.ok().body(generoMapper.generoToGeneroDTO(genero));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping(value = "/delete/{genId}")
  public ResponseEntity<?> delete(@PathVariable("genId") String genId) throws Exception {
    log.debug("Request to delete Genero");

    try {
      Genero genero = generoService.findById(genId).get();

      generoService.delete(genero);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(generoService.count());
  }
}
