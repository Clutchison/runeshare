package com.hutchison.runeshare.util.json;

import com.hutchison.runeshare.model.entity.Keyword;
import com.hutchison.runeshare.model.entity.Rarity;
import com.hutchison.runeshare.model.entity.Region;
import com.hutchison.runeshare.model.entity.SpellSpeed;
import com.hutchison.runeshare.model.entity.card.Card;
import com.hutchison.runeshare.model.entity.card.CardFactory;
import com.hutchison.runeshare.persistence.repository.*;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
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
        CoreInput coreInput = JsonReader.readCoreInput();
        SetInput setInput = JsonReader.readSetInput();
        keywordRepository.saveAll(coreInput.getKeywords().stream()
                .map(Keyword::fromDto)
                .collect(Collectors.toList()));
        rarityRepository.saveAll(coreInput.getRarities().stream()
                .map(Rarity::fromDto)
                .collect(Collectors.toList()));
        regionRepository.saveAll(coreInput.getRegions().stream()
                .map(Region::fromDto)
                .collect(Collectors.toList()));
        spellSpeedRepository.saveAll(coreInput.getSpellSpeeds().stream()
                .map(SpellSpeed::fromDto)
                .collect(Collectors.toList()));
        List<Card> collect = setInput.getCards().stream()
                .map(cardFactory::fromDto)
                .collect(Collectors.toList());
        cardRepository.saveAll(collect);
    }
}
