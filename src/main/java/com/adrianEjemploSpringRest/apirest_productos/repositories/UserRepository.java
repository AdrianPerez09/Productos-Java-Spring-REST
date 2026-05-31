package com.adrianEjemploSpringRest.apirest_productos.repositories;

import com.adrianEjemploSpringRest.apirest_productos.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
