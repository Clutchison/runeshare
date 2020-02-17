package com.hutchison.runeshare.persistence.repository;

import com.hutchison.runeshare.persistence.entity.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardCode(String cardCode);
}