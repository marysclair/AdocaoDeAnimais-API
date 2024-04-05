package com.app.adocaodeanimais.dto.animalGuardian;

import com.app.adocaodeanimais.domain.animalGuardian.AnimalGuardian;

import java.util.List;

public record AnimalGuardianListResponseDTO(List<AnimalGuardian> animalGuardians) {
}
