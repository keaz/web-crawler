package com.kasun.service;

import com.kasun.dto.Request;
import com.kasun.dto.Response;

public interface CrawlerService {

    Response process(Request request);

}
