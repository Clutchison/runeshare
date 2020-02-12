package com.hutchison.runeshare.parse;

import com.hutchison.runeshare.cards.Card;
import com.hutchison.runeshare.cards.Cards;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class HtmlParser {
    private static String PAGE_PATH = "./src/main/resources/page.html";
    private static String OUT_PATH = "./src/main/resources/db.json";

    public static Cards parse() {
        Document htmlFile = null;
        try {
            htmlFile = Jsoup.parse(new File(PAGE_PATH), "ISO-8859-1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Element> trs = htmlFile.select("td[data-sort-value]").stream()
                .map(Element::parent)
                .distinct()
                .collect(Collectors.toList());
        Cards cards = new Cards(trs.stream()
                .map(tr -> {
                    String code = tr.selectFirst("td[data-sort-value]").attr("data-sort-value");
                    String name = tr.selectFirst("td a[title]").text();
                    return Card.builder()
                            .code(code)
                            .name(name)
                            .build();
                })
                .collect(Collectors.toList()));
//        cards.getCards().forEach(System.out::println);
        return cards;
//        try {
//            new ObjectMapper().writeValue(new File(OUT_PATH), cards);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
