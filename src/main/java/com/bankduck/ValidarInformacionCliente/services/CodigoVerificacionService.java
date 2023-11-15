package com.bankduck.ValidarInformacionCliente.services;

import com.bankduck.ValidarInformacionCliente.dto.MensajeRequest;
import com.bankduck.ValidarInformacionCliente.repository.CodigoVerificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

@Service
public class CodigoVerificacionService {

    private final CodigoVerificacionRepository codigoVerificacionRepository;

    @Value("${server.enviarSMS.url}")  // Configura la URL del servidor B en tu archivo application.properties
    private String serverEnviarSMS;
    public CodigoVerificacionService(CodigoVerificacionRepository codigoVerificacionRepository) {
        this.codigoVerificacionRepository = codigoVerificacionRepository;
    }

    @Transactional
    public void eliminarPorCedulaYCodigo(Long cedula, Long codigo) {
        codigoVerificacionRepository.deleteByCedulaAndCodigo(cedula, codigo);
    }

    public void enviarCodigo(Long codigo) {
        WebClient webClient = WebClient.builder()
                .baseUrl(serverEnviarSMS)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + encodeCredentials("admin", "admin"))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        MensajeRequest mensajeRequest = new MensajeRequest();
        mensajeRequest.setMensaje(codigo+"");
        //lo obtendria de la base de datos pero por licencia se usa uno por defecto
        mensajeRequest.setTelefono("");

        webClient.post()
                .uri("/enviarsms")
                .body(BodyInserters.fromValue(mensajeRequest))
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(response -> {
                    System.out.println("Respuesta del servidor: " + response);
                });

    }

    private String encodeCredentials(String username, String password) {
        return Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }
}
