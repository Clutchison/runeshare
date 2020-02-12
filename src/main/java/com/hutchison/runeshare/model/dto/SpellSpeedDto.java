package com.hutchison.runeshare.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@JsonDeserialize(builder = SpellSpeedDto.SpellSpeedDtoBuilder.class)
@Builder
public class SpellSpeedDto {
    String name;
    String nameRef;

    @JsonPOJOBuilder(withPrefix = "")
    public static class SpellSpeedDtoBuilder {

    }
}
