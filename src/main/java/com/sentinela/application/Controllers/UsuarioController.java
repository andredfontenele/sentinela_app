package com.sentinela.application.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.sentinela.application.Servicos.QrCodeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private QrCodeService qrCode;
    @Autowired
    private PasswordEncoder passEncoder;


    @Operation(summary = "Obter todos os usuários.", description = "Retorna uma lista de todos os usuários cadastrados.")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado.")
        })
    @GetMapping("/getUsuario")
    public List<Usuario> getUsuarios(){
       List<Usuario> usuarios = usuarioRepository.findAll();
       return usuarios;
    }

    @Operation(summary = "Cadastrar novo usuário.", description = "Rota para cadastro de um novo usuário no sistema.")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Erro na operação.")
        })
    @PostMapping("/postUsuario")
    public void postUsuario(@RequestBody Usuario usuario) {

        String code;

        try{
            code = qrCode.generateQrCode(usuario);
        } catch(Exception e){
            System.out.println(e);
            return;
        }

        usuario.setQrcode(code);

        usuario.setSenha(passEncoder.encode(usuario.getSenha()));

        usuarioRepository.save(usuario);
    }

    @Operation(summary = "Atualizar usuário.", description = "Rota para atualização do cadastro de um usuário existente no sistema.")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Erro na operação.")
        })
    @PutMapping("/putUsuario")
    public String putUsuario(@RequestParam Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioObject = usuarioRepository.findById(id);
        if(usuarioObject.isEmpty()){
            return "Usuário não encontrado";
        }

        Usuario usuarioUpdate = usuarioObject.get();

        usuario.setSenha(passEncoder.encode(usuario.getSenha()));

        String code;

        try{
            code = qrCode.generateQrCode(usuario);
        } catch(Exception e){
            System.out.println(e);
            return "Erro ao gerar QrCode";
        }

        usuario.setQrcode(code);
        
        usuario.setId(usuarioUpdate.getId());
        usuarioRepository.save(usuario);
        return "Usuário atualizado";
    }

    @Operation(summary = "Deletar um usuário.", description = "Rota para deletar um usuário no sistema.")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Erro na operação.")
        })
    @DeleteMapping("/deleteUsuario")
    public void deleteUsuario(@RequestParam Long id) {
        usuarioRepository.deleteById(id);
    }
    
}
