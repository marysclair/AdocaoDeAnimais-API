package com.app.adocaodeanimais.domain.animalGuardian;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "animal-guardian")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnimalGuardian {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false, unique = true)
    private String cpf;
}
