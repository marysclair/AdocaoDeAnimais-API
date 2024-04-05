package com.app.adocaodeanimais.controllers;

import com.app.adocaodeanimais.domain.adoption.Adoption;
import com.app.adocaodeanimais.dto.adoption.AdoptionListResponseDTO;
import com.app.adocaodeanimais.dto.adoption.AdoptionRequestPostDTO;
import com.app.adocaodeanimais.dto.adoption.AdoptionResponseDTO;
import com.app.adocaodeanimais.dto.adoption.AdoptionUpdateRequestDTO;
import com.app.adocaodeanimais.dto.animal.AnimalResponseListDTO;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianListResponseDTO;
import com.app.adocaodeanimais.exceptions.InternalErrorException;
import com.app.adocaodeanimais.services.AdoptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/adoptions")
@RequiredArgsConstructor
public class AdoptionController {
    private final AdoptionService adoptionService;

    @PostMapping
    public ResponseEntity<AdoptionResponseDTO> createAdoption(@RequestBody AdoptionRequestPostDTO adoption, UriComponentsBuilder uriComponentsBuilder) {
        try {
            AdoptionResponseDTO newAdoption = this.adoptionService.createAdoption(adoption);
            var uri = uriComponentsBuilder.path("/adoptions/{id}").buildAndExpand(newAdoption.adoption().getId()).toUri();
            return ResponseEntity.created(uri).body(newAdoption);
        } catch (Exception e) {
            throw new InternalErrorException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<AdoptionListResponseDTO> getAllAdoptions() {
        try {
            AdoptionListResponseDTO adoptions = this.adoptionService.getAllAdoptions();
            return ResponseEntity.ok(adoptions);
        } catch (Exception e) {
            throw new InternalErrorException(e.getMessage());
        }
    }

    @GetMapping("/{adoptionId}")
    public ResponseEntity<AdoptionResponseDTO> getAdoptionById(@PathVariable String adoptionId) {
        try {
            AdoptionResponseDTO adoption = this.adoptionService.getAdoptionById(adoptionId);
            return ResponseEntity.ok(adoption);
        } catch (Exception e) {
            throw new InternalErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{adoptionId}")
    public ResponseEntity<AdoptionResponseDTO> deleteAdoptionById(@PathVariable String adoptionId) {
        try {
            AdoptionResponseDTO adoption = this.adoptionService.deleteAdoptionById(adoptionId);
            return ResponseEntity.ok(adoption);
        } catch (Exception e) {
            throw new InternalErrorException(e.getMessage());
        }
    }

    @PatchMapping("/{adoptionId}")
    public ResponseEntity<AdoptionResponseDTO> updateAdoptionById(@PathVariable String adoptionId, @RequestBody AdoptionUpdateRequestDTO adoption) {
        try {
            AdoptionResponseDTO adoptionUpdated = this.adoptionService.updateAdoptionById(adoptionId, adoption);
            return ResponseEntity.ok(adoptionUpdated);
        } catch (Exception e) {
            throw new InternalErrorException(e.getMessage());
        }
    }

    @GetMapping("/animals/{animalGuardianId}")
    public ResponseEntity<AnimalResponseListDTO> getAnimalsAdoptedByAnimalGuardianId(@PathVariable String animalGuardianId) {
        try {
            AnimalResponseListDTO animals = this.adoptionService.getAnimalsAdoptedByAnimalGuardianId(animalGuardianId);
            return ResponseEntity.ok(animals);
        } catch (Exception e) {
            throw new InternalErrorException(e.getMessage());
        }
    }

    @GetMapping("/animalGuardians/{animalId}")
    public ResponseEntity<AnimalGuardianListResponseDTO> getAnimalGuardiansByAnimalId(@PathVariable String animalId) {
        try {
            AnimalGuardianListResponseDTO animalGuardians = this.adoptionService.getAnimalGuardiansByAnimalId(animalId);
            return ResponseEntity.ok(animalGuardians);
        } catch (Exception e) {
            throw new InternalErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/animalGuardian/{animalGuardianId}")
    public ResponseEntity<AdoptionListResponseDTO> deleteAdoptionsByAnimalGuardianId(@PathVariable String animalGuardianId){
        try {
            AdoptionListResponseDTO adoptions = this.adoptionService.deleteAdoptionByAnimalGuardianId(animalGuardianId);
            return ResponseEntity.ok(adoptions);
        } catch (Exception e) {
            throw new InternalErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/animal/{animalId}")
    public ResponseEntity<AdoptionListResponseDTO> deleteAdoptionsByAnimalId(@PathVariable String animalId){
        try {
            AdoptionListResponseDTO adoptions = this.adoptionService.deleteAdoptionByAnimalId(animalId);
            return ResponseEntity.ok(adoptions);
        } catch (Exception e) {
            throw new InternalErrorException(e.getMessage());
        }
    }

}
