package co.edu.usbcali.pqrs.controller;

import co.edu.usbcali.pqrs.domain.*;
import co.edu.usbcali.pqrs.dto.AreaDTO;
import co.edu.usbcali.pqrs.mapper.AreaMapper;
import co.edu.usbcali.pqrs.service.AreaService;

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
@RequestMapping("/api/area")
@CrossOrigin(origins = "*")
public class AreaRestController {
  private static final Logger log = LoggerFactory.getLogger(AreaRestController.class);
  @Autowired private AreaService areaService;
  @Autowired private AreaMapper areaMapper;
  @Autowired private CompaniaService companiaService;

  @GetMapping(value = "/findById/{areaId}")
  public ResponseEntity<?> findById(@PathVariable("areaId") String areaId) {
    log.debug("Request to findById() Area");

    try {
      Area area = areaService.findById(areaId).get();

      return ResponseEntity.ok().body(areaMapper.areaToAreaDTO(area));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity<?> findAll() {
    log.debug("Request to findAll() Area");

    try {
      return ResponseEntity.ok().body(areaMapper.listAreaToListAreaDTO(areaService.findAll()));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAllByCompania/{compId}")
  public ResponseEntity<?> findAllByCompania(@PathVariable("compId")  String compId) {
    log.debug("Request to findAll() Area");
    try {
      Compania compania = companiaService.findById(compId).get();
      return ResponseEntity.ok().body(areaMapper.listAreaToListAreaDTO(areaService.findAllByCompania(compania)));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAllByCompaniaExpeto/{compId}/{area}")
  public ResponseEntity<?> findAllByCompania(@PathVariable("compId")  String compId,@PathVariable("area") String area) {
    log.debug("Request to findAll() Area");
    try {
      Compania compania = companiaService.findById(compId).get();
      return ResponseEntity.ok().body(areaMapper.listAreaToListAreaDTO(areaService.findAllByCompaniaExpeto(compania,area)));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping(value = "/save")
  public ResponseEntity<?> save(@RequestBody AreaDTO areaDTO) {
    log.debug("Request to save Area: {}", areaDTO);

    try {
      Area area = areaMapper.areaDTOToArea(areaDTO);
      area = areaService.save(area);

      return ResponseEntity.ok().body(areaMapper.areaToAreaDTO(area));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody AreaDTO areaDTO) {
    log.debug("Request to update Area: {}", areaDTO);

    try {
      Area area = areaMapper.areaDTOToArea(areaDTO);
      area = areaService.update(area);

      return ResponseEntity.ok().body(areaMapper.areaToAreaDTO(area));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping(value = "/delete/{areaId}")
  public ResponseEntity<?> delete(@PathVariable("areaId") String areaId) throws Exception {
    log.debug("Request to delete Area");

    try {
      Area area = areaService.findById(areaId).get();

      areaService.delete(area);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(areaService.count());
  }

  @PutMapping(value = "/cambioEstado")
  public ResponseEntity<?> cambioEstado(@RequestBody AreaDTO areaDTO) {
    try {
      Area area = areaMapper.areaDTOToArea(areaDTO);
      area = areaService.cambiarEstado(area);
      return ResponseEntity.ok().body(areaMapper.areaToAreaDTO(area));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
