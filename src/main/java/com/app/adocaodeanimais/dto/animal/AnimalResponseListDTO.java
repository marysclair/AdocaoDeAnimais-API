package com.app.adocaodeanimais.dto.animal;

import com.app.adocaodeanimais.domain.animal.Animal;

import java.util.List;

public record AnimalResponseListDTO(List<Animal> animals) {
}
