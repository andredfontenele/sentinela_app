package com.sentinela.application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sentinela.application.Entity.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long>{
    
}
