package com.kasun.service;

import com.kasun.dto.Request;
import com.kasun.dto.Response;
import com.kasun.exception.CrawlerException;
import com.kasun.process.Searcher;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.*;

@Log4j2
@Service
public record CrawlerServiceImpl(HTTPReader httpReader, Searcher searcher) implements CrawlerService {


    @Override
    public Response process(Request request) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        final var urls = request.urls();
        log.debug("Start crawling urls {}", urls);

        Response response = new Response(new ArrayList<>(0));
        final var tasks = new ArrayList<Task>();
        for (String url : urls) {
            tasks.add(new Task(httpReader, searcher, url, request.searchText(), response));

        }
        try {
            final var futures = executor.invokeAll(tasks);
            for (Future<Optional<String>> optionalUrl : futures) {
                optionalUrl.get().ifPresent(url -> {
                    response.urls().add(url);
                });
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw new CrawlerException("Failed to Process URL",e);
        }finally {
            executor.shutdown();
        }

        return response;
    }

    record Task(HTTPReader httpReader, Searcher searcher, String url, String searchTest,
                Response response) implements Callable<Optional<String>> {

        @Override
        public Optional<String> call() throws Exception {
            final var search = searcher.search(httpReader.loadHTTP(url), searchTest);
            return search;
        }
    }
}

