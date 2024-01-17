package kr.co.dbsg.api.api.news.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import kr.co.dbsg.api.api.news.entity.NewsEntity;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryNewsRepository implements NewsRepository {
    private static final List<NewsEntity> news = new ArrayList<>();

    static {
        news.add(new NewsEntity(1L, "거인TV가 떳다? IPO시장 긴장", "", "https://www.gamemeca.com/view.php?gid=1743705", LocalDateTime.now()));
        news.add(new NewsEntity(2L, "탱크가 추천하고 위치도 놀란 공모주시장 2024에도 멈추지 않는다",  "","https://zdnet.co.kr/view/?no=20131226085201", LocalDateTime.now().minusDays(1L)));
        news.add(new NewsEntity(3L, "2023년 최고의 OS로 '널기' 선정, LG웹오에스 분사 결정",  "","https://www.newsis.com/view/?id=NISX20231228_0002573329&cID=10403&pID=15000", LocalDateTime.now().minusDays(2L)));
        news.add(new NewsEntity(4L, "새해 대어는 어디로? 이제는 중국몽이다!",  "","https://www.mk.co.kr/news/world/10896353", LocalDateTime.now().minusDays(4L)));
        news.add(new NewsEntity(5L, "'LS머터리얼즈' vs '엘에스메테리얼스', 혼란만 가중되는 공모주 표기 방식",  "","https://www.topdaily.kr/articles/95495", LocalDateTime.now().minusMonths(1L)));
    }

    @Override
    public List<NewsEntity> findAllByTitle(String title) {
        return news;
    }
}
