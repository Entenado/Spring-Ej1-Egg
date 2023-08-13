
package com.egg.Ej1_Spring.entidades;

import com.egg.Ej1_Spring.enumeraciones.Rol;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Periodista extends Usuario {
    
    @OneToMany(mappedBy = "periodista")
    private List<noticia> misNoticias;
    private Integer sueldoMensual;

    public Periodista() {
    }

    public Periodista(List<noticia> misNoticias, Integer sueldoMensual) {
        this.misNoticias = misNoticias;
        this.sueldoMensual = sueldoMensual;
    }

    public Periodista(List<noticia> misNoticias, Integer sueldoMensual, Long id, String nombreUsuario, String password, Date fechaAlta, Rol rol, boolean activo) {
        super(id, nombreUsuario, password, fechaAlta, rol, activo);
        this.misNoticias = misNoticias;
        this.sueldoMensual = sueldoMensual;
    }

    public List<noticia> getMisNoticias() {
        return misNoticias;
    }

    public void setMisNoticias(List<noticia> misNoticias) {
        this.misNoticias = misNoticias;
    }

    public Integer getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(Integer sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
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
        return "Periodista{" + "misNoticias=" + misNoticias + ", sueldoMensual=" + sueldoMensual + '}';
    }

 

}
