package com.sentinela.application.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sentinela.application.Entity.Usuario;
import com.sentinela.application.Repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/getUsuario")
    public List<Usuario> getUsuarios(){
       List<Usuario> usuarios = usuarioRepository.findAll();
       return usuarios;
    }
}
