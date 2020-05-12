package co.edu.usbcali.pqrs.mapper;

import co.edu.usbcali.pqrs.domain.Area;
import co.edu.usbcali.pqrs.dto.AreaDTO;

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
public interface AreaMapper {
  @Mapping(source = "compania.compId", target = "compId_Compania")
  public AreaDTO areaToAreaDTO(Area area) throws Exception;

  @Mapping(source = "compId_Compania", target = "compania.compId")
  public Area areaDTOToArea(AreaDTO areaDTO) throws Exception;

  public List<AreaDTO> listAreaToListAreaDTO(List<Area> areas) throws Exception;

  public List<Area> listAreaDTOToListArea(List<AreaDTO> areaDTOs) throws Exception;
}
