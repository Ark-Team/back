package co.edu.usbcali.pqrs.mapper;

import co.edu.usbcali.pqrs.domain.Compania;
import co.edu.usbcali.pqrs.dto.CompaniaDTO;

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
public interface CompaniaMapper {
  public CompaniaDTO companiaToCompaniaDTO(Compania compania) throws Exception;

  public Compania companiaDTOToCompania(CompaniaDTO companiaDTO) throws Exception;

  public List<CompaniaDTO> listCompaniaToListCompaniaDTO(List<Compania> companias) throws Exception;

  public List<Compania> listCompaniaDTOToListCompania(List<CompaniaDTO> companiaDTOs)
      throws Exception;
}
