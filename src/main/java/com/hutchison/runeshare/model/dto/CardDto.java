package com.hutchison.runeshare.model.dto;

import java.util.List;

public class CardDto {
    List<CardDto> associatedCards;
    List<String> associatedCardRefs;
    Assets assets;
    String Region;
    String RegionRef;
    Integer attack;
    Integer cost;
    Integer health;
    String description;
    String descriptionRaw;
    String levelupDescription;
    String levelupDescriptionRaw;
    String flavorText;
    String artistName;
    String name;
    String cardCode;
    List<String> keywords;
    List<String> keywordRefs;
    String spellSpeed;
    String spellSpeedRef;
    String rarity;
    String rarityRef;
    String subtype;
    String supertype;
    String type;
    Boolean collectible;
}
