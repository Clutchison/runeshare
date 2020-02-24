package com.hutchison.runeshare.util.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hutchison.runeshare.model.dto.CardDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@FieldDefaults(level = PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class SetInput {
    @JsonDeserialize(using = CardDeserializer.class)
    List<CardDto> cards;

    private static class CardDeserializer extends ListDeserializer<CardDeserializer> {
    }
}
