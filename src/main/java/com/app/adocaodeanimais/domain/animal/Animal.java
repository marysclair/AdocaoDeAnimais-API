package com.app.adocaodeanimais.domain.animal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, name = "date_of_birth")
    private LocalDate dateOfBirth = LocalDate.now();
    @Column(nullable = false)
    private String personality;
    @Column(nullable = false)
    private String species;
    @Column(nullable = false)
    private String breed;
}
