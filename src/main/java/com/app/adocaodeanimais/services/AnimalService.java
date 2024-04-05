package com.app.adocaodeanimais.services;

import com.app.adocaodeanimais.domain.animal.Animal;
import com.app.adocaodeanimais.dto.animal.*;
import com.app.adocaodeanimais.repositories.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalResponseDTO CreateAnimal(AnimalRequestPostDTO animal){
        Animal newAnimal = new Animal();
        newAnimal.setName(animal.name());
        newAnimal.setDateOfBirth(animal.dateOfBirth());
        newAnimal.setPersonality(animal.personality());
        newAnimal.setSpecies(animal.species());
        newAnimal.setBreed(animal.breed());

        this.animalRepository.save(newAnimal);

        return new AnimalResponseDTO(newAnimal);
    }

    public AnimalResponseListDTO GetAllAnimals(){
        List<Animal> animals = this.animalRepository.findAll();
        return new AnimalResponseListDTO(animals);
    }

    public AnimalResponseDTO GetAnimalById(String animalId){
        Animal animal = this.animalRepository.findById(animalId).orElseThrow(() ->
                new RuntimeException("animal not found"));
        return new AnimalResponseDTO(animal);
    }

    public AnimalResponseDTO DeleteAnimalById(String animalId){
        Animal animal = this.animalRepository.findById(animalId).orElseThrow(() ->
                new RuntimeException("animal not found"));
        this.animalRepository.deleteById(animalId);
        return new AnimalResponseDTO(animal);
    }

    public AnimalResponseDTO UpdateAnimalById(String animalId, AnimalUpdateRequestDTO animal){
        Animal animalUpdated = this.animalRepository.findById(animalId).orElseThrow(() ->
                new RuntimeException("animal not found"));
        animalUpdated.setName(animal.name());
        animalUpdated.setDateOfBirth(animal.dateOfBirth());
        return new AnimalResponseDTO(animalUpdated);
    }
}
