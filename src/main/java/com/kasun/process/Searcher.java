package com.kasun.process;

import com.kasun.dto.HTMLObject;

import java.util.List;
import java.util.Optional;

public interface Searcher {


    Optional<String> search(HTMLObject html, String searchText);


}
