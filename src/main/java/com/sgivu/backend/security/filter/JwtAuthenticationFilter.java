package com.sgivu.backend.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgivu.backend.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.sgivu.backend.security.TokenJwtConfig.*;

/**
 * Filtro de autenticación JWT que extiende {@link UsernamePasswordAuthenticationFilter}.
 * Se encarga de gestionar el proceso de autenticación mediante JWT.
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final AuthenticationManager authenticationManager;

    /**
     * Constructor que inicializa el filtro con el {@link AuthenticationManager}.
     *
     * @param authenticationManager el gestor de autenticaciones.
     */
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Intenta autenticar al usuario a partir de las credenciales proporcionadas en la solicitud HTTP.
     *
     * @param request  la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @return un objeto de autenticación si las credenciales son válidas.
     * @throws AuthenticationException si ocurre un error durante la autenticación.
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        User user;
        String username = "";
        String password = "";
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            username = user.getUsername();
            password = user.getPassword();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authRequest);
    }

    /**
     * Método llamado cuando la autenticación es exitosa.
     * Genera un token JWT y lo añade a la respuesta HTTP.
     *
     * @param request    la solicitud HTTP.
     * @param response   la respuesta HTTP.
     * @param chain      el filtro de cadena.
     * @param authResult el resultado de la autenticación.
     * @throws IOException      si ocurre un error de I/O.
     * @throws ServletException si ocurre un error de servlet.
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        org.springframework.security.core.userdetails.User User = (org.springframework.security.core.userdetails.User) authResult
                .getPrincipal();

        String username = User.getUsername();
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();

        Claims claims = Jwts.claims()
                .add("authorities", new ObjectMapper().writeValueAsString(authorities))
                .build();

        String token = Jwts.builder()
                .subject(username)
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + convertMinutesToMilliseconds(1)))
                .issuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();

        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);

        Map<String, String> body = new HashMap<>();
        body.put("username", username);
        body.put("authorities", authorities.toString());
        body.put("token", token);
        body.put("message", String.format("Hola %s has iniciado sesión con éxito", username));

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpStatus.OK.value());
    }

    /**
     * Método llamado cuando la autenticación falla.
     * Añade un mensaje de error a la respuesta HTTP.
     *
     * @param request  la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @param failed   la excepción que contiene el error de autenticación.
     * @throws IOException      si ocurre un error de I/O.
     * @throws ServletException si ocurre un error de servlet.
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {

        Map<String, String> body = new HashMap<>();
        body.put("message", "Error en la autenticación, nombre de usuario o contraseña incorrecta");
        body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    public long convertMinutesToMilliseconds(long minutes) {
        if (minutes >= 0) {
            return minutes * 60000;
        } else {
            throw new RuntimeException("No se puede hacer la conversion con un número negativo");
        }
    }
}
