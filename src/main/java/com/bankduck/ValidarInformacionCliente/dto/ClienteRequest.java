package com.bankduck.ValidarInformacionCliente.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel()
public class ClienteRequest {
    @ApiModelProperty(name = "cedula", required = true,example = "", value = "")
    private long cedula;
    @ApiModelProperty(name = "nombre", required = true,example = "Juan Perez", value = "")
    private String nombre;
    @ApiModelProperty(name = "correo", required = true,example = "ejemplo@correo.com", value = "")
    private String correo;
    @ApiModelProperty(name = "fechaNacimiento", required = true,example = "", value = "")
    private Date fechaNacimiento;
    @ApiModelProperty(name = "direccion", required = false,example = "", value = "")
    private String direccion;
    @ApiModelProperty(name = "ciudad", required = false,example = "", value = "")
    private String ciudad;
    @ApiModelProperty(name = "telefono", required = true,example = "", value = "")
    private int telefono;
    @ApiModelProperty(name = "ingresosMensuales", required = false,example = "", value = "")
    private long ingresosMensuales;
    @ApiModelProperty(name = "fotocopiaCedula", required = true,example = "", value = "")
    private String fotocopiaCedula;

    private String estado;

    public ClienteRequest(){
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public long getIngresosMensuales() {
        return ingresosMensuales;
    }

    public void setIngresosMensuales(long ingresosMensuales) {
        this.ingresosMensuales = ingresosMensuales;
    }

    public String getFotocopiaCedula() {
        return fotocopiaCedula;
    }

    public void setFotocopiaCedula(String fotocopiaCedula) {
        this.fotocopiaCedula = fotocopiaCedula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
