package com.sentinela.application.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class InicioController {
    // Redirecionando para Swagger-UI
    @GetMapping("/")
    public String home(){
        return "redirect:/swagger-ui/index.html";
    }

    
}
