package com.arphoenix.blankspringbootapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arphoenix.blankspringbootapi.model.Cliente;
import com.arphoenix.blankspringbootapi.repository.ClienteInterface;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteInterface clienteInterface;

	@GetMapping
	public List<Cliente> listarClientes() {
		return clienteInterface.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente incluirCliente(@RequestBody Cliente cliente) {
		return clienteInterface.save(cliente);
	}	
}