package kr.co.dbsg.api.api.news.controller;

import java.util.List;
import kr.co.dbsg.api.api.news.dto.NewsResponse;
import kr.co.dbsg.api.api.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping("")
    public List<NewsResponse> getNews() {
        return newsService.getNews();
    }
}
