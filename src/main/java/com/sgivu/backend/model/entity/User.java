package com.sgivu.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sgivu.backend.model.RoleEnum;
import com.sgivu.backend.validation.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends Person {

    @Column(unique = true, nullable = false)
    @ExistsByUsername
    @NotBlank
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(unique = true, nullable = false)
    @ExistsByPassword
    @NotBlank
    private String password;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Contract> contracts;

    @Transient
    private boolean admin;

    @PrePersist
    public void prePersist() {
        this.isEnabled = true;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
    }

    /**
     * Verifica si el usuario tiene el rol especificado.
     *
     * @param roleEnum nombre del rol
     * @return true si el usuario tiene el rol especificado, false de caso contrario
     */
    public boolean hasRole(RoleEnum roleEnum) {
        for (Role role : roles) {
            if (role.getRoleEnum().equals(roleEnum)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si el usuario no tiene roles asignados.
     *
     * @return true si no tiene roles, false en caso contrario
     */
    public boolean hasAnyRole() {
        return roles.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        User user = (User) o;
        return isEnabled == user.isEnabled && accountNonExpired == user.accountNonExpired
                && accountNonLocked == user.accountNonLocked && credentialsNonExpired == user.credentialsNonExpired
                && Objects.equals(username, user.username) && Objects.equals(password, user.password)
                && Objects.equals(roles, user.roles) && Objects.equals(contracts, user.contracts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, isEnabled, accountNonExpired, accountNonLocked,
                credentialsNonExpired, roles, contracts);
    }
}
