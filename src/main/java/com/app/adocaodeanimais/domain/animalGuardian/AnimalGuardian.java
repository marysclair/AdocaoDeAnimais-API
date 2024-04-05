package com.app.adocaodeanimais.domain.animalGuardian;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp="^.{15}$", message = "Invalid phone number format")
    private String phoneNumber;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false, unique = true)
    @Pattern(regexp="^.{14}$", message = "Invalid phone number format")
    private String cpf;
}
