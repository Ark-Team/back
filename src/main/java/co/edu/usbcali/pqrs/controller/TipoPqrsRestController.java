package co.edu.usbcali.pqrs.controller;

import co.edu.usbcali.pqrs.domain.*;
import co.edu.usbcali.pqrs.dto.TipoPqrsDTO;
import co.edu.usbcali.pqrs.mapper.TipoPqrsMapper;
import co.edu.usbcali.pqrs.service.TipoPqrsService;

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
@RequestMapping("/api/tipoPqrs")
@CrossOrigin(origins = "*")
public class TipoPqrsRestController {
  private static final Logger log = LoggerFactory.getLogger(TipoPqrsRestController.class);
  @Autowired private TipoPqrsService tipoPqrsService;
  @Autowired private TipoPqrsMapper tipoPqrsMapper;

  @GetMapping(value = "/findById/{tpqrsId}")
  public ResponseEntity<?> findById(@PathVariable("tpqrsId") String tpqrsId) {
    log.debug("Request to findById() TipoPqrs");

    try {
      TipoPqrs tipoPqrs = tipoPqrsService.findById(tpqrsId).get();

      return ResponseEntity.ok().body(tipoPqrsMapper.tipoPqrsToTipoPqrsDTO(tipoPqrs));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity<?> findAll() {
    log.debug("Request to findAll() TipoPqrs");

    try {
      return ResponseEntity.ok()
          .body(tipoPqrsMapper.listTipoPqrsToListTipoPqrsDTO(tipoPqrsService.findAll()));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping(value = "/save")
  public ResponseEntity<?> save(@RequestBody TipoPqrsDTO tipoPqrsDTO) {
    log.debug("Request to save TipoPqrs: {}", tipoPqrsDTO);

    try {
      TipoPqrs tipoPqrs = tipoPqrsMapper.tipoPqrsDTOToTipoPqrs(tipoPqrsDTO);
      tipoPqrs = tipoPqrsService.save(tipoPqrs);

      return ResponseEntity.ok().body(tipoPqrsMapper.tipoPqrsToTipoPqrsDTO(tipoPqrs));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody TipoPqrsDTO tipoPqrsDTO) {
    log.debug("Request to update TipoPqrs: {}", tipoPqrsDTO);

    try {
      TipoPqrs tipoPqrs = tipoPqrsMapper.tipoPqrsDTOToTipoPqrs(tipoPqrsDTO);
      tipoPqrs = tipoPqrsService.update(tipoPqrs);

      return ResponseEntity.ok().body(tipoPqrsMapper.tipoPqrsToTipoPqrsDTO(tipoPqrs));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping(value = "/delete/{tpqrsId}")
  public ResponseEntity<?> delete(@PathVariable("tpqrsId") String tpqrsId) throws Exception {
    log.debug("Request to delete TipoPqrs");

    try {
      TipoPqrs tipoPqrs = tipoPqrsService.findById(tpqrsId).get();

      tipoPqrsService.delete(tipoPqrs);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(tipoPqrsService.count());
  }
}
