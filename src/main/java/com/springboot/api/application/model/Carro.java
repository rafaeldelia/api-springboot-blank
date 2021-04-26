package com.springboot.api.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Tabela carro na base carros do mysql / Usando Lombok
 * 
 * @Data substitui os getters and setters, equals, toString e hashCode
 * @NoArgsConstructor construtor default
 * @AllArgsConstructor todos os atributos da classe em um construtor
 * @author rsdelia
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String tipo;
	private String urlFoto;
	private String urlVideo;
	private String latitude;
	private String longitude;
}