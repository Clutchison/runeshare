package com.hutchison.runeshare.cards;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@JsonDeserialize(builder = Card.CardBuilder.class)
@Builder
public class Card {
    /*
    {
    "associatedCards": [],
    "associatedCardRefs": [],
    "assets": [
      {
        "gameAbsolutePath": "http://dd.b.pvp.net/Set1/en_us/img/cards/01IO012T2.png",
        "fullAbsolutePath": "http://dd.b.pvp.net/Set1/en_us/img/cards/01IO012T2-full.png"
      }
    ],
    "region": "Ionia",
    "regionRef": "Ionia",
    "attack": 0,
    "cost": 3,
    "health": 0,
    "description": "Give an ally +0|+3 this round.",
    "descriptionRaw": "Give an ally +0|+3 this round.",
    "levelupDescription": "",
    "levelupDescriptionRaw": "",
    "flavorText": "",
    "artistName": "SIXMOREVODKA",
    "name": "Discipline of Fortitude",
    "cardCode": "01IO012T2",
    "keywords": [
      "Burst"
    ],
    "keywordRefs": [
      "Burst"
    ],
    "spellSpeed": "Burst",
    "spellSpeedRef": "Burst",
    "rarity": "None",
    "rarityRef": "None",
    "subtype": "",
    "supertype": "",
    "type": "Spell",
    "collectible": false
  }
     */
    String code;
    String name;

    @JsonPOJOBuilder(buildMethodName = "create", withPrefix = "set")
    public static class CardBuilder {
    }
}
