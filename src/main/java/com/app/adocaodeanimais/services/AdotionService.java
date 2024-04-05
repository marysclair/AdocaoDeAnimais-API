package com.app.adocaodeanimais.services;

import com.app.adocaodeanimais.domain.adotion.Adotion;
import com.app.adocaodeanimais.domain.animalGuardian.AnimalGuardian;
import com.app.adocaodeanimais.dto.adotion.AdotionListResponseDTO;
import com.app.adocaodeanimais.dto.adotion.AdotionRequestPostDTO;
import com.app.adocaodeanimais.dto.adotion.AdotionResponseDTO;
import com.app.adocaodeanimais.dto.adotion.AdotionUpdateRequestDTO;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianListResponseDTO;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianRequestPostDTO;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianResponseDTO;
import com.app.adocaodeanimais.dto.animalGuardian.AnimalGuardianUpdateRequestDTO;
import com.app.adocaodeanimais.repositories.AdotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdotionService {
    private final AdotionRepository adotionRepository;

    public AdotionResponseDTO CreateAdotion(AdotionRequestPostDTO adotion) {
        Adotion newAdotion = new Adotion();
        newAdotion.setAnimal(adotion.animal());
        newAdotion.setAnimalGuardian(adotion.animalGuardian());
        newAdotion.setDate(adotion.date());
        newAdotion.setReason(adotion.reason());

        this.adotionRepository.save(newAdotion);

        return new AdotionResponseDTO(newAdotion);
    }

    public AdotionListResponseDTO GetAllAdotions() {
        List<Adotion> adotions = this.adotionRepository.findAll();
        return new AdotionListResponseDTO(adotions);
    }

    public AdotionResponseDTO GetAdotionById(String adotionId) {
        Adotion adotion = this.adotionRepository.findById(adotionId).orElseThrow(() ->
                new RuntimeException("adotion not found"));
        return new AdotionResponseDTO(adotion);
    }

    public AdotionResponseDTO DeleteAdotionById(String adotionId) {
        Adotion adotion = this.adotionRepository.findById(adotionId).orElseThrow(() ->
                new RuntimeException("adotion not found"));
        this.adotionRepository.deleteById(adotionId);
        return new AdotionResponseDTO(adotion);
    }

    public AdotionResponseDTO UpdateAdotionById(String adotionId, AdotionUpdateRequestDTO adotion) {
        Adotion adotionUpdated = this.adotionRepository.findById(adotionId).orElseThrow(() ->
                new RuntimeException("adotion not found"));
        adotionUpdated.setReason(adotion.reason());
        return new AdotionResponseDTO(adotionUpdated);
    }

}
