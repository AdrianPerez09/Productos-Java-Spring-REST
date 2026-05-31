package com.adrianEjemploSpringRest.apirest_productos.config;

import com.adrianEjemploSpringRest.apirest_productos.services.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Servicio JWT para leer y validar tokens
    private final JwtService jwtService;

    // Servicio que carga usuarios desde la base de datos
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(

            @NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain

    ) throws ServletException, IOException {

        // Leer el header Authorization
        String authHeader = request.getHeader("Authorization");

        // Si no existe o no empieza por Bearer
        // dejamos pasar la petición
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);

            return;
        }

        // Extraemos el token eliminando "Bearer "
        String jwt = authHeader.substring(7);

        // Extraemos el username almacenado en el token
        String username = jwtService.extractUsername(jwt);

        // Si existe usuario y todavía no está autenticado
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Buscar usuario en la base de datos
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Validar token
            if (jwtService.isTokenValid(jwt, userDetails)) {

                // Crear objeto Authentication
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(

                        userDetails,

                        null,

                        userDetails.getAuthorities());

                // Asociar detalles de la petición
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Guardar autenticación en Spring Security
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continuar la cadena de filtros
        filterChain.doFilter(request, response);
    }
}