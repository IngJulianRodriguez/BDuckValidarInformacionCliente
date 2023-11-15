package com.bankduck.ValidarInformacionCliente.common;

import com.bankduck.ValidarInformacionCliente.dto.ClienteRequest;
import com.bankduck.ValidarInformacionCliente.dto.ClienteResponse;
import com.bankduck.ValidarInformacionCliente.entities.Cliente;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ClienteResponseMapper {
    ClienteResponse ClienteToClienteResponse(Cliente source);
    List<ClienteResponse> ClienteListToClienteResponseList(List<Cliente> source);
}
