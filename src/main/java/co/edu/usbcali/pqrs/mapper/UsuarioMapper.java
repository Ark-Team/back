package co.edu.usbcali.pqrs.mapper;

import co.edu.usbcali.pqrs.domain.Usuario;
import co.edu.usbcali.pqrs.dto.UsuarioDTO;

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
public interface UsuarioMapper {
  @Mapping(source = "compania.compId", target = "compId_Compania")
  @Mapping(source = "tipoUsuario.tusuId", target = "tusuId_TipoUsuario")
  public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario) throws Exception;

  @Mapping(source = "compId_Compania", target = "compania.compId")
  @Mapping(source = "tusuId_TipoUsuario", target = "tipoUsuario.tusuId")
  public Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO) throws Exception;

  public List<UsuarioDTO> listUsuarioToListUsuarioDTO(List<Usuario> usuarios) throws Exception;

  public List<Usuario> listUsuarioDTOToListUsuario(List<UsuarioDTO> usuarioDTOs) throws Exception;
}
