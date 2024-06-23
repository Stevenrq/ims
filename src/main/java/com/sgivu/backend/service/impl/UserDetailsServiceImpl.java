package com.sgivu.backend.service.impl;

import com.sgivu.backend.model.entity.User;
import com.sgivu.backend.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Nombre de usuario: %s no encontrado", username));
        }

        User user = userOptional.orElseThrow();
        List<GrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleEnum().name())));

        // Transforma los roles del usuario en permisos, y luego agrega cada permiso como una autoridad a la colección
        // de autoridades del usuario
        user.getRoles().stream()
                .flatMap(role -> role.getAuthorities().stream())
                .forEach(authority -> authorities.add(new SimpleGrantedAuthority(authority.getAuthorityEnum().name())));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
                user.isCredentialsNonExpired(), user.isAccountNonLocked(), authorities);
    }
}
