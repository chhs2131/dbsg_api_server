package kr.co.dbsg.api.api.news.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.dbsg.api.api.news.entity.NewsEntity;
import kr.co.dbsg.api.global.config.properties.NaverApiProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Primary
@Repository
@RequiredArgsConstructor
public class NaverNewsRepository implements NewsRepository {
    private final NaverApiProperty naverApiProperty;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<NewsEntity> findAllByTitle(String title) {
        String baseUrl = naverApiProperty.baseUrl();
        String clientId = naverApiProperty.clientId();
        String clientSecret = naverApiProperty.clientSecret();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/v1/search/news.json")
                .queryParam("query", title)
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

            try {
                List<NaverArticle> newsEntities = objectMapper.readValue(responseBody, NaverNews.class).items;

                return newsEntities.stream()
                        .map(naverNews -> NewsEntity.builder()
                                .id(0L)
                                .title(removeHtmlEntity(naverNews.title))
                                .url(naverNews.originallink)
                                .desc(naverNews.description)
                                .publishedAt(toLocalDateTime(naverNews.pubDate))
                                .build())
                        .toList();
            } catch(JsonProcessingException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Json 파싱 에러");  //TODO exception 정의
            }
        } else {
            throw new IllegalArgumentException("Request failed with status code: " + response.getStatusCode());  //TODO exception 정의
        }
    }

    private LocalDateTime toLocalDateTime(String naverTimeFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        return LocalDateTime.parse(naverTimeFormat, formatter);
    }

    private String removeHtmlEntity(String str) {
        // TODO HttpEntity remove utility 추가 필요 및 해당 로직을 도메인 로직으로 삼을 것
        return str.replace("<b>", "")
            .replace("</b>", "")
            .replace("&amp;", "&")
            .replace("&quot;", "\"");
    }

    @Data
    @NoArgsConstructor
    static class NaverNews {
        private String lastBuildDate;
        private int total;
        private int start;
        private int display;
        private List<NaverArticle> items;
    }

    @Data
    @NoArgsConstructor
    static class NaverArticle {
        private String title;
        private String originallink;
        private String link;
        private String description;
        private String pubDate;
    }
}
