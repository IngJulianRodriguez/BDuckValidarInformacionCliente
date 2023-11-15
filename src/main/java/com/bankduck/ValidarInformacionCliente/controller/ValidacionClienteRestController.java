package com.bankduck.ValidarInformacionCliente.controller;

import com.bankduck.ValidarInformacionCliente.Utils.Generador;
import com.bankduck.ValidarInformacionCliente.common.ClienteRequestMapper;
import com.bankduck.ValidarInformacionCliente.common.ClienteResponseMapper;
import com.bankduck.ValidarInformacionCliente.dto.ClienteRequest;
import com.bankduck.ValidarInformacionCliente.dto.ClienteResponse;
import com.bankduck.ValidarInformacionCliente.dto.CodigoVerificacionRequest;
import com.bankduck.ValidarInformacionCliente.entities.Cliente;
import com.bankduck.ValidarInformacionCliente.entities.CodigoVerificacion;
import com.bankduck.ValidarInformacionCliente.entities.Servicio;
import com.bankduck.ValidarInformacionCliente.entities.ServiciosCliente;
import com.bankduck.ValidarInformacionCliente.repository.ClienteRepository;
import com.bankduck.ValidarInformacionCliente.repository.CodigoVerificacionRepository;
import com.bankduck.ValidarInformacionCliente.repository.ServicioRepository;
import com.bankduck.ValidarInformacionCliente.repository.ServiciosClienteRepository;
import com.bankduck.ValidarInformacionCliente.services.ClienteService;
import com.bankduck.ValidarInformacionCliente.services.CodigoVerificacionService;
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
    ClienteRepository clienteRepository;

    @Autowired
    ServicioRepository servicioRepository;

    @Autowired
    CodigoVerificacionRepository codigoVerificacionRepository;

    @Autowired
    ServiciosClienteRepository serviciosClienteRepository;

    @Autowired
    ClienteResponseMapper clienteResponseMapper;
    @Autowired
    ClienteRequestMapper clienteRequestMapper;

    @Autowired
    CodigoVerificacionService codigoVerificacionService;
    @Autowired
    ClienteService clienteService;

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

    Optional<Cliente> optionalCliente = clienteRepository.findById(input.getCedula());
    Cliente cliente = optionalCliente.get();
    ClienteResponse clienteResponse =  clienteResponseMapper.ClienteToClienteResponse(cliente);
    return ResponseEntity.ok (clienteResponse);
}

    @GetMapping("/existeUsuario/{cedula}")
    public boolean existeUsuario(@PathVariable Long cedula) {
        Optional<Cliente> findById = clienteRepository.findById(cedula);
        if (findById.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
    @GetMapping("/existeServicio/{idServicio}")
    public boolean existeServicio(@PathVariable Long idServicio) {
        Optional<Servicio> findById = servicioRepository.findById(idServicio);
        if (findById.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
    @GetMapping("/usuarioTieneServicio/{cedula}/{idServicio}")
    public boolean usuarioTieneServicio(@PathVariable Long cedula,@PathVariable Long idServicio) {

        Optional<ServiciosCliente> findById = serviciosClienteRepository.findByClienteCedulaAndServicioId(cedula,idServicio);
        if (findById.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
    @GetMapping("/generarCodigo/{cedula}")
    public void generarCodigo(@PathVariable Long cedula) {
        Long codigo;
        Optional<CodigoVerificacion> optionalCodigoVerificacion = codigoVerificacionRepository.findById(cedula);
        if(optionalCodigoVerificacion.isPresent()){
            codigo = optionalCodigoVerificacion.get().getCodigo();
        }else {
            codigo = Generador.CodigoVerificacion();
            CodigoVerificacion codigoVerificacion = new CodigoVerificacion();
            codigoVerificacion.setCodigo(codigo);
            codigoVerificacion.setCedula(cedula);
            codigoVerificacionRepository.save(codigoVerificacion);
        }
        codigoVerificacionService.enviarCodigo(codigo);

    }

    @PostMapping("/VerificarTelefono")
    public ResponseEntity<?> VerificarTelefono(@RequestBody CodigoVerificacionRequest input) {
        Optional<CodigoVerificacion> optionalCodigoVerificacion
                = codigoVerificacionRepository.findByCedulaAndCodigo(input.getCedula(), input.getCodigo());
        if (optionalCodigoVerificacion.isPresent()) {
            codigoVerificacionService.eliminarPorCedulaYCodigo(input.getCedula(), input.getCodigo());
            return ResponseEntity.ok("ok");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
