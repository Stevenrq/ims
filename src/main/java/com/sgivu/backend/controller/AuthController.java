package com.sgivu.backend.controller;

import com.sgivu.backend.model.entity.Authority;
import com.sgivu.backend.model.entity.Role;
import com.sgivu.backend.service.AuthorityService;
import com.sgivu.backend.service.RoleService;
import com.sgivu.backend.util.ValidationUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthorityService authorityService;
    private final RoleService roleService;

    public AuthController(AuthorityService authorityService, RoleService roleService) {
        this.authorityService = authorityService;
        this.roleService = roleService;
    }

    @PostMapping("/authorities")
    public ResponseEntity<?> saveAuthority(@Valid @RequestBody Authority authority, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.validation(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(authorityService.save(authority));
    }

    @PostMapping("/authorities/save-all")
    public ResponseEntity<?> saveAllAuthorities(@Valid @RequestBody Set<Authority> authorities, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.validation(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(authorityService.saveAll(authorities));
    }

    @GetMapping("/authorities")
    public ResponseEntity<List<Authority>> getAllAuthorities() {
        return ResponseEntity.ok(authorityService.findAll());
    }

    @GetMapping("/authorities/{id}")
    public ResponseEntity<Authority> getAuthority(@PathVariable Long id) {
        Optional<Authority> authorityOptional = authorityService.findById(id);
        if (authorityOptional.isPresent()) {
            return ResponseEntity.ok(authorityOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/roles")
    public ResponseEntity<?> saveRole(@Valid @RequestBody Role role, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.validation(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(role));
    }

    @PostMapping("/roles/save-all")
    public ResponseEntity<?> saveAllRoles(@Valid @RequestBody Set<Role> roles, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.validation(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.saveAll(roles));
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Long id) {
        Optional<Role> roleOptional = roleService.findById(id);
        if (roleOptional.isPresent()) {
            return ResponseEntity.ok(roleOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
