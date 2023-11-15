package com.bankduck.ValidarInformacionCliente.entities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Cliente {
    @Id
    private long cedula;

    private String nombre;

    private String correo;

    private Date fechaNacimiento;

    private String direccion;

    private String ciudad;

    private int telefono;

    private long ingresosMensuales;

    private String fotocopiaCedula;

    private String usuario;

    private String estado;

    @Column(name = "contrasena")
    private String contrasenaHash;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    @OrderColumn(name = "servicio_order")
    private List<ServiciosCliente> serviciosCliente;

    public Cliente() {
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean verificarContrasena(String contrasena) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(contrasena, this.contrasenaHash);
    }

    public void setContrasena(String contrasena) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.contrasenaHash = passwordEncoder.encode(contrasena);
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

    public List<ServiciosCliente> getServiciosCliente() {
        return serviciosCliente;
    }

    public void setServiciosCliente(List<ServiciosCliente> serviciosCliente) {
        this.serviciosCliente = serviciosCliente;
    }
}
