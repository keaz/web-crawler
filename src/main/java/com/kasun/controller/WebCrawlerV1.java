package com.kasun.controller;

import com.kasun.dto.Request;
import com.kasun.dto.Response;
import com.kasun.service.CrawlerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/crawl")
public record WebCrawlerV1(CrawlerService crawlerService) {


    @PostMapping
    public ResponseEntity<Response> crawl(@RequestBody @Valid Request request) {
        final var response = crawlerService.process(request);
        return ResponseEntity.ok(response);
    }

}
