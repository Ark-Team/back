package co.edu.usbcali.pqrs.controller;

import co.edu.usbcali.pqrs.domain.*;
import co.edu.usbcali.pqrs.dto.TipoDocumentoDTO;
import co.edu.usbcali.pqrs.mapper.TipoDocumentoMapper;
import co.edu.usbcali.pqrs.service.TipoDocumentoService;

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
@RequestMapping("/api/tipoDocumento")
@CrossOrigin(origins = "*")
public class TipoDocumentoRestController {
  private static final Logger log = LoggerFactory.getLogger(TipoDocumentoRestController.class);
  @Autowired private TipoDocumentoService tipoDocumentoService;
  @Autowired private TipoDocumentoMapper tipoDocumentoMapper;

  @GetMapping(value = "/findById/{tcdocId}")
  public ResponseEntity<?> findById(@PathVariable("tcdocId") String tcdocId) {
    log.debug("Request to findById() TipoDocumento");

    try {
      TipoDocumento tipoDocumento = tipoDocumentoService.findById(tcdocId).get();

      return ResponseEntity.ok()
          .body(tipoDocumentoMapper.tipoDocumentoToTipoDocumentoDTO(tipoDocumento));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity<?> findAll() {
    log.debug("Request to findAll() TipoDocumento");

    try {
      return ResponseEntity.ok()
          .body(
              tipoDocumentoMapper.listTipoDocumentoToListTipoDocumentoDTO(
                  tipoDocumentoService.findAll()));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping(value = "/save")
  public ResponseEntity<?> save(@RequestBody TipoDocumentoDTO tipoDocumentoDTO) {
    log.debug("Request to save TipoDocumento: {}", tipoDocumentoDTO);

    try {
      TipoDocumento tipoDocumento =
          tipoDocumentoMapper.tipoDocumentoDTOToTipoDocumento(tipoDocumentoDTO);
      tipoDocumento = tipoDocumentoService.save(tipoDocumento);

      return ResponseEntity.ok()
          .body(tipoDocumentoMapper.tipoDocumentoToTipoDocumentoDTO(tipoDocumento));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody TipoDocumentoDTO tipoDocumentoDTO) {
    log.debug("Request to update TipoDocumento: {}", tipoDocumentoDTO);

    try {
      TipoDocumento tipoDocumento =
          tipoDocumentoMapper.tipoDocumentoDTOToTipoDocumento(tipoDocumentoDTO);
      tipoDocumento = tipoDocumentoService.update(tipoDocumento);

      return ResponseEntity.ok()
          .body(tipoDocumentoMapper.tipoDocumentoToTipoDocumentoDTO(tipoDocumento));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping(value = "/delete/{tcdocId}")
  public ResponseEntity<?> delete(@PathVariable("tcdocId") String tcdocId) throws Exception {
    log.debug("Request to delete TipoDocumento");

    try {
      TipoDocumento tipoDocumento = tipoDocumentoService.findById(tcdocId).get();

      tipoDocumentoService.delete(tipoDocumento);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(tipoDocumentoService.count());
  }
}
