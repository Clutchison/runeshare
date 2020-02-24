package com.hutchison.runeshare.controller;

import com.hutchison.runeshare.controller.route.Route;
import com.hutchison.runeshare.service.RuneshareService;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
@Value
public class RuneshareController {

    private final RuneshareService service;

    @Autowired
    public RuneshareController(RuneshareService service) {
        this.service = service;
    }

    @Route(startsWith = "!deck")
    public String parseDeckString(String input) {
        List<String> splitInput = Arrays.asList(input.split(" "));
        if (splitInput.size() != 2) return "Bad input, format example: !deck DECKCODE";
        String s = service.parseDeck(splitInput.get(1));
        return s;
    }

    @Route(matches = ".*\\[.*\\].*")
    public File getCardImage(String input) {
        String cardName = input.substring(input.indexOf('[') + 1, input.indexOf(']'));
        return service.getCardImage(cardName);
    }
}
