package com.adrianEjemploSpringRest.apirest_productos.repositories;

import com.adrianEjemploSpringRest.apirest_productos.entities.RefreshToken;
import com.adrianEjemploSpringRest.apirest_productos.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByUser(User user);

}