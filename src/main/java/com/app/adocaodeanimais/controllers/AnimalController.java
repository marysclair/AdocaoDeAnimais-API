package com.app.adocaodeanimais.controllers;

import com.app.adocaodeanimais.dto.animal.AnimalRequestPostDTO;
import com.app.adocaodeanimais.dto.animal.AnimalResponseDTO;
import com.app.adocaodeanimais.dto.animal.AnimalResponseListDTO;
import com.app.adocaodeanimais.dto.animal.AnimalUpdateRequestDTO;
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
    public ResponseEntity<AnimalResponseDTO> CreateAnimal(@RequestBody AnimalRequestPostDTO animal, UriComponentsBuilder uriComponentsBuilder) {
        try {
            AnimalResponseDTO newAnimal = this.animalService.CreateAnimal(animal);
            var uri = uriComponentsBuilder.path("/todos/{id}").buildAndExpand(newAnimal.animal().getId()).toUri();
            return ResponseEntity.created(uri).body(newAnimal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<AnimalResponseListDTO> GetAllAnimals() {
        try {
            AnimalResponseListDTO animals = this.animalService.GetAllAnimals();
            return ResponseEntity.ok(animals);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{animalId}")
    public ResponseEntity<AnimalResponseDTO> GetAnimalById(@PathVariable String animalId) {
        try {
            AnimalResponseDTO animal = this.animalService.GetAnimalById(animalId);
            return ResponseEntity.ok(animal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{animalId}")
    public ResponseEntity<AnimalResponseDTO> DeleteAnimalById(@PathVariable String animalId) {
        try {
            AnimalResponseDTO animal = this.animalService.DeleteAnimalById(animalId);
            return ResponseEntity.ok(animal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/{animalId}")
    public ResponseEntity<AnimalResponseDTO> UpdateAnimalById(@PathVariable String animalId, @RequestBody AnimalUpdateRequestDTO animal) {
        try {
            AnimalResponseDTO animalUpdated = this.animalService.UpdateAnimalById(animalId, animal);
            return ResponseEntity.ok(animalUpdated);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
