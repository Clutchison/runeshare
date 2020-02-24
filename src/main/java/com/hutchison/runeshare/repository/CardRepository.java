package com.hutchison.runeshare.repository;

import com.hutchison.runeshare.model.entity.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardCode(String cardCode);

    Card findByName(String cardName);
}
