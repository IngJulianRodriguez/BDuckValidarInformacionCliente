package com.bankduck.ValidarInformacionCliente.services;

import com.bankduck.ValidarInformacionCliente.entities.Servicio;
import com.bankduck.ValidarInformacionCliente.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioService {
    @Autowired
    ServicioRepository servicioRepository;

    public boolean ExisteServicio(Long idServicio){
        Optional<Servicio> findById = servicioRepository.findById(idServicio);
        if (findById.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}
