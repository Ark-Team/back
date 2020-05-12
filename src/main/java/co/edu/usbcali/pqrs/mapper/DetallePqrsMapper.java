package co.edu.usbcali.pqrs.mapper;

import co.edu.usbcali.pqrs.domain.DetallePqrs;
import co.edu.usbcali.pqrs.dto.DetallePqrsDTO;

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
public interface DetallePqrsMapper {
  @Mapping(source = "pqrs.pqrsId", target = "pqrsId_Pqrs")
  @Mapping(source = "prioridad.prioId", target = "prioId_Prioridad")
  @Mapping(source = "estado.estaId", target = "estaId_Estado")
  public DetallePqrsDTO detallePqrsToDetallePqrsDTO(DetallePqrs detallePqrs) throws Exception;

  @Mapping(source = "pqrsId_Pqrs", target = "pqrs.pqrsId")
  @Mapping(source = "prioId_Prioridad", target = "prioridad.prioId")
  @Mapping(source = "estaId_Estado", target = "estado.estaId")
  public DetallePqrs detallePqrsDTOToDetallePqrs(DetallePqrsDTO detallePqrsDTO) throws Exception;

  public List<DetallePqrsDTO> listDetallePqrsToListDetallePqrsDTO(List<DetallePqrs> detallePqrss)
      throws Exception;

  public List<DetallePqrs> listDetallePqrsDTOToListDetallePqrs(List<DetallePqrsDTO> detallePqrsDTOs)
      throws Exception;
}
