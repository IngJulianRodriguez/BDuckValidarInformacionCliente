package com.bankduck.ValidarInformacionCliente.repository;

import com.bankduck.ValidarInformacionCliente.entities.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
}
