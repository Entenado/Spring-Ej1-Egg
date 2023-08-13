
package com.egg.Ej1_Spring.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/adminn")
public class AdminControlador {
    
    @GetMapping("/dashboard")
    public String panelAdministrativo(){
    
        return "inicio.html";
    }
}
