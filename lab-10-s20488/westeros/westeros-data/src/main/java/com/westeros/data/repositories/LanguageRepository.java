package com.westeros.data.repositories;

import com.westeros.data.model.SpokenLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<SpokenLanguage, Integer> {

    @Query("select x from SpokenLanguage x where x.name in (:names)")
    List<SpokenLanguage> withNames(List<String> names);
}
