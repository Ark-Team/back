package co.edu.usbcali.pqrs.controller;

import co.edu.usbcali.pqrs.domain.*;
import co.edu.usbcali.pqrs.dto.FormularioDTO;
import co.edu.usbcali.pqrs.mapper.FormularioMapper;
import co.edu.usbcali.pqrs.service.CompaniaService;
import co.edu.usbcali.pqrs.service.FormularioService;

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
@RequestMapping("/api/formulario")
@CrossOrigin(origins = "*")
public class FormularioRestController {
  private static final Logger log = LoggerFactory.getLogger(FormularioRestController.class);
  @Autowired private FormularioService formularioService;
  @Autowired private CompaniaService companiaService;
  @Autowired private FormularioMapper formularioMapper;

  @GetMapping(value = "/findById/{compaId}")
  public ResponseEntity<?> findById(@PathVariable("compaId") String compaId) {
    log.debug("Request to findById() Formulario");

    try {
      Compania compania = companiaService.findById(compaId).get();
      Formulario formulario = formularioService.findByCompania(compania);

      return ResponseEntity.ok().body(formularioMapper.formularioToFormularioDTO(formulario));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity<?> findAll() {
    log.debug("Request to findAll() Formulario");

    try {
      return ResponseEntity.ok()
          .body(formularioMapper.listFormularioToListFormularioDTO(formularioService.findAll()));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAllByCompania/{compId}")
  public ResponseEntity<?> findAllByCompania(@PathVariable("compId") String compId) {
    log.debug("Request to findAll() Formulario");
    try {
      Compania compania = companiaService.findById(compId).get();
      return ResponseEntity.ok()
              .body(formularioMapper.listFormularioToListFormularioDTO(formularioService.findAllByCompania(compania)));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping(value = "/save")
  public ResponseEntity<?> save(@RequestBody FormularioDTO formularioDTO) {
    log.debug("Request to save Formulario: {}", formularioDTO);

    try {
      Formulario formulario = formularioMapper.formularioDTOToFormulario(formularioDTO);
      formulario = formularioService.save(formulario);

      return ResponseEntity.ok().body(formularioMapper.formularioToFormularioDTO(formulario));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody FormularioDTO formularioDTO) {
    log.debug("Request to update Formulario: {}", formularioDTO);

    try {
      Formulario formulario = formularioMapper.formularioDTOToFormulario(formularioDTO);
      formulario = formularioService.update(formulario);

      return ResponseEntity.ok().body(formularioMapper.formularioToFormularioDTO(formulario));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping(value = "/delete/{formId}")
  public ResponseEntity<?> delete(@PathVariable("formId") String formId) throws Exception {
    log.debug("Request to delete Formulario");

    try {
      Formulario formulario = formularioService.findById(formId).get();

      formularioService.delete(formulario);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(formularioService.count());
  }
}
