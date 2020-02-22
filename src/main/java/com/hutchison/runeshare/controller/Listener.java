package com.hutchison.runeshare.controller;

import com.hutchison.runeshare.annotation.Route;
import com.hutchison.runeshare.persistence.repository.CardRepository;
import lombok.Value;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Listener extends ListenerAdapter implements EventListener {

    private final CardRepository cardRepository;

    @Autowired
    Listener(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void listen() throws LoginException {
        JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT);
        jdaBuilder.setToken("Njc0MDAyNzE4NDMxNjQxNjYx.XjjzVA.msOoJWmdqZpm0Jg26dB74cFQi0M");
        jdaBuilder.addEventListeners(new Listener(cardRepository));
        jdaBuilder.build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        Set<Method> methodsAnnotatedWith = new Reflections("com.hutchison.runeshare.routing", new MethodAnnotationsScanner()).getMethodsAnnotatedWith(Route.class);
        Map<Method, Annotation> methodMap = methodsAnnotatedWith.stream()
                .collect(Collectors.toMap(
                        method -> method,
                        method -> Arrays.stream(method.getDeclaredAnnotations())
                                .filter(annotation -> annotation instanceof Route)
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("Failed to find route annotation"))
                ));
        System.out.println("Welp");
    }

    @Component
    @Value
    static class ListenerInit {
        Listener listener;

        ListenerInit(Listener listener) throws LoginException {
            this.listener = listener;
            listener.listen();
        }
    }
}
