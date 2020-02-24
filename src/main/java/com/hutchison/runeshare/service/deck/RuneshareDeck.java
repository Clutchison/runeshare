package com.hutchison.runeshare.service.deck;

import com.hutchison.runeshare.model.entity.card.Card;
import com.hutchison.runeshare.repository.CardRepository;
import lombok.Value;
import no.stelar7.lor.types.LoRDeck;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Value
public class RuneshareDeck {

    private Map<Card, Integer> cards;

    public String toFormattedString(ParseFormat format) {
        switch (format) {
            case ALPHA:
                return getAlphaString();
            case CMC:
                return getCmcString();
            default:
                throw new RuntimeException("Unsupported format: " + format);
        }
    }

    private String getCmcString() {
        Map<Integer, List<Map.Entry<Card, Integer>>> manaCostCardMap = cards.entrySet().stream()
                .collect(Collectors.groupingBy(es -> es.getKey().getCost()));
        return manaCostCardMap.entrySet().stream()
                .map(cmcList -> generateCmcHeader(cmcList) + getAlphaString(cmcList.getValue()))
                .collect(Collectors.joining("\n"));
    }

    @NotNull
    private String generateCmcHeader(Map.Entry<Integer, List<Map.Entry<Card, Integer>>> cmcList) {
        return "**__     " + cmcList.getKey() + "     __**\n";
    }

    private String getAlphaString(List<Map.Entry<Card, Integer>> value) {
        return getAlphaString(value.stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                )));
    }

    private String getAlphaString(Map<Card, Integer> map) {
        return map.entrySet().stream()
                .sorted(Comparator.comparing(es -> es.getKey().getName()))
                .map(es -> es.getValue() + "x " + es.getKey().getName())
                .collect(Collectors.joining("\n"));
    }

    private String getAlphaString() {
        return getAlphaString(cards);
    }

    @Component
    public static class RuneshareDeckFactory {

        private final CardRepository cardRepository;

        @Autowired
        RuneshareDeckFactory(CardRepository cardRepository) {
            this.cardRepository = cardRepository;
        }


        public RuneshareDeck fromLoRDeck(LoRDeck loRDeck) {
            return new RuneshareDeck(
                    loRDeck.getDeck().entrySet().stream()
                            .collect(Collectors.toMap(
                                    es -> getCard(es.getKey().getCardCode()),
                                    Map.Entry::getValue
                            ))
            );
        }

        @Transactional
        private Card getCard(String code) {
            return cardRepository.findByCardCode(code);
        }
    }
}
