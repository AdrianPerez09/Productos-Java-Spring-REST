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

                .cors(cors -> {
                })
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
                         * AUTH
                         */

                        .requestMatchers("/auth/**").permitAll()

                        /**
                         * DASHBOARD
                         */

                        .requestMatchers(HttpMethod.GET, "/dashboard/**").hasAnyRole("USER", "ADMIN")


                        .requestMatchers(
                                HttpMethod.GET,
                                "/products/**"
                        ).permitAll()

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/categories/**"
                                ).permitAll()

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/brands/**"
                                ).permitAll()


                        /**
                         * PRODUCTS
                         */

                        .requestMatchers(HttpMethod.POST, "/products/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/products/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")

                        /**
                         * CATEGORIES
                         */

                        .requestMatchers(HttpMethod.POST, "/categories/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/categories/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/categories/**").hasRole("ADMIN")

                        /**
                         * BRANDS
                         */


                        .requestMatchers(HttpMethod.POST, "/brands/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/brands/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/brands/**").hasRole("ADMIN")

                        /**
                         * USERS
                         */

                        .requestMatchers("/users/**").hasRole("ADMIN")

                        /**
                         * CUALQUIER OTRA PETICIÓN
                         */

                        .anyRequest().authenticated()

                )

                // Ejecutar nuestro filtro JWT antes del filtro
                // estándar de Spring Security
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                // Construir la configuración final
                .build();
    }
}