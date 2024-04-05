package com.app.adocaodeanimais.services;

import com.app.adocaodeanimais.domain.adoption.Adoption;
import com.app.adocaodeanimais.domain.animal.Animal;
import com.app.adocaodeanimais.domain.animalGuardian.AnimalGuardian;
import com.app.adocaodeanimais.dto.adoption.AdoptionListResponseDTO;
import com.app.adocaodeanimais.dto.adoption.AdoptionRequestPostDTO;
import com.app.adocaodeanimais.dto.adoption.AdoptionResponseDTO;
import com.app.adocaodeanimais.dto.adoption.AdoptionUpdateRequestDTO;
import com.app.adocaodeanimais.dto.animal.AnimalResponseListDTO;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianListResponseDTO;
import com.app.adocaodeanimais.repositories.AdoptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionService {
    private final AdoptionRepository adoptionRepository;

    public AdoptionResponseDTO CreateAdoption(AdoptionRequestPostDTO adoption) {
        Adoption newAdoption = new Adoption();
        newAdoption.setAnimal(adoption.animal());
        newAdoption.setAnimalGuardian(adoption.animalGuardian());
        newAdoption.setDate(adoption.date());
        newAdoption.setReason(adoption.reason());

        this.adoptionRepository.save(newAdoption);

        return new AdoptionResponseDTO(newAdoption);
    }

    public AdoptionListResponseDTO GetAllAdoptions() {
        List<Adoption> adoptions = this.adoptionRepository.findAll();
        return new AdoptionListResponseDTO(adoptions);
    }

    public AdoptionResponseDTO GetAdoptionById(String adoptionId) {
        Adoption adoption = this.adoptionRepository.findById(adoptionId).orElseThrow(() ->
                new RuntimeException("adoption not found"));
        return new AdoptionResponseDTO(adoption);
    }

    public AdoptionResponseDTO DeleteAdoptionById(String adoptionId) {
        Adoption adoption = this.adoptionRepository.findById(adoptionId).orElseThrow(() ->
                new RuntimeException("adoption not found"));
        this.adoptionRepository.deleteById(adoptionId);
        return new AdoptionResponseDTO(adoption);
    }

    public AdoptionResponseDTO UpdateAdoptionById(String adoptionId, AdoptionUpdateRequestDTO adoption) {
        Adoption adoptionUpdated = this.adoptionRepository.findById(adoptionId).orElseThrow(() ->
                new RuntimeException("adoption not found"));
        adoptionUpdated.setReason(adoption.reason());
        return new AdoptionResponseDTO(adoptionUpdated);
    }

    public AnimalResponseListDTO GetAnimalsAdoptedByAnimalGuardianId(String animalGuardianId){
        List<Adoption> adoptions = this.adoptionRepository.getAdoptionByAnimalGuardianId(animalGuardianId);
        List<Animal> animals = adoptions.stream().map(Adoption::getAnimal).toList();
        return new AnimalResponseListDTO(animals);
    }

    public AnimalGuardianListResponseDTO GetAnimalGuardiansByAnimalId(String animalId){
        List<Adoption> adoptions = this.adoptionRepository.getAdoptionByAnimalId(animalId);
        List<AnimalGuardian> animalGuardians = adoptions.stream().map(Adoption::getAnimalGuardian).toList();
        return new AnimalGuardianListResponseDTO(animalGuardians);
    }

}
