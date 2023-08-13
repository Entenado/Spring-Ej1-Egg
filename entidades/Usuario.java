package com.egg.Ej1_Spring.entidades;

import com.egg.Ej1_Spring.enumeraciones.Rol;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nombreUsuario;
    public String password;
    @Temporal(TemporalType.DATE)
    public Date fechaAlta;
    @Enumerated(EnumType.STRING)
    public Rol rol;

    public boolean activo;

    public Usuario() {
    }

    public Usuario(Long id, String nombreUsuario, String password, Date fechaAlta, Rol rol, boolean activo) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.fechaAlta = fechaAlta;
        this.rol = rol;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombreUsuario=" + nombreUsuario + ", password=" + password + ", fechaAlta=" + fechaAlta + ", rol=" + rol + ", activo=" + activo + '}';
    }

}
