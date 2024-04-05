package com.app.adocaodeanimais.dto.adoption;

import com.app.adocaodeanimais.domain.adoption.Adoption;

import java.util.List;

public record AdoptionListResponseDTO(List<Adoption> adoptions) {
}
