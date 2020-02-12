package com.hutchison.runeshare;

import com.hutchison.runeshare.util.json.DBLoader;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;
import java.util.Arrays;
import java.util.List;

@Component
public class Listener extends ListenerAdapter implements EventListener {

//    private final Cards cards;
    private final DBLoader dbLoader;

    @Autowired
    Listener(DBLoader dbLoader) {
        dbLoader.load();
        this.dbLoader = dbLoader;
    }

    void listen() throws LoginException {
        JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT);
        jdaBuilder.setToken("Njc0MDAyNzE4NDMxNjQxNjYx.XjjzVA.msOoJWmdqZpm0Jg26dB74cFQi0M");
        jdaBuilder.addEventListeners(new Listener(dbLoader));
        jdaBuilder.build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        if (event.getMessage().getContentRaw().startsWith("!deck")) {
            List<String> args = Arrays.asList(event.getMessage().getContentRaw().split(" "));
//            LoRDeck decode = LoRDeckCode.decode(args.get(1));
//            String output = decode.getDeck().entrySet().stream()
//                    .sorted(Comparator.comparing(es -> cards.get(es.getKey().getCardCode()).getName()))
//                    .map(es -> es.getValue() + "x " + cards.get(es.getKey().getCardCode()).getName())
//                    .collect(Collectors.joining("\n"));
            event.getChannel().sendMessage("test").queue();
        }
    }
}
