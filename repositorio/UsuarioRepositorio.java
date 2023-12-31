
package com.egg.Ej1_Spring.repositorio;

import com.egg.Ej1_Spring.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
    
    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario =:nombreUsuario")
    public Usuario buscarPorNombreUsuario(@Param("nombreUsuario")String nombreUsuario);
}
