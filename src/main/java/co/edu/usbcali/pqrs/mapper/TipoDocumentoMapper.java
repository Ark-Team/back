package co.edu.usbcali.pqrs.mapper;

import co.edu.usbcali.pqrs.domain.TipoDocumento;
import co.edu.usbcali.pqrs.dto.TipoDocumentoDTO;

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
public interface TipoDocumentoMapper {
  public TipoDocumentoDTO tipoDocumentoToTipoDocumentoDTO(TipoDocumento tipoDocumento)
      throws Exception;

  public TipoDocumento tipoDocumentoDTOToTipoDocumento(TipoDocumentoDTO tipoDocumentoDTO)
      throws Exception;

  public List<TipoDocumentoDTO> listTipoDocumentoToListTipoDocumentoDTO(
      List<TipoDocumento> tipoDocumentos) throws Exception;

  public List<TipoDocumento> listTipoDocumentoDTOToListTipoDocumento(
      List<TipoDocumentoDTO> tipoDocumentoDTOs) throws Exception;
}
