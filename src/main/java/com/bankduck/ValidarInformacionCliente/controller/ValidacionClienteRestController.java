package com.bankduck.ValidarInformacionCliente.controller;

import com.bankduck.ValidarInformacionCliente.dto.ClienteRequest;
import com.bankduck.ValidarInformacionCliente.dto.ClienteResponse;
import com.bankduck.ValidarInformacionCliente.dto.CodigoVerificacionRequest;
import com.bankduck.ValidarInformacionCliente.services.ClienteService;
import com.bankduck.ValidarInformacionCliente.services.CodigoVerificacionService;
import com.bankduck.ValidarInformacionCliente.services.ServicioService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Api(tags = "Api Validacion Clientes")
@RestController
@RequestMapping("/validacionCliente")
public class ValidacionClienteRestController {


    @Autowired
    CodigoVerificacionService codigoVerificacionService;
    @Autowired
    ClienteService clienteService;

    @Autowired
    ServicioService servicioService;
@PostMapping("/{idServicio}")
public ResponseEntity<?> post(@PathVariable String idServicio,@RequestBody ClienteRequest input) throws InterruptedException {
    if(idServicio.isEmpty()){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id Servicio requerido" );
    }
    Long longIdServicio;
    try {
        longIdServicio = Long.parseLong(idServicio);
    } catch (NumberFormatException e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id Servicio inválido: " + idServicio);
    }
    if(!existeServicio(longIdServicio)){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Servicio no encontrado" );
    }
    if(existeUsuario(input.getCedula())){
        return ResponseEntity.noContent().build();
    }else{
        clienteService.crearClienteEnProceso(input);
    }
    Thread.sleep(2000);
    clienteService.asignarServicioEnProceso(input.getCedula(),longIdServicio);
    ClienteResponse clienteResponse = clienteService.obtenerClienteResponseById(input.getCedula());

    return ResponseEntity.ok (clienteResponse);
}

    @GetMapping("/existeUsuario/{cedula}")
    public boolean existeUsuario(@PathVariable Long cedula) {
        return clienteService.existeUsuario(cedula);

    }
    @GetMapping("/existeServicio/{idServicio}")
    public boolean existeServicio(@PathVariable Long idServicio) {
        return servicioService.ExisteServicio(idServicio);
    }
    @GetMapping("/usuarioTieneServicio/{cedula}/{idServicio}")
    public boolean usuarioTieneServicio(@PathVariable Long cedula,@PathVariable Long idServicio) {
        return clienteService.ClienteTieneServicio(cedula, idServicio);
    }
    @GetMapping("/generarCodigo/{cedula}")
    public void generarCodigo(@PathVariable Long cedula) {
        codigoVerificacionService.GenerarCodigo(cedula);

    }

    @PostMapping("/VerificarTelefono")
    public ResponseEntity<?> VerificarTelefono(@RequestBody CodigoVerificacionRequest input) {
        boolean codigoVerificado = codigoVerificacionService.VerificarCodigo(input);
        if (codigoVerificado) {
            return ResponseEntity.ok("ok");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
