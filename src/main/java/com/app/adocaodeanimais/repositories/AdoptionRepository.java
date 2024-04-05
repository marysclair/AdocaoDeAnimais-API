package com.app.adocaodeanimais.repositories;

import com.app.adocaodeanimais.domain.adoption.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdoptionRepository extends JpaRepository<Adoption, String> {
    public List<Adoption> getAdoptionByAnimalGuardianId(String animalGuardianId);
    public List<Adoption> getAdoptionByAnimalId(String animalId);

    public List<Adoption> deleteAdoptionsByAnimalGuardianId(String animalGuardianId);

    public List<Adoption> deleteAdoptionByAnimalId(String animalId);
}
