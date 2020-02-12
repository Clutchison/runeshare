package com.hutchison.runeshare.cards;

import lombok.Value;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Value
public class Cards {
    List<Card> cards;
    Map<String, Card> map;

    public Cards(List<Card> cards) {
        this.cards = cards;
        this.map = cards.stream()
                .collect(Collectors.toMap(
                        Card::getCode,
                        card -> card
                ));
    }

    public Card get(String code) {
        return map.get(code);
    }
}
