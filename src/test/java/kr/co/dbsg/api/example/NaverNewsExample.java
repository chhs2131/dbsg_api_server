package kr.co.dbsg.api.example;

import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class NaverNewsExample {
    @Test
    void example() {
        String clientId = "-------------";
        String clientSecret = "------------";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://openapi.naver.com/v1/search/news.json")
                .queryParam("query", "IPO")
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "sim");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = new RestTemplate().exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();
            System.out.println(responseBody);
        } else {
            System.out.println("Request failed with status code: " + response.getStatusCode());
        }
    }
}
