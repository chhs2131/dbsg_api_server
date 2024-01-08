package kr.co.dbsg.api.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

public class NaverNewsExample {
    private RestClient restClient;

    @BeforeEach
    public void before() {
        restClient = RestClient.builder()
                .baseUrl("https://openapi.naver.com/v1/search/news.json\t")
                .build();
    }

    @Test
    public void example() {
        restClient;
    }
}
