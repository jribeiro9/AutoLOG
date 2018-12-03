package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Veiculo;
import com.example.repository.VeiculoRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;

	public List<Veiculo> findAll() {
		return veiculoRepository.findAll();
	}

	public Optional<Veiculo> findOne(Integer id) {
		return veiculoRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Veiculo save(Veiculo entity) {
		return veiculoRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Veiculo entity) {
		veiculoRepository.delete(entity);
	}

}
	
