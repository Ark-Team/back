package co.edu.usbcali.pqrs.mapper;

import co.edu.usbcali.pqrs.domain.Cliente;
import co.edu.usbcali.pqrs.dto.ClienteDTO;

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
public interface ClienteMapper {
  public ClienteDTO clienteToClienteDTO(Cliente cliente) throws Exception;

  public Cliente clienteDTOToCliente(ClienteDTO clienteDTO) throws Exception;

  public List<ClienteDTO> listClienteToListClienteDTO(List<Cliente> clientes) throws Exception;

  public List<Cliente> listClienteDTOToListCliente(List<ClienteDTO> clienteDTOs) throws Exception;
}
