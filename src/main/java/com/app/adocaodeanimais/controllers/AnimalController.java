package com.app.adocaodeanimais.controllers;

import com.app.adocaodeanimais.dto.animal.AnimalRequestPostDTO;
import com.app.adocaodeanimais.dto.animal.AnimalResponseDTO;
import com.app.adocaodeanimais.dto.animal.AnimalResponseListDTO;
import com.app.adocaodeanimais.dto.animal.AnimalUpdateRequestDTO;
import com.app.adocaodeanimais.exceptions.InternalErrorException;
import com.app.adocaodeanimais.services.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/animals")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<AnimalResponseDTO> createAnimal(@RequestBody AnimalRequestPostDTO animal, UriComponentsBuilder uriComponentsBuilder) {
        try {
            AnimalResponseDTO newAnimal = this.animalService.createAnimal(animal);
            var uri = uriComponentsBuilder.path("/animals/{id}").buildAndExpand(newAnimal.animal().getId()).toUri();
            return ResponseEntity.created(uri).body(newAnimal);
        } catch (Exception e) {
            throw new InternalErrorException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<AnimalResponseListDTO> getAllAnimals() {
        try {
            AnimalResponseListDTO animals = this.animalService.getAllAnimals();
            return ResponseEntity.ok(animals);
        } catch (Exception e) {
            throw new InternalErrorException(e.getMessage());
        }
    }

    @GetMapping("/{animalId}")
    public ResponseEntity<AnimalResponseDTO> getAnimalById(@PathVariable String animalId) {
        try {
            AnimalResponseDTO animal = this.animalService.getAnimalById(animalId);
            return ResponseEntity.ok(animal);
        } catch (Exception e) {
            throw new InternalErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{animalId}")
    public ResponseEntity<AnimalResponseDTO> deleteAnimalById(@PathVariable String animalId) {
        try {
            AnimalResponseDTO animal = this.animalService.deleteAnimalById(animalId);
            return ResponseEntity.ok(animal);
        } catch (Exception e) {
            throw new InternalErrorException(e.getMessage());
        }
    }

    @PatchMapping("/{animalId}")
    public ResponseEntity<AnimalResponseDTO> updateAnimalById(@PathVariable String animalId, @RequestBody AnimalUpdateRequestDTO animal) {
        try {
            AnimalResponseDTO animalUpdated = this.animalService.updateAnimalById(animalId, animal);
            return ResponseEntity.ok(animalUpdated);
        } catch (Exception e) {
            throw new InternalErrorException(e.getMessage());
        }
    }
}
