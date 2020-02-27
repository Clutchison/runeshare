package com.hutchison.runeshare.service;

import com.hutchison.runeshare.model.entity.card.Card;
import com.hutchison.runeshare.repository.CardRepository;
import com.hutchison.runeshare.service.deck.RuneshareDeck;
import com.hutchison.runeshare.service.deck.RuneshareDeck.RuneshareDeckFactory;
import lombok.Value;
import no.stelar7.lor.LoRDeckCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

import static com.hutchison.runeshare.service.deck.ParseFormat.CMC;

@Service
@Value
public class RuneshareService {

    RuneshareDeckFactory deckFactory;
    CardRepository cardRepository;

    @Autowired
    public RuneshareService(RuneshareDeckFactory deckFactory,
                            CardRepository cardRepository) {
        this.deckFactory = deckFactory;
        this.cardRepository = cardRepository;
    }

    public String parseDeck(String code) {
        RuneshareDeck deck = deckFactory.fromLoRDeck(LoRDeckCode.decode(code));
        return deck.toFormattedString(CMC);
    }

    public File getCardImage(String cardName) {
        return cardRepository.findByName(cardName).stream()
                .filter(Card::getCollectible)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No cards found that are collectible."))
                .getCardImage();
    }

    public File getArt(String cardName) {
        return cardRepository.findByName(cardName).stream()
                .filter(Card::getCollectible)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No cards found that are collectible"))
                .getCardArt();
    }
}
