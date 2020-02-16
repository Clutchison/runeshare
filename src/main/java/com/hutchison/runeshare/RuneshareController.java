package com.hutchison.runeshare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.security.auth.login.LoginException;

@Controller
public class RuneshareController {

    private final Listener listener;

    @Autowired
    public RuneshareController(Listener listener) throws LoginException {
        this.listener = listener;
        listener.listen();
    }
}
