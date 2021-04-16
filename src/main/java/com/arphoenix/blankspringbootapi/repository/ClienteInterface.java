/**
 * 
 */
package com.arphoenix.blankspringbootapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arphoenix.blankspringbootapi.model.Cliente;

/**
 * @author rsdelia
 *
 */
@Repository
public interface ClienteInterface extends JpaRepository<Cliente, Long> {

	/**
	 * Spring JPA já implementa os principais métodos
	 */
}
