package com.hutchison.runeshare.persistence.entity.card;

import com.hutchison.runeshare.model.dto.CardDto;
import com.hutchison.runeshare.persistence.entity.*;
import com.hutchison.runeshare.persistence.repository.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CardFactory {
    private final CardRepository cardRepository;
    private final AssetsRepository assetsRepository;
    private final RegionRepository regionRepository;
    private final KeywordRepository keywordRepository;
    private final SpellSpeedRepository spellSpeedRepository;
    private final RarityRepository rarityRepository;

    public CardFactory(CardRepository cardRepository,
                       AssetsRepository assetsRepository,
                       RegionRepository regionRepository,
                       KeywordRepository keywordRepository,
                       SpellSpeedRepository spellSpeedRepository,
                       RarityRepository rarityRepository) {
        this.cardRepository = cardRepository;
        this.assetsRepository = assetsRepository;
        this.regionRepository = regionRepository;
        this.keywordRepository = keywordRepository;
        this.spellSpeedRepository = spellSpeedRepository;
        this.rarityRepository = rarityRepository;
    }

    public Card fromDto(CardDto dto) {
        Set<Card> associatedCards = new HashSet<>();
        Assets assets = Assets.fromDto(dto.getAssets().get(0));
        Region region = regionRepository.findByNameRef(dto.getRegionRef());
        Set<Keyword> keywords = keywordRepository.findAllByNameRef(dto.getKeywordRefs());
        SpellSpeed spellSpeed = dto.getSpellSpeedRef() == null ? null : spellSpeedRepository.findByNameRef(dto.getSpellSpeedRef());
        Rarity rarity = rarityRepository.findByNameRef(dto.getRarityRef());

        return Card.builder()
                .associatedCards(associatedCards)
                .assets(assets)
                .region(region)
                .attack(dto.getAttack())
                .cost(dto.getCost())
                .health(dto.getHealth())
                .description(dto.getDescription())
                .descriptionRaw(dto.getDescriptionRaw())
                .levelUpDescription(dto.getLevelupDescription())
                .levelUpDescriptionRaw(dto.getLevelupDescriptionRaw())
                .flavorText(dto.getFlavorText())
                .artistName(dto.getArtistName())
                .name(dto.getName())
                .cardCode(dto.getCardCode())
                .keywords(keywords)
                .spellSpeed(spellSpeed)
                .rarity(rarity)
                .subtype(dto.getSubtype())
                .supertype(dto.getSupertype())
                .type(dto.getType())
                .collectible(dto.getCollectible())
                .build();
    }
}
