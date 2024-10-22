package com.sentinela.application.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/postUsuario")
    public void postUsuario(@RequestBody Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @PutMapping("/putUsuario")
    public String putUsuario(@RequestParam Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioObject = usuarioRepository.findById(id);
        if(usuarioObject.isEmpty()){
            return "Usuário não encontrado";
        }

        Usuario usuarioUpdate = usuarioObject.get();
        
        usuario.setId(usuarioUpdate.getId());
        usuarioRepository.save(usuario);
        return "Usuário atualizado";
    }

    @DeleteMapping("/deleteUsuario")
    public void deleteUsuario(@RequestParam Long id) {
        usuarioRepository.deleteById(id);
    }
    
}
