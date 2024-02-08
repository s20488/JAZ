package com.westeros.data.repositories;

import com.westeros.data.model.SpokenLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<SpokenLanguage, Integer> {
}
