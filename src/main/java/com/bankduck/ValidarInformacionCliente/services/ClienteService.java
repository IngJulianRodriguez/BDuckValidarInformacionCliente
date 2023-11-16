package com.bankduck.ValidarInformacionCliente.services;

import com.bankduck.ValidarInformacionCliente.dto.ClienteRequest;
import com.bankduck.ValidarInformacionCliente.dto.MensajeRequest;
import com.bankduck.ValidarInformacionCliente.entities.Cliente;
import com.bankduck.ValidarInformacionCliente.entities.ServiciosCliente;
import com.bankduck.ValidarInformacionCliente.repository.ClienteRepository;
import com.bankduck.ValidarInformacionCliente.repository.ServiciosClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ServiciosClienteRepository serviciosClienteRepository;

    @Value("${server.administrarCliente.url}")  // Configura la URL del servidor B en tu archivo application.properties
    private String serverAdministrarCliente;
    public void crearClienteEnProceso(ClienteRequest input) {
        WebClient webClient = WebClient.builder()
                .baseUrl(serverAdministrarCliente)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + encodeCredentials("admin", "admin"))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        webClient.post()
                .uri("/administrarCliente/crearClienteEnProceso")
                .body(BodyInserters.fromValue(input))
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(response -> {
                    System.out.println("Respuesta del servidor: " + response);
                });

    }

    public void asignarServicioEnProceso(Long clienteId,Long servicioId) {
        WebClient webClient = WebClient.builder()
                .baseUrl(serverAdministrarCliente)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + encodeCredentials("admin", "admin"))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        webClient.post()
                .uri("/administrarCliente/asignarServicioEnProceso/{clienteId}/{servicioId}",clienteId, servicioId)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(response -> {
                    System.out.println("Respuesta del servidor: " + response);
                });

    }
    public boolean existeUsuario(Long cedula){
        Optional<Cliente> findById = clienteRepository.findById(cedula);
        if (findById.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean ClienteTieneServicio(Long cedula,Long idServicio){
        Optional<ServiciosCliente> findById = serviciosClienteRepository.findByClienteCedulaAndServicioId(cedula,idServicio);
        if (findById.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
    private String encodeCredentials(String username, String password) {
        return Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }
}
