package com.egg.Ej1_Spring.controladores;

import com.egg.Ej1_Spring.Excepeciones.MiException;
import com.egg.Ej1_Spring.entidades.noticia;
import com.egg.Ej1_Spring.servicio.noticiaServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")//localhost:8080/admin
public class vistaPanelAdminControlador {

    @Autowired
    private noticiaServicio noticiaServicio;
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/registrar")//localhost:8080/admin/registrar
    public String registrar(ModelMap modelo) {

        /*   List<noticia> noticias = noticiaServicio.listarNoticias(); 
        modelo.addAttribute("noticias", noticias); */
        return "vistaPanelAdmin.html";
    }
    @PreAuthorize("hasRole('ADMIN')") 
    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long id, @RequestParam String titulo,
            @RequestParam String cuerpo, String detalle,
            ModelMap modelo) {

        try {
            noticiaServicio.crearNoticia(id, titulo, cuerpo, detalle);
            modelo.put("exito", "La noticia fue cargado correctamente");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "vistaPanelAdmin.html";
        }

        return "vistaInicio.html";

    }

    @GetMapping("/lista")
    public String listarNoticias(ModelMap modelo) {
        List<noticia> noticias = noticiaServicio.listarNoticias();

        modelo.addAttribute("noticias", noticias);
        return "noticiasLista.html";

    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable("id") Long id, ModelMap modelo) {

        modelo.put("noticia", noticiaServicio.getOne(id));
        return "modificar_noticia.html";

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable("id") Long id, @RequestParam String cuerpo,String detalle, @RequestParam String titulo, ModelMap modelo) {

        try {
            noticiaServicio.modificarNoticia(id, titulo, cuerpo, detalle);

            return "redirect:/admin/lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "modificar_noticia.html";
        }
    }
    
    
    


    @GetMapping("/noticia/{id}")
    public String verNoticia(@PathVariable("id") Long id, ModelMap modelo, String detalle) {
        noticia noticia = noticiaServicio.getOne(id); // Reemplaza "error.html" con la vista que deseas mostrar en caso de error
        modelo.addAttribute("noticia", noticia);
        // Agrega el índice de card al modelo
        Integer cardIndex = 2; // Define el índice de card que deseas mostrar
        modelo.addAttribute("cardIndex", cardIndex);
        return "detalleNoticia.html";
    }
    

 
}
