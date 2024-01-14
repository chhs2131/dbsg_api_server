package kr.co.dbsg.api.example;

import kr.co.dbsg.api.global.config.properties.NaverApiProperty;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest
public class NaverNewsExample {
    @Autowired
    private NaverApiProperty naverApiProperty;
    String clientId;
    String clientSecret;

    @Test
    @DisplayName("네이버뉴스 가져오기")
    void example() {
        clientId = naverApiProperty.clientId();
        clientSecret = naverApiProperty.clientSecret();

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
