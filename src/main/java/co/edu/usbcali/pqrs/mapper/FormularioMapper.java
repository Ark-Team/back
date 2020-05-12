package co.edu.usbcali.pqrs.mapper;

import co.edu.usbcali.pqrs.domain.Formulario;
import co.edu.usbcali.pqrs.dto.FormularioDTO;

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
public interface FormularioMapper {
  @Mapping(source = "compania.compId", target = "compId_Compania")
  public FormularioDTO formularioToFormularioDTO(Formulario formulario) throws Exception;

  @Mapping(source = "compId_Compania", target = "compania.compId")
  public Formulario formularioDTOToFormulario(FormularioDTO formularioDTO) throws Exception;

  public List<FormularioDTO> listFormularioToListFormularioDTO(List<Formulario> formularios)
      throws Exception;

  public List<Formulario> listFormularioDTOToListFormulario(List<FormularioDTO> formularioDTOs)
      throws Exception;
}
