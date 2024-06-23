package com.sgivu.backend.util;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ValidationUtil {

    /**
     * Realiza la validación de un resultado de enlace.
     *
     * @param bindingResult El resultado del enlace que contiene los errores de validación.
     * @return Una respuesta de entidad HTTP con los errores de validación en el cuerpo de la respuesta.
     */
    public static ResponseEntity<?> validation(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(),
                "El campo " + error.getField() + " " + error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
