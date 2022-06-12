package com.kasun.config;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchConfig {

    @Bean
    public StandardAnalyzer analyzer(){
        return new StandardAnalyzer();
    }

}
