package com.app.adocaodeanimais.services;

import com.app.adocaodeanimais.domain.animalGuardian.AnimalGuardian;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianListResponseDTO;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianRequestPostDTO;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianResponseDTO;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianUpdateRequestDTO;
import com.app.adocaodeanimais.exceptions.NotFoundException;
import com.app.adocaodeanimais.repositories.AnimalGuardianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalGuardianService {
    private final AnimalGuardianRepository animalGuardianRepository;

    public AnimalGuardianResponseDTO createAnimalGuardian(AnimalGuardianRequestPostDTO animalGuardian) {
        AnimalGuardian newAnimalGuardian = new AnimalGuardian();
        newAnimalGuardian.setName(animalGuardian.name());
        newAnimalGuardian.setAddress(animalGuardian.address());
        newAnimalGuardian.setPhoneNumber("(" + animalGuardian.phoneNumber().substring(0, 2) + ") " +
                animalGuardian.phoneNumber().substring(2, 7) + "-" +
                animalGuardian.phoneNumber().substring(7));
        newAnimalGuardian.setCpf(animalGuardian.cpf().substring(0, 3) + "." +
                animalGuardian.cpf().substring(3, 6) + "." +
                animalGuardian.cpf().substring(6, 9) + "-" +
                animalGuardian.cpf().substring(9));

        this.animalGuardianRepository.save(newAnimalGuardian);

        return new AnimalGuardianResponseDTO(newAnimalGuardian);
    }

    public AnimalGuardianListResponseDTO getAllAnimalGuardians() {
        List<AnimalGuardian> animalGuardians = this.animalGuardianRepository.findAll();
        return new AnimalGuardianListResponseDTO(animalGuardians);
    }

    public AnimalGuardianResponseDTO getAnimalGuardianById(String animalGuardianId) {
        AnimalGuardian animalGuardian = this.animalGuardianRepository.findById(animalGuardianId).orElseThrow(() ->
                new NotFoundException("animal guardian not found by id: " + animalGuardianId));
        return new AnimalGuardianResponseDTO(animalGuardian);
    }

    public AnimalGuardianResponseDTO deleteAnimalGuardianById(String animalGuardianId) {
        AnimalGuardian animalGuardian = this.animalGuardianRepository.findById(animalGuardianId).orElseThrow(() ->
                new NotFoundException("animal guardian not found by id: " + animalGuardianId));
        this.animalGuardianRepository.deleteById(animalGuardianId);
        return new AnimalGuardianResponseDTO(animalGuardian);
    }

    public AnimalGuardianResponseDTO updateAnimalGuardianById(String animalGuardianId, AnimalGuardianUpdateRequestDTO animalGuardian) {
        AnimalGuardian animalGuardianUpdated = this.animalGuardianRepository.findById(animalGuardianId).orElseThrow(() ->
                new NotFoundException("animal guardian not found by id: " + animalGuardianId));
        animalGuardianUpdated.setName(animalGuardian.name());
        animalGuardianUpdated.setPhoneNumber(animalGuardian.phoneNumber());
        animalGuardianUpdated.setAddress(animalGuardian.address());
        return new AnimalGuardianResponseDTO(animalGuardianUpdated);
    }

}
