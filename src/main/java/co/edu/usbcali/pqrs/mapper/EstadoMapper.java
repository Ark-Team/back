package co.edu.usbcali.pqrs.mapper;

import co.edu.usbcali.pqrs.domain.Estado;
import co.edu.usbcali.pqrs.dto.EstadoDTO;

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
public interface EstadoMapper {
  public EstadoDTO estadoToEstadoDTO(Estado estado) throws Exception;

  public Estado estadoDTOToEstado(EstadoDTO estadoDTO) throws Exception;

  public List<EstadoDTO> listEstadoToListEstadoDTO(List<Estado> estados) throws Exception;

  public List<Estado> listEstadoDTOToListEstado(List<EstadoDTO> estadoDTOs) throws Exception;
}
