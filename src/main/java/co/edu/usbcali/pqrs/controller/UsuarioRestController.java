package co.edu.usbcali.pqrs.controller;

import co.edu.usbcali.pqrs.domain.Area;
import co.edu.usbcali.pqrs.domain.Compania;
import co.edu.usbcali.pqrs.domain.Usuario;
import co.edu.usbcali.pqrs.domain.UsuarioArea;
import co.edu.usbcali.pqrs.dto.UsuarioDTO;
import co.edu.usbcali.pqrs.mapper.UsuarioMapper;
import co.edu.usbcali.pqrs.service.AreaService;
import co.edu.usbcali.pqrs.service.CompaniaService;
import co.edu.usbcali.pqrs.service.UsuarioAreaService;
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
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioRestController {
  private static final Logger log = LoggerFactory.getLogger(UsuarioRestController.class);
  @Autowired private UsuarioService usuarioService;
  @Autowired private UsuarioMapper usuarioMapper;
  @Autowired private CompaniaService companiaService;
  @Autowired private AreaService areaService;
  @Autowired private UsuarioAreaService usuarioAreaService;

  @GetMapping(value = "/findById/{usuId}")
  public ResponseEntity<?> findById(@PathVariable("usuId") String usuId) {
    log.debug("Request to findById() Usuario");

    try {
      Usuario usuario = usuarioService.findById(usuId).get();

      return ResponseEntity.ok().body(usuarioMapper.usuarioToUsuarioDTO(usuario));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity<?> findAll() {
    log.debug("Request to findAll() Usuario");

    try {
      return ResponseEntity.ok()
          .body(usuarioMapper.listUsuarioToListUsuarioDTO(usuarioService.findAll()));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/findAllByCompania/{compId}")
  public ResponseEntity<?> findAll(@PathVariable("compId") String compId) {
    log.debug("Request to findAll() Usuario");

    try {
      Compania compania = companiaService.findById(compId).get();
      return ResponseEntity.ok()
          .body(
              usuarioMapper.listUsuarioToListUsuarioDTO(
                  usuarioService.findAllByCompania(compania)));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
  @GetMapping(value = "/findAllUserByArea/{areaId}")
  public ResponseEntity<?> fi(@PathVariable("areaId") String areaId) {
    log.debug("Request to findAllUserByArea() Usuario");

    try {
      Area area = areaService.findById(areaId).get();
      return ResponseEntity.ok()
          .body(
              usuarioMapper.listUsuarioToListUsuarioDTO(usuarioService.findAllUserByArea(area)));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping(value = "/save")
  public ResponseEntity<?> save(
      @RequestBody UsuarioDTO usuarioDTO) {
    log.debug("Request to save Usuario: {}", usuarioDTO);

    try {
      Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
      usuario = usuarioService.save(usuario);
      return ResponseEntity.ok().body(usuarioMapper.usuarioToUsuarioDTO(usuario));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping(value = "/saveConUsuArea/{areaId}")
  public ResponseEntity<?> saveConUsuArea(
      @RequestBody UsuarioDTO usuarioDTO, @PathVariable("areaId") String areaId) {
    log.debug("Request to save Usuario: {}", usuarioDTO);

    try {
      Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
      Area area = areaService.findById(areaId).get();
      usuario = usuarioService.saveConUsuArea(usuario, area);

      return ResponseEntity.ok().body(usuarioMapper.usuarioToUsuarioDTO(usuario));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody UsuarioDTO usuarioDTO) {
    log.debug("Request to update Usuario: {}", usuarioDTO);

    try {
      Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
      usuario = usuarioService.update(usuario);

      return ResponseEntity.ok().body(usuarioMapper.usuarioToUsuarioDTO(usuario));
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping(value = "/delete/{usuId}")
  public ResponseEntity<?> delete(@PathVariable("usuId") String usuId) throws Exception {
    log.debug("Request to delete Usuario");

    try {
      Usuario usuario = usuarioService.findById(usuId).get();

      usuarioService.delete(usuario);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      log.error(e.getMessage(), e);

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(value = "/count")
  public ResponseEntity<?> count() {
    return ResponseEntity.ok().body(usuarioService.count());
  }

  @PutMapping(value = "/cambioEstado")
  public ResponseEntity<?> cambioEstado(@RequestBody UsuarioDTO usuarioDTO) {
    try {
      Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
      usuario = usuarioService.cambiarEstado(usuario);
      return ResponseEntity.ok().body(usuarioMapper.usuarioToUsuarioDTO(usuario));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
