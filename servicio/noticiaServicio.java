
package com.egg.Ej1_Spring.servicio;

import com.egg.Ej1_Spring.Excepeciones.MiException;
import com.egg.Ej1_Spring.entidades.noticia;
import com.egg.Ej1_Spring.repositorio.noticiaRepositorio;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class noticiaServicio {
    @Autowired
    private noticiaRepositorio noticiaRepositorio;
    
    @Transactional
    public void crearNoticia(Long id, String titulo, String cuerpo, String detalle) throws MiException{
    
        validar(id,titulo,cuerpo, detalle);
        noticia noticia = new noticia();
        noticia.setId(id);
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setDetalle(detalle);
        noticiaRepositorio.save(noticia);
        
    }
    
    public List<noticia> listarNoticias(){
    
        List<noticia> noticias = new ArrayList();
        noticias = noticiaRepositorio.findAll();
        
        return noticias;
    }
    
    public void modificarNoticia(Long id, String titulo, String cuerpo, String detalle)throws MiException{
    
        validar(id,titulo,cuerpo,detalle);
        Optional<noticia> respuesta = noticiaRepositorio.findById(id);
        
        
        if(respuesta.isPresent()){
        
            noticia noticia = respuesta.get();
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            noticia.setDetalle(detalle);
            noticiaRepositorio.save(noticia);
        }
    }
    
    public noticia getOne(Long id){
    
        return noticiaRepositorio.getOne(id);
    }
    
    private void validar (Long id, String titulo, String cuerpo, String detalle)throws MiException{
    
        if(id == null){
        throw new MiException("El isbn no puede ser nulo");
        }
        if(titulo.isEmpty()|| titulo.isEmpty()){
        
            throw new MiException("El titulo o nombre de la noticia, no puede ser nulo o estar vacio");
        }
         if(cuerpo.isEmpty()|| cuerpo.isEmpty()){
        
            throw new MiException("El cuerpo no puede ser nulo o estar vacio");
        }
         if(detalle.isEmpty() || detalle == null){
         
             throw new MiException ("El detalle no puede ser nulo o estar vacio");
         }
    }
}
