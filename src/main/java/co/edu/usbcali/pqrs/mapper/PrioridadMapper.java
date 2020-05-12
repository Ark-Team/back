package co.edu.usbcali.pqrs.mapper;

import co.edu.usbcali.pqrs.domain.Prioridad;
import co.edu.usbcali.pqrs.dto.PrioridadDTO;

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
public interface PrioridadMapper {
  public PrioridadDTO prioridadToPrioridadDTO(Prioridad prioridad) throws Exception;

  public Prioridad prioridadDTOToPrioridad(PrioridadDTO prioridadDTO) throws Exception;

  public List<PrioridadDTO> listPrioridadToListPrioridadDTO(List<Prioridad> prioridads)
      throws Exception;

  public List<Prioridad> listPrioridadDTOToListPrioridad(List<PrioridadDTO> prioridadDTOs)
      throws Exception;
}
