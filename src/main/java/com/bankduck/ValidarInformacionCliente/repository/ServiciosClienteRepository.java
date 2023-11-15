package com.bankduck.ValidarInformacionCliente.repository;

import com.bankduck.ValidarInformacionCliente.entities.Cliente;
import com.bankduck.ValidarInformacionCliente.entities.ServiciosCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiciosClienteRepository extends JpaRepository<ServiciosCliente, Long> {
    Optional<ServiciosCliente> findByClienteCedulaAndServicioId(Long cliente_id, Long servicio_id);
}
