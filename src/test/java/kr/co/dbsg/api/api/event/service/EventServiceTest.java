package kr.co.dbsg.api.api.event.service;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import kr.co.dbsg.api.api.event.domain.Event;
import kr.co.dbsg.api.api.event.entity.EventEntity;
import kr.co.dbsg.api.api.event.entity.EventInformationEntity;
import kr.co.dbsg.api.api.event.entity.EventScheduleEntity;
import kr.co.dbsg.api.api.event.entity.EventSizeEntity;
import kr.co.dbsg.api.api.event.repository.EventRepository;
import kr.co.dbsg.api.api.event.repository.EventRepositoryImpl;
import kr.co.dbsg.api.api.stock.entity.StockEntity;
import kr.co.dbsg.api.api.stock.repository.StockRepository;
import kr.co.dbsg.api.global.config.QuerydslConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("local")
@DataJpaTest
@Import(QuerydslConfiguration.class)
class EventServiceTest {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventRepositoryImpl eventRepositoryImpl;
    @Autowired
    private StockRepository stockRepository;

    private EventService eventService;
    @BeforeEach
    public void setup() {
        eventService = new EventService(eventRepository, eventRepositoryImpl);
    }

    @Test
    @DisplayName("Event 정상적으로 등록/조회가 되는가")
    //TODO 이 엉망진창 테스트를 구제해라 (Service나 Domain 기준으로 테스트할 것)
    void add_new_event() {
        // given
        final String eventType = "실권주";
        final String corpName = "삼성전자";
        final StockEntity stock = stockRepository.findByCorpName(corpName).orElseThrow();

        // when
        final EventEntity event = EventEntity.builder()
            .stockId(stock)
            .eventType(eventType)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        eventRepository.save(event);

        long day = 1L;
        EventScheduleEntity schedule = EventScheduleEntity.builder()
            .event(event)
            .forecastStartDate(LocalDate.now().minusDays(day + 11L))
            .forecastEndDate(LocalDate.now().minusDays(day + 10L))
            .subscriptionStartDate(LocalDate.now().minusDays(day + 8L))
            .subscriptionEndDate(LocalDate.now().minusDays(day + 7L))
            .refundDate(LocalDate.now().minusDays(day + 4L))
            .debutDate(LocalDate.now().minusDays(day))
            .build();

        EventInformationEntity eventInformation = EventInformationEntity.builder()
            .id(1L)
            .event(event)
            .issuanceReason("reason")
            .parValue(100)
            .hopePriceHigh(200)
            .hopePriceLow(100)
            .fixedPrice(150)
            .chanceRateOfInstitutional(50)
            .chanceRateOfEven(30)
            .chanceRateOfProportional(20)
            .depositPercent(0.1)
            .minimumSubscription(10)
            .build();

        Long id = 1L;
        Integer totalSubscriptionStock = 100;
        Integer totalSubscriptionMoney = 1000;
        Integer subscriptionNewStock = 50;
        Integer subscriptionOldStock = 50;
        EventSizeEntity eventSizeEntity = new EventSizeEntity(id, event, new ArrayList<>(), totalSubscriptionStock, totalSubscriptionMoney, subscriptionNewStock, subscriptionOldStock);

        event.setEventInformation(eventInformation);
        event.setEventSchedule(schedule);
        event.setEventSize(eventSizeEntity);
        final EventEntity save = eventRepository.save(event);

        // then
        final Event response = eventService.getEvent(save.getId());
        assertThat(response.getCorporationOverview().getName()).isEqualTo(corpName);
        assertThat(response.getType()).isEqualTo(eventType);
    }

    // TODO - EventSchedule, EventPrice 등 연관 객체들은 반드시 존재해야하는지 관계를 명확히!!!! is it nullable?

}