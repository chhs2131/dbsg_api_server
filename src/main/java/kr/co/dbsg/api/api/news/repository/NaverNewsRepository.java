package kr.co.dbsg.api.api.news.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import kr.co.dbsg.api.api.news.entity.NewsEntity;
import kr.co.dbsg.api.global.config.properties.NaverApiProperty;
import kr.co.dbsg.api.global.util.HtmlCharEntityConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Primary
@Repository
@RequiredArgsConstructor
public class NaverNewsRepository implements NewsRepository {
    private final NaverApiProperty naverApiProperty;

    @Override
    public List<NewsEntity> findAllByTitle(String title) {
        String requestUri = naverApiProperty.baseUrl() + "/v1/search/news.json";
        String clientId = naverApiProperty.clientId();
        String clientSecret = naverApiProperty.clientSecret();

        String uriWithParam = UriComponentsBuilder.fromHttpUrl(requestUri)
            .queryParam("query", title)
            .queryParam("display", 10)
            .queryParam("start", 1)
            .queryParam("sort", "sim")
            .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        return getArticles(uriWithParam, headers).stream()
            .map(naverNews -> NewsEntity.builder()
                .id(0L)
                .title(removeHtmlEntity(naverNews.title))
                .url(naverNews.originallink)
                .desc(naverNews.description)
                .publishedAt(toLocalDateTime(naverNews.pubDate))
                .build())
            .toList();
    }

    private static List<NaverArticle> getArticles(final String requestUri, final HttpHeaders headers) {
        //TODO NPE 발생가능
        return RestClient.create().get()
            .uri(requestUri)
            .accept(MediaType.APPLICATION_JSON)
            .headers(httpHeaders -> httpHeaders.addAll(headers))
            .retrieve()
            .toEntity(NaverNews.class)
            .getBody()  //TODO 응답이 2xx가 아닌경우 처리
            .items;
    }

    private LocalDateTime toLocalDateTime(String naverTimeFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        return LocalDateTime.parse(naverTimeFormat, formatter);
    }

    private String removeHtmlEntity(String str) {
        // TODO 해당 로직을 도메인 로직으로 삼을 것
        return HtmlCharEntityConverter.unescape(str);
    }

    @Data
    @NoArgsConstructor
    private static class NaverNews {
        private String lastBuildDate;
        private int total;
        private int start;
        private int display;
        private List<NaverArticle> items;
    }

    @Data
    @NoArgsConstructor
    private static class NaverArticle {
        private String title;
        private String originallink;
        private String link;
        private String description;
        private String pubDate;
    }
}
