package com.springboot.api.application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.springboot.api.application.dto.CarroDTO;
import com.springboot.api.application.exception.ObjectNotFoundException;
import com.springboot.api.application.model.Carro;
import com.springboot.api.application.repository.CarroRepository;

@Service
public class CarroService {

	@Autowired
	public CarroRepository carroRepository;

	public List<CarroDTO> findAll() {
		//carroRepository.findAll()     -> retorna a lista de carros do JpaRespository
		//.map(c -> CarroDTO.create(c)) -> percorre a lista e cria um CarroDTO de retorno com os carros já preenchidos no DTO
		//.collect(Collectors.toList()) -> retorna o CarroDTO em lista
		return carroRepository.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
	}

	public CarroDTO findById(Long id) {
		// busca na base e retorna Carro
		Optional<Carro> carro = carroRepository.findById(id);
		// transforma Carro em CarroDTO e se por acaso não tiver o ID passado, retorna ObjectNotFoundException
		return carro.map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado!"));
	}

	public List<Carro> findByTipo(String tipo) {
		return carroRepository.findByTipo(tipo);
	}

	public CarroDTO save(Carro carro) {

		Assert.isNull(carro.getId(), "Não pode enviar um ID para o post!");
		
		return CarroDTO.create(carroRepository.save(carro));
	}

	public CarroDTO update(Carro carro, Long id) {
		Assert.notNull(id, "Não foi possível atualizar o registro");

		// Busca o carro no banco de dados
		Optional<Carro> optional = carroRepository.findById(id);
		if (optional.isPresent()) {
			Carro db = optional.get();

			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());

			carroRepository.save(db);

			return CarroDTO.create(db);
		} else {
			return null;
		}
	}

	public void delete(Long id) {
		carroRepository.deleteById(id);
	}
}