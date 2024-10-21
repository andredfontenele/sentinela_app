package com.sentinela.application.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sentinela.application.Entity.Carro;
import com.sentinela.application.Repository.CarroRepository;


@RestController
@RequestMapping("/carro")
public class CarroController {
        
        @Autowired
        private CarroRepository carroRepository;

        //surpresswarning?
        @SuppressWarnings("rawtypes")
        @PostMapping("/insertCarro")
        public ResponseEntity insertCarro(@RequestBody Carro carro){
            return null;
            
    }
}
