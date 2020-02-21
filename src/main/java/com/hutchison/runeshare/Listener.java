package com.hutchison.runeshare;

import com.hutchison.runeshare.persistence.repository.CardRepository;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import no.stelar7.lor.LoRDeckCode;
import no.stelar7.lor.types.LoRDeck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Listener extends ListenerAdapter implements EventListener {

//    private final DBLoader dbLoader;
//
//    @Autowired
//    Listener(DBLoader dbLoader) {
//        dbLoader.load();
//        this.dbLoader = dbLoader;
//    }

    private final CardRepository cardRepository;

    @Autowired
    Listener(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    void listen() throws LoginException {
        JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT);
        jdaBuilder.setToken("Njc0MDAyNzE4NDMxNjQxNjYx.XjjzVA.msOoJWmdqZpm0Jg26dB74cFQi0M");
        jdaBuilder.addEventListeners(new Listener(cardRepository));
        jdaBuilder.build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        if (event.getMessage().getContentRaw().startsWith("!deck")) {
            if (event.getMessage().getContentRaw().startsWith("!deck")) {
//                List<String> args = Arrays.asList(event.getMessage().getContentRaw().split(" "));
//                LoRDeck decode = LoRDeckCode.decode(args.get(1));
//                Set<Card> cards = decode.getDeck().keySet().stream()
//                        .map(lorC -> cardRepository.findByCardCode(lorC.getCardCode()))
//                        .collect(Collectors.toSet());
//
//                String output = decode.getDeck().entrySet().stream()
////                    .sorted(Comparator.comparing(es -> cards.get(es.getKey().getCardCode()).getName()))
//                        .map(es -> es.getValue() + "x " + cardRepository.findByCardCode(es.getKey().getCardCode()).getCardCode())
//                        .collect(Collectors.joining("\n"));
//                event.getChannel().sendMessage(output).queue();
            }
        }
    }
}
