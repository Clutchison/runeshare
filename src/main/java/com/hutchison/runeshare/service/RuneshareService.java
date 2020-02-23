package com.hutchison.runeshare.service;

import com.hutchison.runeshare.service.deck.RuneshareDeck;
import com.hutchison.runeshare.service.deck.RuneshareDeck.RuneshareDeckFactory;
import lombok.Value;
import no.stelar7.lor.LoRDeckCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.hutchison.runeshare.service.deck.ParseFormat.CMC;

@Service
@Value
public class RuneshareService {

    RuneshareDeckFactory deckFactory;

    @Autowired
    public RuneshareService(RuneshareDeckFactory deckFactory) {
        this.deckFactory = deckFactory;
    }

    public String parseDeck(String code) {
        RuneshareDeck deck = deckFactory.fromLoRDeck(LoRDeckCode.decode(code));
        return deck.toFormattedString(CMC);
    }
}
