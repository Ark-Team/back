package co.edu.usbcali.pqrs.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

/** @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org */
@SpringBootTest
public class GeneroServiceTest {
  private static final Logger log = LoggerFactory.getLogger(GeneroServiceTest.class);
  @Autowired private GeneroService generoService;

  @Test
  @DisplayName("findAll")
  public void findAll() {
    assertNotNull(generoService);
  }

  @Test
  @DisplayName("save")
  public void save() throws Exception {
    assertNotNull(generoService);
  }

  @Test
  @DisplayName("delete")
  public void delete() throws Exception {
    assertNotNull(generoService);
  }

  @Test
  @DisplayName("deleteById")
  public void deleteById() throws Exception {
    assertNotNull(generoService);
  }

  @Test
  @DisplayName("update")
  public void update() throws Exception {
    assertNotNull(generoService);
  }

  @Test
  @DisplayName("findById")
  public void findById() throws Exception {
    assertNotNull(generoService);
  }

  @Test
  @DisplayName("count")
  public void count() throws Exception {
    assertNotNull(generoService);
  }
}
