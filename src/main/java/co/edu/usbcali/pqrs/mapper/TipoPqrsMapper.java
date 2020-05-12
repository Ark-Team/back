package co.edu.usbcali.pqrs.mapper;

import co.edu.usbcali.pqrs.domain.TipoPqrs;
import co.edu.usbcali.pqrs.dto.TipoPqrsDTO;

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
public interface TipoPqrsMapper {
  public TipoPqrsDTO tipoPqrsToTipoPqrsDTO(TipoPqrs tipoPqrs) throws Exception;

  public TipoPqrs tipoPqrsDTOToTipoPqrs(TipoPqrsDTO tipoPqrsDTO) throws Exception;

  public List<TipoPqrsDTO> listTipoPqrsToListTipoPqrsDTO(List<TipoPqrs> tipoPqrss) throws Exception;

  public List<TipoPqrs> listTipoPqrsDTOToListTipoPqrs(List<TipoPqrsDTO> tipoPqrsDTOs)
      throws Exception;
}
