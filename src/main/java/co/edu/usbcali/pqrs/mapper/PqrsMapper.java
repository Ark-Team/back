package co.edu.usbcali.pqrs.mapper;

import co.edu.usbcali.pqrs.domain.Pqrs;
import co.edu.usbcali.pqrs.dto.PqrsDTO;

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
public interface PqrsMapper {
  @Mapping(source = "formulario.formId", target = "formId_Formulario")
  @Mapping(source = "proceso.prosId", target = "prosId_Proceso")
  @Mapping(source = "cliente.clieId", target = "clieId_Cliente")
  @Mapping(source = "tipoPqrs.tpqrsId", target = "tpqrsId_TipoPqrs")
  @Mapping(source = "tipoDocumento.tcdocId", target = "tcdocId_TipoDocumento")
  @Mapping(source = "compania.compId", target = "compId_Compania")
  @Mapping(source = "area.areaId", target = "areaId_Area")
  @Mapping(source = "genero.genId", target = "genId_Genero")
  public PqrsDTO pqrsToPqrsDTO(Pqrs pqrs) throws Exception;

  @Mapping(source = "formId_Formulario", target = "formulario.formId")
  @Mapping(source = "prosId_Proceso", target = "proceso.prosId")
  @Mapping(source = "clieId_Cliente", target = "cliente.clieId")
  @Mapping(source = "tpqrsId_TipoPqrs", target = "tipoPqrs.tpqrsId")
  @Mapping(source = "tcdocId_TipoDocumento", target = "tipoDocumento.tcdocId")
  @Mapping(source = "compId_Compania", target = "compania.compId")
  @Mapping(source = "areaId_Area", target = "area.areaId")
  @Mapping(source = "genId_Genero", target = "genero.genId")
  public Pqrs pqrsDTOToPqrs(PqrsDTO pqrsDTO) throws Exception;

  public List<PqrsDTO> listPqrsToListPqrsDTO(List<Pqrs> pqrss) throws Exception;

  public List<Pqrs> listPqrsDTOToListPqrs(List<PqrsDTO> pqrsDTOs) throws Exception;
}
