package co.edu.usbcali.pqrs.mapper;

import co.edu.usbcali.pqrs.domain.Genero;
import co.edu.usbcali.pqrs.dto.GeneroDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *     <p>Mapper Build with MapStruct https://mapstruct.org/ MapStruct is a code generator that
 *     greatly simplifies the implementation of mappings between Java bean type based on a
 *     convention over configuration approach.
 */
@Mapper
public interface GeneroMapper {
  public GeneroDTO generoToGeneroDTO(Genero genero) throws Exception;

  public Genero generoDTOToGenero(GeneroDTO generoDTO) throws Exception;

  public List<GeneroDTO> listGeneroToListGeneroDTO(List<Genero> generos) throws Exception;

  public List<Genero> listGeneroDTOToListGenero(List<GeneroDTO> generoDTOs) throws Exception;
}
