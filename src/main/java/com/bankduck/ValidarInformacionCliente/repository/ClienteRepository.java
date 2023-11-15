package com.bankduck.ValidarInformacionCliente.repository;

import com.bankduck.ValidarInformacionCliente.entities.Cliente;
import com.bankduck.ValidarInformacionCliente.entities.ServiciosCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCedula(Long cedula);
}
