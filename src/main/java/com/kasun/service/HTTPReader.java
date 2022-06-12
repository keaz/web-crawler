package com.kasun.service;

import com.kasun.dto.HTMLObject;

public interface HTTPReader {

    HTMLObject loadHTTP(String stringURL);

}
