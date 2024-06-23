package com.sgivu.backend.model.entity;

import com.sgivu.backend.validation.annotation.ExistsByEmail;
import com.sgivu.backend.validation.annotation.ExistsByIdentificationCard;
import com.sgivu.backend.validation.annotation.ExistsByPhone;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@Data
@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identification_card", unique = true, nullable = false)
    @ExistsByIdentificationCard
    @NotNull
    private Long identificationCard;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    private String lastName;

    @Column(nullable = false)
    @NotBlank
    private String address;

    @Column(unique = true, nullable = false)
    @ExistsByPhone
    @NotNull
    private Long phone;

    @Column(unique = true, nullable = false)
    @ExistsByEmail
    @Email
    @NotBlank
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
