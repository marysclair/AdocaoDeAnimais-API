package com.app.adocaodeanimais.services;

import com.app.adocaodeanimais.domain.adoption.Adoption;
import com.app.adocaodeanimais.domain.animal.Animal;
import com.app.adocaodeanimais.domain.animalGuardian.AnimalGuardian;
import com.app.adocaodeanimais.dto.adoption.AdoptionListResponseDTO;
import com.app.adocaodeanimais.dto.adoption.AdoptionRequestPostDTO;
import com.app.adocaodeanimais.dto.adoption.AdoptionResponseDTO;
import com.app.adocaodeanimais.dto.adoption.AdoptionUpdateRequestDTO;
import com.app.adocaodeanimais.dto.animal.AnimalResponseDTO;
import com.app.adocaodeanimais.dto.animal.AnimalResponseListDTO;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianListResponseDTO;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianResponseDTO;
import com.app.adocaodeanimais.exceptions.NotFoundException;
import com.app.adocaodeanimais.repositories.AdoptionRepository;
import com.app.adocaodeanimais.repositories.AnimalGuardianRepository;
import com.app.adocaodeanimais.repositories.AnimalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionService {
    private final AdoptionRepository adoptionRepository;
    private final AnimalService animalService;
    private final AnimalGuardianService animalGuardianService;

    public AdoptionResponseDTO createAdoption(AdoptionRequestPostDTO adoption) {
        Adoption newAdoption = new Adoption();
        AnimalResponseDTO animal = this.animalService.getAnimalById(adoption.animalId());
        newAdoption.setAnimal(animal.animal());
        AnimalGuardianResponseDTO animalGuardian = this.animalGuardianService.getAnimalGuardianById(adoption.animalGuardianId());
        newAdoption.setAnimalGuardian(animalGuardian.animalGuardian());
        newAdoption.setReason(adoption.reason());

        this.adoptionRepository.save(newAdoption);

        return new AdoptionResponseDTO(newAdoption);
    }

    public AdoptionListResponseDTO getAllAdoptions() {
        List<Adoption> adoptions = this.adoptionRepository.findAll();
        return new AdoptionListResponseDTO(adoptions);
    }

    public AdoptionResponseDTO getAdoptionById(String adoptionId) {
        Adoption adoption = this.adoptionRepository.findById(adoptionId).orElseThrow(() ->
                new RuntimeException("adoption not found"));
        return new AdoptionResponseDTO(adoption);
    }

    public AdoptionResponseDTO deleteAdoptionById(String adoptionId) {
        Adoption adoption = this.adoptionRepository.findById(adoptionId).orElseThrow(() ->
                new RuntimeException("adoption not found"));
        this.adoptionRepository.deleteById(adoptionId);
        return new AdoptionResponseDTO(adoption);
    }

    public AdoptionResponseDTO updateAdoptionById(String adoptionId, AdoptionUpdateRequestDTO adoption) {
        Adoption adoptionUpdated = this.adoptionRepository.findById(adoptionId).orElseThrow(() ->
                new RuntimeException("adoption not found"));
        adoptionUpdated.setReason(adoption.reason());
        return new AdoptionResponseDTO(adoptionUpdated);
    }

    public AnimalResponseListDTO getAnimalsAdoptedByAnimalGuardianId(String animalGuardianId){
        List<Adoption> adoptions = this.adoptionRepository.getAdoptionByAnimalGuardianId(animalGuardianId);
        List<Animal> animals = adoptions.stream().map(Adoption::getAnimal).toList();
        return new AnimalResponseListDTO(animals);
    }

    public AnimalGuardianListResponseDTO getAnimalGuardiansByAnimalId(String animalId){
        List<Adoption> adoptions = this.adoptionRepository.getAdoptionByAnimalId(animalId);
        List<AnimalGuardian> animalGuardians = adoptions.stream().map(Adoption::getAnimalGuardian).toList();
        return new AnimalGuardianListResponseDTO(animalGuardians);
    }

    @Transactional
    public AdoptionListResponseDTO deleteAdoptionByAnimalGuardianId(String animalGuardianId){
        AnimalGuardianResponseDTO animalGuardian = this.animalGuardianService.getAnimalGuardianById(animalGuardianId);
        List<Adoption> adoptions = this.adoptionRepository.deleteByAnimalGuardian(animalGuardian.animalGuardian());
        return new AdoptionListResponseDTO(adoptions);
    }

    @Transactional
    public AdoptionListResponseDTO deleteAdoptionByAnimalId(String animalId){
        AnimalResponseDTO animal = this.animalService.getAnimalById(animalId);
        List<Adoption> adoptions =this.adoptionRepository.deleteByAnimal(animal.animal());
        return new AdoptionListResponseDTO(adoptions);
    }

}
