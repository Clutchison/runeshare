package com.hutchison.runeshare.controller;

import com.hutchison.runeshare.annotation.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RuneshareController {

    @Autowired
    public RuneshareController() {
    }

    public String callMethod() {
        return "";
    }

    @Route(startsWith = "!deck")
    public String parseDeckString() {
        return "";
    }

    @Route(regex = ".*\\[.*\\].*")
    public String getCardImage() {
        return "";
    }
}
