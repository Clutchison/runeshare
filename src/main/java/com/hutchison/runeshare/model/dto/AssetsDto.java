package com.hutchison.runeshare.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@JsonDeserialize(builder = AssetsDto.AssetsDtoBuilder.class)
@Builder
public class AssetsDto {
    String gameAbsolutePath;
    String fullAbsolutePath;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AssetsDtoBuilder {
    }
}
