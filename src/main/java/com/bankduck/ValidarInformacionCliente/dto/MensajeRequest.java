package com.bankduck.ValidarInformacionCliente.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel()
public class MensajeRequest {
    @ApiModelProperty(name = "mensaje", required = true,example = "1080", value = "")
    private String mensaje;
    @ApiModelProperty(name = "telefono", required = true,example = "3046378034", value = "")
    private String telefono;

    public MensajeRequest(){
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
