package co.edu.usbcali.pqrs.mapper;

import co.edu.usbcali.pqrs.domain.AtributoFormulario;
import co.edu.usbcali.pqrs.dto.AtributoFormularioDTO;

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
public interface AtributoFormularioMapper {
  @Mapping(source = "atributo.atriId", target = "atriId_Atributo")
  @Mapping(source = "compania.compId", target = "compId_Compania")
  public AtributoFormularioDTO atributoFormularioToAtributoFormularioDTO(
      AtributoFormulario atributoFormulario) throws Exception;

  @Mapping(source = "atriId_Atributo", target = "atributo.atriId")
  @Mapping(source = "compId_Compania", target = "compania.compId")
  public AtributoFormulario atributoFormularioDTOToAtributoFormulario(
      AtributoFormularioDTO atributoFormularioDTO) throws Exception;

  public List<AtributoFormularioDTO> listAtributoFormularioToListAtributoFormularioDTO(
      List<AtributoFormulario> atributoFormularios) throws Exception;

  public List<AtributoFormulario> listAtributoFormularioDTOToListAtributoFormulario(
      List<AtributoFormularioDTO> atributoFormularioDTOs) throws Exception;
}
