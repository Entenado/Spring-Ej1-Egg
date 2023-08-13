package com.egg.Ej1_Spring.controladores;

import com.egg.Ej1_Spring.Excepeciones.MiException;
import com.egg.Ej1_Spring.entidades.Usuario;
import com.egg.Ej1_Spring.servicio.usuarioServicio;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class portalControlador {

    @Autowired
    private usuarioServicio usuarioServicio;

    @GetMapping("/dashboard")
    public String panelAdministrativo() {
        return "inicio.html";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/adminn/panel")
    public String inicio(ModelMap modelo) {
        
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.put("usuarios", usuarios);
        return "panel.html";
    }

    @GetMapping("/")
    public String vistaInicio() {

        return "noticiasLista.html";
    }

    @GetMapping("/registrar")//localhost:8080/registrar
    public String registrar(ModelMap modelo) {

        return "registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombreUsuario, @RequestParam String password, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaAlta, @RequestParam(defaultValue = "false") boolean activo, ModelMap modelo) {
        
        try {
            usuarioServicio.registrar(nombreUsuario, password, fechaAlta, activo);

            modelo.put("exito", "mensaje registrado correctamente");
            return "noticiasLista.html";
        } catch (MiException ex) {
            ex.printStackTrace();
            modelo.put("error", ex.getMessage());
            modelo.put("nombreUsuario", nombreUsuario);
            modelo.put("password", password);
            modelo.put("fechaAlata", fechaAlta);
            modelo.put("activo", activo);
            return "registro.html";
        }

    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {

        if (error != null) {
            modelo.put("error", "Usuario o Contraseña invalida");
            System.out.println("no está tomando los dats");
        }

        return "login.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session) {

        Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        if (logueado.getRol().toString().equals("ADMIN")) {

            return "redirect:/adminn/dashboard";
        }
        return "noticiasLista.html";

    }
    
        @GetMapping("/adminn/panel/{id}")
    public String modificar(@PathVariable("id") Long id, ModelMap modelo) {

        modelo.put("usuario", usuarioServicio.getOne(id));
        return "panel.html";

    }

    @PostMapping("/adminn/panel/{id}")
    public String modificar(@PathVariable("id") Long id, @RequestParam String nombreUsuario, @RequestParam String password, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaAlta, @RequestParam(defaultValue = "false") Boolean activo, ModelMap modelo) {

        try {
            usuarioServicio.actualizar(id, nombreUsuario, password,fechaAlta,activo);

            return "redirect:/adminn/dashboard";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "panel.html";
        }
    }

}
