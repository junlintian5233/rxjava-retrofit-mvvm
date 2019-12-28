package com.junzhitian.basicapp.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlFormatUtil {
    public static String getNewContent(String htmltext) {

        Document doc      = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        if (!elements.isEmpty()) {
            for (Element element : elements) {
                element.attr("width", "100%").attr("height", "auto");
            }
        }
        return doc.toString();
    }
}
