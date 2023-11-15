package com.bankduck.ValidarInformacionCliente.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CodigoVerificacion {


    private long codigo;

    @Id
    private long cedula;

    public CodigoVerificacion() {
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
