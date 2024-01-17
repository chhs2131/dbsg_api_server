package kr.co.dbsg.api.api.news.repository;

import java.util.List;

import kr.co.dbsg.api.api.news.entity.NewsEntity;

public interface NewsRepository {
    List<NewsEntity> findAllByTitle(String title);
}
