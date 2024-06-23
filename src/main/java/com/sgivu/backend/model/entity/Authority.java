package com.sgivu.backend.model.entity;

import com.sgivu.backend.model.AuthorityEnum;
import com.sgivu.backend.validation.annotation.ExistsByAuthorityEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authority", unique = true, updatable = false)
    @ExistsByAuthorityEnum
    @Enumerated(EnumType.STRING)
    private AuthorityEnum authorityEnum;
}
