package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Mecanico;
import com.example.repository.MecanicoRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MecanicoService {

	@Autowired
	private MecanicoRepository mecanicoRepository;

	public List<Mecanico> findAll() {
		return mecanicoRepository.findAll();
	}

	public Optional<Mecanico> findOne(Integer id) {
		return mecanicoRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Mecanico save(Mecanico entity) {
		return mecanicoRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Mecanico entity) {
		mecanicoRepository.delete(entity);
	}

}
	
