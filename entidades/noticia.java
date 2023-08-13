
package com.egg.Ej1_Spring.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import jakarta.persistence.OneToOne;

@Entity
public class noticia {
    @Id
    public Long id;
    public String titulo;
    public String cuerpo;
    public String detalle;
    
    @OneToOne
    Periodista periodista;

    public noticia() {
    }

    public noticia(Long id, String titulo, String cuerpo, String detalle, Periodista periodista) {
        this.id = id;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.detalle = detalle;
        this.periodista = periodista;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Periodista getPeriodista() {
        return periodista;
    }

    public void setPeriodista(Periodista periodista) {
        this.periodista = periodista;
    }

    @Override
    public String toString() {
        return "noticia{" + "id=" + id + ", titulo=" + titulo + ", cuerpo=" + cuerpo + ", detalle=" + detalle + ", periodista=" + periodista + '}';
    }


               
}
