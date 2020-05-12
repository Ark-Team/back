package co.edu.usbcali.pqrs.mapper;

import co.edu.usbcali.pqrs.domain.UsuarioArea;
import co.edu.usbcali.pqrs.dto.UsuarioAreaDTO;

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
public interface UsuarioAreaMapper {
  @Mapping(source = "area.areaId", target = "areaId_Area")
  @Mapping(source = "usuario.usuId", target = "usuId_Usuario")
  public UsuarioAreaDTO usuarioAreaToUsuarioAreaDTO(UsuarioArea usuarioArea) throws Exception;

  @Mapping(source = "areaId_Area", target = "area.areaId")
  @Mapping(source = "usuId_Usuario", target = "usuario.usuId")
  public UsuarioArea usuarioAreaDTOToUsuarioArea(UsuarioAreaDTO usuarioAreaDTO) throws Exception;

  public List<UsuarioAreaDTO> listUsuarioAreaToListUsuarioAreaDTO(List<UsuarioArea> usuarioAreas)
      throws Exception;

  public List<UsuarioArea> listUsuarioAreaDTOToListUsuarioArea(List<UsuarioAreaDTO> usuarioAreaDTOs)
      throws Exception;
}
