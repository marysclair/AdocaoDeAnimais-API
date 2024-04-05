package com.app.adocaodeanimais.dto.adoption;

import com.app.adocaodeanimais.domain.animal.Animal;
import com.app.adocaodeanimais.domain.animalGuardian.AnimalGuardian;

import java.time.LocalDateTime;

public record AdoptionRequestPostDTO(String id, Animal animal, AnimalGuardian animalGuardian, LocalDateTime date, String reason) {
}
