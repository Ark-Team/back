package co.edu.usbcali.pqrs.controller;

import co.edu.usbcali.pqrs.domain.*;
import co.edu.usbcali.pqrs.dto.ClienteDTO;
import co.edu.usbcali.pqrs.mapper.ClienteMapper;
import co.edu.usbcali.pqrs.service.ClienteService;

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
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class ClienteRestController {
  private static final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
  @Autowired private ClienteService clienteService;
  @Autowired private ClienteMapper clienteMapper;

  @GetMapping(value = "/findById/{clieId}")
  public ResponseEntity<?> findById(@PathVariable("clieId") String clieId) {
    log.debug("Request to findById() Cliente");

    try {
      Cliente cliente = clienteService.findById(clieId).get();

      return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(cliente));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity<?> findAll() {
    log.debug("Request to findAll() Cliente");

    try {
      return ResponseEntity.ok()
          .body(clienteMapper.listClienteToListClienteDTO(clienteService.findAll()));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping(value = "/save")
  public ResponseEntity<?> save(@RequestBody ClienteDTO clienteDTO) {
    log.debug("Request to save Cliente: {}", clienteDTO);

    try {
      Cliente cliente = clienteMapper.clienteDTOToCliente(clienteDTO);
      cliente = clienteService.save(cliente);

      return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(cliente));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody ClienteDTO clienteDTO) {
    log.debug("Request to update Cliente: {}", clienteDTO);

    try {
      Cliente cliente = clienteMapper.clienteDTOToCliente(clienteDTO);
      cliente = clienteService.update(cliente);

      return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(cliente));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping(value = "/delete/{clieId}")
  public ResponseEntity<?> delete(@PathVariable("clieId") String clieId) throws Exception {
    log.debug("Request to delete Cliente");

    try {
      Cliente cliente = clienteService.findById(clieId).get();

      clienteService.delete(cliente);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(clienteService.count());
  }
}
