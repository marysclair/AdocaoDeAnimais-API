package com.app.adocaodeanimais.services;

import com.app.adocaodeanimais.domain.animal.Animal;
import com.app.adocaodeanimais.dto.animal.*;
import com.app.adocaodeanimais.exceptions.NotFoundException;
import com.app.adocaodeanimais.repositories.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalResponseDTO createAnimal(AnimalRequestPostDTO animal){
        Animal newAnimal = new Animal();
        newAnimal.setName(animal.name());
        newAnimal.setDateOfBirth(animal.dateOfBirth());
        newAnimal.setPersonality(animal.personality());
        newAnimal.setSpecies(animal.species());
        newAnimal.setBreed(animal.breed());
        System.out.println(newAnimal);
        this.animalRepository.save(newAnimal);

        return new AnimalResponseDTO(newAnimal);
    }

    public AnimalResponseListDTO getAllAnimals(){
        List<Animal> animals = this.animalRepository.findAll();
        return new AnimalResponseListDTO(animals);
    }

    public AnimalResponseDTO getAnimalById(String animalId){
        Animal animal = this.animalRepository.findById(animalId).orElseThrow(() ->
                new NotFoundException("animal not found"));
        return new AnimalResponseDTO(animal);
    }

    public AnimalResponseDTO deleteAnimalById(String animalId){
        Animal animal = this.animalRepository.findById(animalId).orElseThrow(() ->
                new NotFoundException("animal not found"));
        this.animalRepository.deleteById(animalId);
        return new AnimalResponseDTO(animal);
    }

    public AnimalResponseDTO updateAnimalById(String animalId, AnimalUpdateRequestDTO animal){
        Animal animalUpdated = this.animalRepository.findById(animalId).orElseThrow(() ->
                new NotFoundException("animal not found"));
        animalUpdated.setName(animal.name());
        animalUpdated.setDateOfBirth(animal.dateOfBirth());
        return new AnimalResponseDTO(animalUpdated);
    }
}
