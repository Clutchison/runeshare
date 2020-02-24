package com.hutchison.runeshare.util.json;

import com.hutchison.runeshare.model.dto.CardDto;
import com.hutchison.runeshare.model.entity.Keyword;
import com.hutchison.runeshare.model.entity.Rarity;
import com.hutchison.runeshare.model.entity.Region;
import com.hutchison.runeshare.model.entity.SpellSpeed;
import com.hutchison.runeshare.model.entity.card.Card;
import com.hutchison.runeshare.model.entity.card.CardFactory;
import com.hutchison.runeshare.repository.*;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Value
@Component
public class DBLoader {

    List<JpaRepository> repositories;
    KeywordRepository keywordRepository;
    RarityRepository rarityRepository;
    RegionRepository regionRepository;
    SpellSpeedRepository spellSpeedRepository;
    CardRepository cardRepository;
    AssetsRepository assetsRepository;

    CardFactory cardFactory;

    @Autowired
    public DBLoader(
            KeywordRepository keywordRepository,
            RarityRepository rarityRepository,
            RegionRepository regionRepository,
            SpellSpeedRepository spellSpeedRepository,
            CardRepository cardRepository,
            AssetsRepository assetsRepository,
            CardFactory cardFactory
    ) {
        this.keywordRepository = keywordRepository;
        this.rarityRepository = rarityRepository;
        this.regionRepository = regionRepository;
        this.spellSpeedRepository = spellSpeedRepository;
        this.cardRepository = cardRepository;
        this.assetsRepository = assetsRepository;
        repositories = Arrays.asList(
                keywordRepository,
                rarityRepository,
                regionRepository,
                spellSpeedRepository,
                cardRepository
        );
        this.cardFactory = cardFactory;
    }

    public void load() {
        repositories.forEach(JpaRepository::deleteAllInBatch);
        com.hutchison.runeshare.util.json.CoreInput coreInput = com.hutchison.runeshare.util.json.JsonReader.readCoreInput();
        com.hutchison.runeshare.util.json.SetInput setInput = com.hutchison.runeshare.util.json.JsonReader.readSetInput();
        keywordRepository.saveAll(coreInput.getKeywords().stream()
                .map(Keyword::fromDto)
                .collect(Collectors.toSet()));
        rarityRepository.saveAll(coreInput.getRarities().stream()
                .map(Rarity::fromDto)
                .collect(Collectors.toSet()));
        regionRepository.saveAll(coreInput.getRegions().stream()
                .map(Region::fromDto)
                .collect(Collectors.toSet()));
        spellSpeedRepository.saveAll(coreInput.getSpellSpeeds().stream()
                .map(SpellSpeed::fromDto)
                .collect(Collectors.toSet()));
        cardRepository.saveAll(loadCards(setInput));
    }

    private Set<Card> loadCards(SetInput setInput) {
        // Create Cards first without associated cards populated.
        Map<CardDto, Card> cardDtoCardMap = setInput.getCards().stream()
                .collect(Collectors.toMap(
                        dto -> dto,
                        cardFactory::fromDto
                ));
        // Populate associated cards after first pass.
        return cardDtoCardMap.entrySet().stream()
                .peek(es -> es.getValue().setAssociatedCards(
                        cardDtoCardMap.values().stream()
                                .filter(card -> es.getKey().getAssociatedCardRefs().contains(card.getCardCode()))
                                .collect(Collectors.toSet())))
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());
    }
}
