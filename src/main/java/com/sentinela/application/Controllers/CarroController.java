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

import com.sentinela.application.Entity.Carro;
import com.sentinela.application.Repository.CarroRepository;
import com.sentinela.application.Servicos.QrCodeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;




@RestController
@RequestMapping("/carro")
public class CarroController {
        
        @Autowired
        private CarroRepository carroRepository;
        @Autowired
        private QrCodeService qrCode;

        
        @Operation(summary = "Obter todos os carros.", description = "Retorna uma lista de todos os carros cadastrados.")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carros encontrados com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum carro encontrado.")
        })
        @GetMapping("/getCarro")
        public List<Carro> getCarro(){
            List<Carro> carros = carroRepository.findAll();
            return carros;
        }

        @Operation(summary = "Cadastrar novo carro.", description = "Rota para cadastro de um novo carro no sistema.")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro cadastrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Erro na operação.")
        })
        @PostMapping("/postCarro")
        public void postCarro(@RequestBody Carro carro){

            String code;

            try{
                code = qrCode.generateQrCode(carro);
            } catch(Exception e){
                System.out.println(e);
                return;
            }
    
            carro.setQrcode(code);
            carroRepository.save(carro);
        }

        @Operation(summary = "Atualizar carro.", description = "Rota para atualização do cadastro de um carro existente no sistema.")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Erro na operação.")
        })
        //INTEGER ID ou LONG ID?
        @PutMapping("/putCarro")
        public String putCarro(@RequestParam Long id, @RequestBody Carro carro){
            Optional<Carro> carroObject = carroRepository.findById(id);
            if(carroObject.isEmpty()){
                return "Carro não encontrado";
            }

            Carro carroUpdate = carroObject.get();

            
            String code;

            try{
                code = qrCode.generateQrCode(carro);
            } catch(Exception e){
                System.out.println(e);
                return "Erro ao gerar QrCoe";
            }
    
            carro.setQrcode(code);

            carro.setId(carroUpdate.getId());
            carroRepository.save(carro);
            return "Carro atualizado";
        }

        @Operation(summary = "Deletar um carro.", description = "Rota para deletar um carro no sistema.")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Erro na operação.")
        })
        @DeleteMapping("/deleteCarro")
        public void deleteCarro(@RequestParam Long id){
            carroRepository.deleteById(id);
        }
    }
