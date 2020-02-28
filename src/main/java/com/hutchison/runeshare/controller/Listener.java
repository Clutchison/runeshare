package com.hutchison.runeshare.controller;

import com.hutchison.runeshare.controller.route.Route;
import com.hutchison.runeshare.controller.route.RouteMapping;
import com.hutchison.runeshare.repository.CardRepository;
import com.hutchison.runeshare.util.json.DBLoader;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class Listener extends ListenerAdapter implements EventListener {

    private final CardRepository cardRepository;
    private final List<RouteMapping> routeMappings;
    private final RuneshareController runeshareController;
    private final DBLoader dbLoader;

    @Value("${discord.token}")
    private String token;

    @Autowired
    Listener(CardRepository cardRepository,
             RuneshareController runeshareController,
             DBLoader dbLoader) {
        this.cardRepository = cardRepository;
        this.runeshareController = runeshareController;
        this.dbLoader = dbLoader;
        this.routeMappings = Arrays.stream(runeshareController.getClass().getMethods())
                .filter(method -> method.getAnnotation(Route.class) != null)
                .map(RouteMapping::new)
                .collect(Collectors.toList());
    }

    public void listen() throws LoginException {
        if (!new ClassPathResource("rs-local.db").exists()) {
            dbLoader.load();
        }
        JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT);
        jdaBuilder.setToken(token);
        jdaBuilder.addEventListeners(new Listener(cardRepository, runeshareController, dbLoader));
        jdaBuilder.build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        String contentRaw = event.getMessage().getContentRaw();
        log.debug("Received " + contentRaw);
        routeMappings.stream()
                .filter(mapping -> mapping.check(contentRaw))
                .findFirst()
                .ifPresent(value -> send(event, contentRaw, value));
    }

    private void send(MessageReceivedEvent event, String contentRaw, RouteMapping value) {
        Object returnObj;
        try {
            returnObj = value.getMethod().invoke(runeshareController, contentRaw);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to invoke method: " + value.getMethod());
        }

        if (returnObj instanceof String)
            event.getChannel().sendMessage((String) returnObj).queue();
        else if (returnObj instanceof File)
            event.getChannel().sendFile((File) returnObj).queue();
        else if (returnObj instanceof List) {
            ((List<Object>) returnObj).forEach(obj -> {
                if (obj instanceof String) {
                    event.getChannel().sendMessage((String) obj).queue();
                } else if (obj instanceof File) {
                    event.getChannel().sendFile((File) obj).queue();
                } else {
                    throw new RuntimeException("Returned object from method not string or file: " + returnObj.toString());
                }
            });
        } else
            throw new RuntimeException("Returned object from method not String or File: " + returnObj.toString());
    }

    @Component
    @Data
    static class ListenerInit {
        Listener listener;

        ListenerInit(Listener listener) throws LoginException {
            this.listener = listener;
            listener.listen();
        }
    }
}
