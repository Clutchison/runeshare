package com.hutchison.runeshare.controller;

import com.hutchison.runeshare.controller.route.Route;
import org.springframework.stereotype.Controller;

@Controller
public class RuneshareController {

    @Route(startsWith = "!deck")
    public String parseDeckString() {
        return "";
    }

    @Route(matches = "\\[.*\\]")
    public String getCardImage() {
        return "";
    }
}
