package com.bankduck.ValidarInformacionCliente.common;

import com.bankduck.ValidarInformacionCliente.dto.ClienteRequest;
import com.bankduck.ValidarInformacionCliente.entities.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteRequestMapper {
    Cliente ClienteRequestToCliente(ClienteRequest source);
}
