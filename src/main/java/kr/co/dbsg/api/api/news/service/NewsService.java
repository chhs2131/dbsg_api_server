package kr.co.dbsg.api.api.news.service;

import java.util.List;
import kr.co.dbsg.api.api.news.dto.NewsResponse;
import kr.co.dbsg.api.api.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;

    public List<NewsResponse> getNews(String title) {
        return newsRepository.findAllByTitle(title)
            .stream()
            .map(NewsResponse::from)
            .toList();
    }
}
