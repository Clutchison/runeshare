package com.hutchison.runeshare.persistence.repository;

import com.hutchison.runeshare.persistence.entity.Rarity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RarityRepository extends JpaRepository<Rarity, Long> {
}
