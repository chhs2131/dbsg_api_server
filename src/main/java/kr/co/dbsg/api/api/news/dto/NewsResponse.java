package kr.co.dbsg.api.api.news.dto;

import java.time.LocalDateTime;
import kr.co.dbsg.api.api.news.entity.NewsEntity;

public record NewsResponse(
    long id,
    String title,
    String url,
    LocalDateTime publishedAt
) {
    public static NewsResponse from(NewsEntity newsEntity) {
        return new NewsResponse(
            newsEntity.getId(),
            newsEntity.getTitle(),
            newsEntity.getUrl(),
            newsEntity.getPublishedAt()
        );
    }
}
