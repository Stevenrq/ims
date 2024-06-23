package com.sgivu.backend.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Mixin utilizado para personalizar la deserialización de instancias de SimpleGrantedAuthority
 * desde JSON. Este mixin especifica cómo Jackson debe crear instancias de SimpleGrantedAuthority
 * a partir de datos JSON que contienen la propiedad "authority".
 */
public abstract class SimpleGrantedAuthorityJsonCreator {

    /**
     * Constructor utilizado por Jackson para deserializar SimpleGrantedAuthority desde JSON.
     * Jackson utilizará la propiedad "authority" del JSON para inicializar el parámetro 'role'
     * de este constructor.
     *
     * @param role El nombre del rol que se asignará a la instancia de SimpleGrantedAuthority.
     */
    @JsonCreator
    public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role) {
    }
}
