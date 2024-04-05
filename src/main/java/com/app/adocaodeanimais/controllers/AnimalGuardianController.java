package com.app.adocaodeanimais.controllers;

import com.app.adocaodeanimais.dto.animal.AnimalResponseDTO;
import com.app.adocaodeanimais.dto.animal.AnimalUpdateRequestDTO;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianListResponseDTO;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianRequestPostDTO;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianResponseDTO;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianUpdateRequestDTO;
import com.app.adocaodeanimais.exceptions.InternalErrorException;
import com.app.adocaodeanimais.services.AnimalGuardianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/animal-guardians")
public class AnimalGuardianController {
    private final AnimalGuardianService animalGuardianService;

    @PostMapping
    public ResponseEntity<AnimalGuardianResponseDTO> CreateAnimalGuardian(@Valid @RequestBody AnimalGuardianRequestPostDTO animalGuardian, UriComponentsBuilder uriComponentsBuilder) {
        try {
            AnimalGuardianResponseDTO newAnimalGuardian = this.animalGuardianService.createAnimalGuardian(animalGuardian);
            var uri = uriComponentsBuilder.path("/animal-guardians/{id}").buildAndExpand(newAnimalGuardian.animalGuardian().getId()).toUri();
            return ResponseEntity.created(uri).body(newAnimalGuardian);
        } catch (Exception e) {
            throw new InternalErrorException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<AnimalGuardianListResponseDTO> GetAllAnimalGuardians() {
        try {
            AnimalGuardianListResponseDTO animalGuardians = this.animalGuardianService.getAllAnimalGuardians();
            return ResponseEntity.ok(animalGuardians);
        } catch (Exception e) {
            throw new InternalErrorException(e.getMessage());
        }
    }

    @GetMapping("/{animaGuardianId}")
    public ResponseEntity<AnimalGuardianResponseDTO> getAnimalGuardianById(@PathVariable String animaGuardianId) {
        try {
            AnimalGuardianResponseDTO animalGuardian = this.animalGuardianService.getAnimalGuardianById(animaGuardianId);
            return ResponseEntity.ok(animalGuardian);
        } catch (Exception e) {
            throw new InternalErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{animaGuardianId}")
    public ResponseEntity<AnimalGuardianResponseDTO> deleteAnimalGuardianById(@PathVariable String animaGuardianId) {
        try {
            AnimalGuardianResponseDTO animalGuardian = this.animalGuardianService.deleteAnimalGuardianById(animaGuardianId);
            return ResponseEntity.ok(animalGuardian);
        } catch (Exception e) {
            throw new InternalErrorException(e.getMessage());
        }
    }

    @PatchMapping("/{animaGuardianId}")
    public ResponseEntity<AnimalGuardianResponseDTO> updateAnimalGuardianById(@PathVariable String animaGuardianId, @RequestBody AnimalGuardianUpdateRequestDTO animalGuardian) {
        try {
            AnimalGuardianResponseDTO animalGuardianUpdated = this.animalGuardianService.updateAnimalGuardianById(animaGuardianId, animalGuardian);
            return ResponseEntity.ok(animalGuardianUpdated);
        } catch (Exception e) {
            throw new InternalErrorException(e.getMessage());
        }
    }
}
