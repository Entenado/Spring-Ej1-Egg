
package com.egg.Ej1_Spring.repositorio;

import com.egg.Ej1_Spring.entidades.noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface noticiaRepositorio extends JpaRepository<noticia, Long>{
    
    @Query("SELECT l FROM noticia l where l.titulo = :titulo")
    public noticia buscarPorTitulo(@Param("titulo") String titulo);
    
   
}
