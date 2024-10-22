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




@RestController
@RequestMapping("/carro")
public class CarroController {
        
        @Autowired
        private CarroRepository carroRepository;

        @GetMapping("/getCarro")
        public List<Carro> getCarro(){
            List<Carro> carros = carroRepository.findAll();
            return carros;
        }

        @PostMapping("/postCarro")
        public void postCarro(@RequestBody Carro carro){
            carroRepository.save(carro);
        }

        //INTEGER ID ou LONG ID?
        @PutMapping("/putCarro")
        public String putCarro(@RequestParam Long id, @RequestBody Carro carro){
            Optional<Carro> carroObject = carroRepository.findById(id);
            if(carroObject.isEmpty()){
                return "Carro não encontrado";
            }

            Carro carroUpdate = carroObject.get();

            carro.setId(carroUpdate.getId());
            carroRepository.save(carro);
            return "Carro atualizado";
        }

        @DeleteMapping("/deleteCarro")
        public void deleteCarro(@RequestParam Long id){
            carroRepository.deleteById(id);
        }
    }
