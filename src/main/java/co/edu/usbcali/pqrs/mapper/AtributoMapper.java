package co.edu.usbcali.pqrs.mapper;

import co.edu.usbcali.pqrs.domain.Atributo;
import co.edu.usbcali.pqrs.dto.AtributoDTO;

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
public interface AtributoMapper {
  public AtributoDTO atributoToAtributoDTO(Atributo atributo) throws Exception;

  public Atributo atributoDTOToAtributo(AtributoDTO atributoDTO) throws Exception;

  public List<AtributoDTO> listAtributoToListAtributoDTO(List<Atributo> atributos) throws Exception;

  public List<Atributo> listAtributoDTOToListAtributo(List<AtributoDTO> atributoDTOs)
      throws Exception;
}
