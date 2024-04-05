package com.app.adocaodeanimais.dto.animal;

import java.time.LocalDate;

public record AnimalUpdateRequestDTO(String name, LocalDate dateOfBirth) {
}
