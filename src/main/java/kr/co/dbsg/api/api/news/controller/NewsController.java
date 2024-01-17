package kr.co.dbsg.api.api.news.controller;

import java.util.List;
import kr.co.dbsg.api.api.news.dto.NewsResponse;
import kr.co.dbsg.api.api.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping("")
    public ResponseEntity<List<NewsResponse>> getNews(
            @RequestParam(name = "title", defaultValue = "IPO", required = false) String title
    ) {
        return ResponseEntity.ok(newsService.getNews(title));
    }
}
