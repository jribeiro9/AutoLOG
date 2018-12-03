package com.example.repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Chamado;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
	
}