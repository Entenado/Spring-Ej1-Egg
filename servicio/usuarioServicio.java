
package com.egg.Ej1_Spring.servicio;

import com.egg.Ej1_Spring.Excepeciones.MiException;
import com.egg.Ej1_Spring.entidades.Usuario;
import com.egg.Ej1_Spring.entidades.noticia;
import com.egg.Ej1_Spring.enumeraciones.Rol;
import com.egg.Ej1_Spring.repositorio.UsuarioRepositorio;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Service
public class usuarioServicio implements UserDetailsService{
    
    @Autowired
    public UsuarioRepositorio usuarioRepositorio;
    
    
    @Transactional
    public void registrar(String nombreUsuario, String password, Date fechaAlta, Boolean activo)throws MiException{
    
        validar(nombreUsuario, fechaAlta, password, activo);
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setFechaAlta(fechaAlta);
        usuario.setActivo(activo);
        usuario.setRol(Rol.USER);
        
        
        usuarioRepositorio.save(usuario);
    }
    
    @Transactional
    public void actualizar(Long idUsuario, String nombreUsuario, String password, Date fechaAlta, Boolean activo) throws MiException {

        validar(nombreUsuario, fechaAlta, password, activo);

        Optional<Usuario> respuesta = usuarioRepositorio.findById(idUsuario);
        if (respuesta.isPresent()) {

            Usuario usuario = respuesta.get();
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setFechaAlta(fechaAlta);
            usuario.setActivo(true);

            usuario.setPassword(new BCryptPasswordEncoder().encode(password));

            usuario.setRol(Rol.USER);
  
            //usuarioRepositorio.save(usuario);
        }

    }
    
    
    
    @org.springframework.transaction.annotation.Transactional(readOnly=true)
    public List<Usuario> listarUsuarios() {

        List<Usuario> usuarios = new ArrayList();

        usuarios = usuarioRepositorio.findAll();

        return usuarios;
    }
    
        @Transactional
        public void modificarUsuario(Long id, String nombreUsuario, Date fechaAlta, String password, Boolean activo)throws MiException{
    
        validar(nombreUsuario,fechaAlta,password,activo);
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        
        
        if(respuesta.isPresent()){
        
            Usuario usuario = respuesta.get();
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setFechaAlta(fechaAlta);
            usuario.setPassword(password);
            usuario.setActivo(true);
        }
    }
            public Usuario getOne(Long id){
    
        return usuarioRepositorio.getOne(id);
    }
    
    private void validar(String nombreUsuario, Date fechaAlta, String password, Boolean activo)throws MiException{
    
        if(nombreUsuario.isEmpty() || nombreUsuario == null){
        
            throw new MiException("el nombre del usuario no puede ser nulo o estar vacío");
        }
        if(password.isEmpty() || password == null){
        
            throw new MiException("el password del usuario no puede ser nulo o estar vacío");
        }
        if(fechaAlta == null){
            throw new MiException("debe ingresar una fecha adecuada");
        }
        if(activo == null){
            throw new MiException("porfavor complete el campo");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarPorNombreUsuario(nombreUsuario);
        System.out.println("Usuario" + usuario);
        if(usuario != null){
            List<GrantedAuthority> permisos = new ArrayList();
            
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" +  usuario.getRol().toString()); //ROLE_USER
        
            permisos.add(p);
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            
            session.setAttribute("usuariosession", usuario);
            return new User(usuario.getNombreUsuario(), usuario.getPassword(), permisos);
        }else{
        System.out.println("Usuario no encontrado: " + nombreUsuario); // Agrega este registro
            return null;
        }
    }


    
}
