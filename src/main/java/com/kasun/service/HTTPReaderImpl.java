package com.kasun.service;

import com.kasun.dto.HTMLObject;
import com.kasun.exception.CrawlerException;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Log4j2
@Component
public class HTTPReaderImpl implements HTTPReader {

    @Override
    public HTMLObject loadHTTP(String stringURL) {

        try {
            Document doc = Jsoup.connect(stringURL).get();
            log.debug("Body {}", doc.body().text());
            return new HTMLObject(stringURL, doc.title(), doc.body().text());
        } catch (IOException e) {
            throw new CrawlerException("Failed to load the HTML", e);
        }

    }

}
