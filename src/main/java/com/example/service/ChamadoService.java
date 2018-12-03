package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Chamado;
import com.example.repository.ChamadoRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;

	public List<Chamado> findAll() {
		return chamadoRepository.findAll();
	}
	
	public Optional<Chamado> findOne(Integer id) {
		return chamadoRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Chamado save(Chamado entity) {
		return chamadoRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Chamado entity) {
		chamadoRepository.delete(entity);
	}

}
	
