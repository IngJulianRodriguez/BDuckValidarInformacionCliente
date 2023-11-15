package com.bankduck.ValidarInformacionCliente.repository;

import com.bankduck.ValidarInformacionCliente.entities.CodigoVerificacion;
import com.bankduck.ValidarInformacionCliente.entities.ServiciosCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodigoVerificacionRepository extends JpaRepository<CodigoVerificacion, Long> {
    Optional<CodigoVerificacion> findByCedulaAndCodigo(Long cedula, Long codigo);
    void deleteByCedulaAndCodigo(Long cedula, Long codigo);
}
