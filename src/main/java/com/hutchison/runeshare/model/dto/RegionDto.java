package com.hutchison.runeshare.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@JsonDeserialize(builder = RegionDto.RegionDtoBuilder.class)
@Builder
public class RegionDto {
    String abbreviation;
    String iconAbsolutePath;
    String name;
    String nameRef;

    @JsonPOJOBuilder(withPrefix = "")
    public static class RegionDtoBuilder {

    }
}
