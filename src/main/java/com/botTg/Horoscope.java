package com.botTg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Horoscope {

    String parseURL(String url) {
        String atr = "p";
        String text;
        Document doc = null;
        try {
            //Получаем документ нужной нам страницы
            doc = Jsoup.connect(url).maxBodySize(0).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Elements elQuote = doc.select(atr);
        text = elQuote.get(1).text() + "\n\n" + elQuote.get(2).text() + "\n\n" + elQuote.get(3).text();
        return text;
    }
}
