package com.hutchison.runeshare.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.hutchison.runeshare.persistence.entity.Assets;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@JsonDeserialize(builder = CardDto.CardDtoBuilder.class)
@Builder
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

    @JsonPOJOBuilder(withPrefix = "")
    public static class CardDtoBuilder {

    }
}
