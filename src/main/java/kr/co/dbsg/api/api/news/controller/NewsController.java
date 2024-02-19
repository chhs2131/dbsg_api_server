package kr.co.dbsg.api.api.news.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
            @Valid @NotBlank @Size(min = 1, max = 20) @Parameter(description = "검색할 키워드를 입력합니다. (글자수 제한: 1~20글자)")
            @RequestParam(name = "title", defaultValue = "IPO", required = false) String title
    ) {
        return ResponseEntity.ok(newsService.getNews(title));
    }
}
