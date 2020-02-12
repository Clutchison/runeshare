package com.hutchison.runeshare.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@JsonDeserialize(builder = KeywordDto.KeywordDtoBuilder.class)
@Builder
public class KeywordDto {
    String description;
    String name;
    String nameRef;

    @JsonPOJOBuilder(withPrefix = "")
    public static class KeywordDtoBuilder {

    }
}
