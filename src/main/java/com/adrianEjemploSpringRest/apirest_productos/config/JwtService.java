package com.adrianEjemploSpringRest.apirest_productos.config;

// Entidad User de la aplicación

import com.adrianEjemploSpringRest.apirest_productos.entities.User;

// Clases JWT para generar, firmar y validar tokens
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

// Permite inyectar propiedades desde application.properties
import org.springframework.beans.factory.annotation.Value;

// Interfaz estándar de Spring Security para usuarios autenticados
import org.springframework.security.core.userdetails.UserDetails;

// Marca esta clase como un servicio gestionado por Spring
import org.springframework.stereotype.Service;

// Clave criptográfica utilizada para firmar JWT
import javax.crypto.SecretKey;

// Utilidades para trabajar con fechas
import java.util.Date;

@Service
public class JwtService {

    // Clave secreta utilizada para firmar y validar JWT
    // Se obtiene desde application.properties
    @Value("${jwt.secret}")
    private String secret;

    // Tiempo de expiración del token en milisegundos
    @Value("${jwt.expiration}")
    private long expiration;

    /**
     * Genera la clave criptográfica utilizada para firmar
     * y verificar los tokens JWT.
     */
    private SecretKey getSignInKey() {

        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Genera un JWT para el usuario autenticado.
     * <p>
     * Información incluida:
     * - username (subject)
     * - role (claim personalizado)
     * - fecha de creación
     * - fecha de expiración
     */
    public String generateToken(User user) {

        return Jwts.builder()

                // Usuario propietario del token
                .subject(user.getUsername())

                // Fecha de emisión
                .issuedAt(new Date())

                // Fecha de expiración
                .expiration(new Date(System.currentTimeMillis() + expiration))

                // Claim personalizado con el rol
                .claim("role", user.getRole().name())

                // Firma digital del token
                .signWith(getSignInKey())

                // Genera el JWT final
                .compact();
    }

    /**
     * Extrae el username almacenado en el token.
     */
    public String extractUsername(String token) {

        return extractAllClaims(token).getSubject();
    }

    /**
     * Comprueba si el token pertenece al usuario indicado.
     * <p>
     * Actualmente valida:
     * - Username
     * <p>
     * Mejora recomendada:
     * - Comprobar también expiración del token.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {

        String username = extractUsername(token);

        return username.equals(userDetails.getUsername());
    }

    /**
     * Obtiene todos los Claims contenidos en el JWT.
     * <p>
     * Aquí se valida automáticamente:
     * - Firma digital
     * - Integridad del token
     */
    private Claims extractAllClaims(String token) {

        return Jwts.parser()

                // Verifica la firma usando la clave secreta
                .verifyWith(getSignInKey())

                .build()

                // Decodifica y valida el JWT
                .parseSignedClaims(token)

                // Devuelve el contenido del token
                .getPayload();
    }
}