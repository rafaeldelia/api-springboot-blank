package com.springboot.api.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.api.application.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByLogin(String login);
}