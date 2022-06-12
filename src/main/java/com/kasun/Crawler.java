package com.kasun;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@Log4j2
@SpringBootApplication
public class Crawler {

    public static void main(String[] args) {

        SpringApplication.run(Crawler.class, args);
//        if (args.length == 0) {
//            log.warn("Input is empty. exiting the application...");
//            return;
//        }
//
//        final var inputFileReader = new InputFileReaderImpl();
//        final var urls = inputFileReader.extractURLs(args[0]);
//        log.info("Urls in the input {} are {}", args[0], urls);
//        final var httpReader = new HTTPReaderImpl();
//        urls.forEach(url -> {
//            final var httpContent = httpReader.loadHTTP(url);
//            log.debug("Content of the url {} is {}", url, httpContent);
//        });
    }
}
