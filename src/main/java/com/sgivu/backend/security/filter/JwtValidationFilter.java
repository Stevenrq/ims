package com.sgivu.backend.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgivu.backend.security.SimpleGrantedAuthorityJsonCreator;
import com.sgivu.backend.security.TokenJwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.*;

/**
 * Filtro de validación de JWT que extiende {@link BasicAuthenticationFilter}.
 * Se encarga de validar el token JWT en cada solicitud y establecer la autenticación en el contexto de seguridad.
 */
public class JwtValidationFilter extends BasicAuthenticationFilter {

    /**
     * Constructor que inicializa el filtro con el {@link AuthenticationManager}.
     *
     * @param authenticationManager el gestor de autenticaciones.
     */
    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public Claims getClaims(String token) {
        return Jwts.parser().verifyWith(TokenJwtConfig.SECRET_KEY).build().parseSignedClaims(token).getPayload();
    }

    public Date getExpirationDate(String token) {
        return getClaims(token).getExpiration();
    }

    public boolean isExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }

    /**
     * Método que se ejecuta en cada solicitud HTTP para validar el token JWT.
     *
     * @param request  la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @param chain    el filtro de cadena.
     * @throws IOException      si ocurre un error de I/O.
     * @throws ServletException si ocurre un error de servlet.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(TokenJwtConfig.HEADER_AUTHORIZATION);

        if (header == null || !header.startsWith(TokenJwtConfig.PREFIX_TOKEN)) {
            // Continúa con los demás filtros
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(TokenJwtConfig.PREFIX_TOKEN, "");
        try {
            // Validar token
            if (isExpired(token)) {
                String errorMessage = String.format("El token JWT ha expirado. Fecha de expiración: %s", getExpirationDate(token));
                logger.error(errorMessage);
                throw new JwtException(errorMessage + ". Por favor, solicite un nuevo token.");
            }
            Claims claims = getClaims(token);
            String username = claims.getSubject();
            Object claimAuthorities = claims.get("authorities");

            Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                    new ObjectMapper()
                            /*
                             * Configura Jackson para utilizar SimpleGrantedAuthorityJsonCreator como mixin
                             * al deserializar instancias de SimpleGrantedAuthority.
                             * Esto permite personalizar cómo Jackson maneja la deserialización de
                             * propiedades específicas, como "authority", dentro de SimpleGrantedAuthority.
                             *
                             * Esto es necesario porque se intenta deserializar la propiedad "authority"
                             * del JSON y poblarlo en el constructor de SimpleGrantedAuthority,
                             * donde el nombre de la propiedad y el nombre del parámetro son diferentes.
                             */
                            .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
                            .readValue(claimAuthorities.toString().getBytes(), SimpleGrantedAuthority[].class));

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                    null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        } catch (JwtException e) {
            Map<String, String> body = new HashMap<>();
            body.put("error", e.getMessage());
            body.put("message", "El token JWT NO es válido");
            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(TokenJwtConfig.CONTENT_TYPE);
        }
    }
}
