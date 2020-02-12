package com.hutchison.runeshare.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hutchison.runeshare.model.dto.KeywordDto;
import com.hutchison.runeshare.model.dto.RarityDto;
import com.hutchison.runeshare.model.dto.RegionDto;
import com.hutchison.runeshare.model.dto.SpellSpeedDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@FieldDefaults(level = PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CoreInput {
    @JsonDeserialize(using = KeywordDeserializer.class)
    List<KeywordDto> keywords;
    @JsonDeserialize(using = RarityDeserializer.class)
    List<RarityDto> rarities;
    @JsonDeserialize(using = RegionDeserializer.class)
    List<RegionDto> regions;
    @JsonDeserialize(using = SpellSpeedDeserializer.class)
    List<SpellSpeedDto> spellSpeeds;

    private static class KeywordDeserializer extends ListDeserializer<KeywordDeserializer> {
    }

    private static class RarityDeserializer extends ListDeserializer<RarityDto> {
    }

    private static class RegionDeserializer extends ListDeserializer<RegionDto> {
    }

    private static class SpellSpeedDeserializer extends ListDeserializer<SpellSpeedDto> {
    }
}
