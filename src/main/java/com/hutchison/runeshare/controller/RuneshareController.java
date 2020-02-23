package com.hutchison.runeshare.controller;

import com.hutchison.runeshare.controller.route.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class RuneshareController {

    @Route(startsWith = "!deck")
    public String parseDeckString(String input) {
        String s = "parseDeckString called!";
        log.debug(s);
        return s;
    }

    @Route(matches = "\\[.*\\]")
    public String getCardImage(String input) {
        String s = "getCardImage called!";
        log.debug(s);
        return s;
    }
}
