package com.app.adocaodeanimais.repositories;

import com.app.adocaodeanimais.domain.animalGuardian.AnimalGuardian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalGuardianRepository extends JpaRepository<AnimalGuardian, String> {
}
