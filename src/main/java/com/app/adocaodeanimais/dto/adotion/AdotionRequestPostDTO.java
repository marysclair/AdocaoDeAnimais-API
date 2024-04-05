package com.app.adocaodeanimais.dto.adotion;

import com.app.adocaodeanimais.domain.animal.Animal;
import com.app.adocaodeanimais.domain.animalGuardian.AnimalGuardian;

import java.time.LocalDateTime;

public record AdotionRequestPostDTO(String id, Animal animal, AnimalGuardian animalGuardian, LocalDateTime date, String reason) {
}
