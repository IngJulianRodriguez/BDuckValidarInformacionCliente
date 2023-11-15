package com.bankduck.ValidarInformacionCliente.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel()
public class ClienteResponse {
    @ApiModelProperty(name = "cedula", required = true,example = "2548975",value = "2548975")
    private long cedula;
    @ApiModelProperty(name = "nombre", required = true,example = "Juan perez", value = "Juan perez")
    private String nombre;

    public ClienteResponse(){
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
