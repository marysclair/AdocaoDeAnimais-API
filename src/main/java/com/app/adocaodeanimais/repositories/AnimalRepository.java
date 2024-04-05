package com.app.adocaodeanimais.repositories;

import com.app.adocaodeanimais.domain.animal.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, String> {
}
