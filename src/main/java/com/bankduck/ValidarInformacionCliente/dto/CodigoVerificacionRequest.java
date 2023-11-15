package com.bankduck.ValidarInformacionCliente.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel()
public class CodigoVerificacionRequest {
    @ApiModelProperty(name = "codigo", required = true,example = "", value = "")
    private long codigo;

    @ApiModelProperty(name = "cedula", required = true,example = "", value = "")
    private long cedula;

    public CodigoVerificacionRequest() {
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }
}
