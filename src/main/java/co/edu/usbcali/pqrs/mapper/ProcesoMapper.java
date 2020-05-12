package co.edu.usbcali.pqrs.mapper;

import co.edu.usbcali.pqrs.domain.Proceso;
import co.edu.usbcali.pqrs.dto.ProcesoDTO;

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
public interface ProcesoMapper {
  @Mapping(source = "area.areaId", target = "areaId_Area")
  public ProcesoDTO procesoToProcesoDTO(Proceso proceso) throws Exception;

  @Mapping(source = "areaId_Area", target = "area.areaId")
  public Proceso procesoDTOToProceso(ProcesoDTO procesoDTO) throws Exception;

  public List<ProcesoDTO> listProcesoToListProcesoDTO(List<Proceso> procesos) throws Exception;

  public List<Proceso> listProcesoDTOToListProceso(List<ProcesoDTO> procesoDTOs) throws Exception;
}
