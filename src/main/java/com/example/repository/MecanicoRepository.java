package com.example.repository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Mecanico;


@Repository
public interface MecanicoRepository extends JpaRepository<Mecanico, Integer> {
	
}