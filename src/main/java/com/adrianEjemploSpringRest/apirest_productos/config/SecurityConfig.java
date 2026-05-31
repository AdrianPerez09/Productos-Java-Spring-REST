package com.adrianEjemploSpringRest.apirest_productos.config;

// Permite declarar esta clase como configuración de Spring

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configuración de seguridad HTTP
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

// Permite configurar el tipo de sesión
import org.springframework.security.config.http.SessionCreationPolicy;

// Define la cadena de filtros de seguridad
import org.springframework.security.web.SecurityFilterChain;

// Filtro estándar de Spring para usuario/contraseña
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    // Filtro JWT personalizado que se ejecutará antes
    // del filtro de autenticación de Spring Security
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http

                // Desactiva CSRF.
                // En APIs REST con JWT normalmente no se utiliza.
                .csrf(csrf -> csrf.disable())

                // Configuración de sesiones
                .sessionManagement(session ->

                        // JWT es STATELESS:
                        // Spring NO guardará sesiones en memoria
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Configuración de permisos
                .authorizeHttpRequests(auth -> auth

                        /**
                         * FILTROS JWT
                         */

                        // Permitir acceso libre a login y registro
                        .requestMatchers("/auth/**").permitAll()

                        // Filtro que controla quien hace GET
                        .requestMatchers(
                                HttpMethod.GET,
                                "/products/**"
                        )
                        .hasAnyRole("USER","ADMIN")

                        // Filtro que controla quien hace POST
                        .requestMatchers(
                                HttpMethod.POST,
                                "/products/**"
                        )
                        .hasRole("ADMIN")

                        // Filtro que controla quien hace PUT
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/products/**"
                        )
                        .hasRole("ADMIN")

                        // Filtro que controla quien hace DELETE
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/products/**"
                        )
                        .hasRole("ADMIN")

                        .anyRequest()
                        .authenticated()

                )

                // Ejecutar nuestro filtro JWT antes del filtro
                // estándar de Spring Security
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                // Construir la configuración final
                .build();
    }
}