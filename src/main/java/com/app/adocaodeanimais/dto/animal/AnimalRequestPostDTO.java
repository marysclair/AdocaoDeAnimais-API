package com.app.adocaodeanimais.dto.animal;

import java.time.LocalDate;

public record AnimalRequestPostDTO(String name, LocalDate dateOfBirth, String personality,
                                   String species, String breed) {
}
