package com.app.adocaodeanimais.repositories;

import com.app.adocaodeanimais.domain.adotion.Adotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdotionRepository extends JpaRepository<Adotion, String> {
}
