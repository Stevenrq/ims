package com.sgivu.backend.controller;

import com.sgivu.backend.model.dto.DtoConverter;
import com.sgivu.backend.model.dto.UserDto;
import com.sgivu.backend.model.entity.User;
import com.sgivu.backend.service.UserService;
import com.sgivu.backend.util.ValidationUtil;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", originPatterns = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.validation(bindingResult);
        }
        user.setAdmin(true);
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoConverter.convertUserToDto(userService.save(user)));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.validation(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoConverter.convertUserToDto(userService.save(user)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> get(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(DtoConverter.convertUserToDto(user.orElseThrow()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> users = userService.findAll().stream()
                .map(DtoConverter::convertUserToDto).toList();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.validation(bindingResult);
        }
        Optional<User> userOptional = userService.update(id, user);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(DtoConverter.convertUserToDto(userOptional.orElseThrow()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
