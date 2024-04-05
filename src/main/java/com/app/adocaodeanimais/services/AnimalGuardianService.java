package com.app.adocaodeanimais.services;

import com.app.adocaodeanimais.domain.animalGuardian.AnimalGuardian;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianListResponseDTO;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianRequestPostDTO;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianResponseDTO;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianUpdateRequestDTO;
import com.app.adocaodeanimais.repositories.AnimalGuardianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalGuardianService {
    private final AnimalGuardianRepository animalGuardianRepository;

    public AnimalGuardianResponseDTO CreateAnimalGuardian(AnimalGuardianRequestPostDTO animalGuardian) {
        AnimalGuardian newAnimalGuardian = new AnimalGuardian();
        newAnimalGuardian.setName(animalGuardian.name());
        newAnimalGuardian.setAddress(animalGuardian.address());
        newAnimalGuardian.setPhoneNumber(animalGuardian.phoneNumber());
        newAnimalGuardian.setCpf(animalGuardian.cpf());

        this.animalGuardianRepository.save(newAnimalGuardian);

        return new AnimalGuardianResponseDTO(newAnimalGuardian);
    }

    public AnimalGuardianListResponseDTO GetAllAnimalGuardians() {
        List<AnimalGuardian> animalGuardians = this.animalGuardianRepository.findAll();
        return new AnimalGuardianListResponseDTO(animalGuardians);
    }

    public AnimalGuardianResponseDTO GetAnimalGuardianById(String animalGuardianId) {
        AnimalGuardian animalGuardian = this.animalGuardianRepository.findById(animalGuardianId).orElseThrow(() ->
                new RuntimeException("animal not found"));
        return new AnimalGuardianResponseDTO(animalGuardian);
    }

    public AnimalGuardianResponseDTO DeleteAnimalGuardianById(String animalGuardianId) {
        AnimalGuardian animalGuardian = this.animalGuardianRepository.findById(animalGuardianId).orElseThrow(() ->
                new RuntimeException("animal not found"));
        this.animalGuardianRepository.deleteById(animalGuardianId);
        return new AnimalGuardianResponseDTO(animalGuardian);
    }

    public AnimalGuardianResponseDTO UpdateAnimalGuardianById(String animalGuardianId, AnimalGuardianUpdateRequestDTO animalGuardian) {
        AnimalGuardian animalGuardianUpdated = this.animalGuardianRepository.findById(animalGuardianId).orElseThrow(() ->
                new RuntimeException("animal not found"));
        animalGuardianUpdated.setName(animalGuardian.name());
        animalGuardianUpdated.setPhoneNumber(animalGuardian.phoneNumber());
        animalGuardianUpdated.setAddress(animalGuardian.address());
        return new AnimalGuardianResponseDTO(animalGuardianUpdated);
    }

}
