/**
 * 
 */
package com.springboot.api.application.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.api.application.dto.CarroDTO;
import com.springboot.api.application.model.Carro;
import com.springboot.api.application.service.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

	@Autowired
	public CarroService carroService;

	@GetMapping
	public ResponseEntity findAll() {
		return ResponseEntity.ok(carroService.findAll());
	}

	/**
	 * @author rsdelia 404 not found se não encontrar registros 200 em caso de
	 *         sucesso
	 * @param id
	 * @return ResponseEntity<CarroDTO>
	 */
	@GetMapping("/{id}")
	public ResponseEntity findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(carroService.findById(id));
	}

	@GetMapping("/findByTipo/{tipo}")
	public ResponseEntity findByTipo(@PathVariable("tipo") String tipo) {
		List<Carro> retorno = carroService.findByTipo(tipo);
		return retorno.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(retorno);
	}

	@PostMapping
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity save(@RequestBody Carro carro) {
		CarroDTO c = carroService.save(carro);
		URI location = getURI(c.getId());
		return ResponseEntity.created(location).build();
	}

	private URI getURI(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@PutMapping("/update/{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Carro carro) {
		carro.setId(id);
		CarroDTO c = carroService.update(carro, id);
		return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		carroService.delete(id);
		return ResponseEntity.ok().build();
	}
}