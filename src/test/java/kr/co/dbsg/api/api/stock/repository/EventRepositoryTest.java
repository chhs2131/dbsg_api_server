//package kr.co.dbsg.api.api.stock.repository;
//
//import static kr.co.dbsg.api.api.event.entity.QEventEntity.eventEntity;
//import static org.assertj.core.api.Assertions.*;
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//import kr.co.dbsg.api.api.event.repository.EventRepository;
//import kr.co.dbsg.api.api.event.repository.EventRepositoryImpl;
//import kr.co.dbsg.api.api.event.repository.EventScheduleRepository;
//import kr.co.dbsg.api.api.event.entity.EventEntity;
//import kr.co.dbsg.api.api.event.entity.EventScheduleEntity;
//import kr.co.dbsg.api.api.stock.entity.StockEntity;
//import kr.co.dbsg.api.global.config.QuerydslConfiguration;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//@DataJpaTest
//@Import(QuerydslConfiguration.class)
//class EventRepositoryTest {
//    private final JPAQueryFactory jpaQueryFactory;
//    private final EventRepository eventRepository;
//    private final StockRepository stockRepository;
//    private final EventScheduleRepository eventScheduleRepository;
//    private final EventRepositoryImpl eventRepositoryImpl;
//
//    @Autowired
//    public EventRepositoryTest(JPAQueryFactory jpaQueryFactory, EventRepository eventRepository, StockRepository stockRepository, EventScheduleRepository eventScheduleRepository, EventRepositoryImpl eventRepositoryImpl) {
//        this.jpaQueryFactory = jpaQueryFactory;
//        this.eventRepository = eventRepository;
//        this.stockRepository = stockRepository;
//        this.eventScheduleRepository = eventScheduleRepository;
//        this.eventRepositoryImpl = eventRepositoryImpl;
//    }
//
//    @Test
//    void 디에스엘() {
//        createData("00000001", "테스트종목A", "20240101", "공모주", "test:알수없음", 4L);
//        createData("00000002", "테스트종목B", "20240102", "공모주", "test:진행중", 3L);
//        createData("00000003", "테스트종목C", "20240103", "공모주", "test:마감", 2L);
//        createData("00000004", "테스트종목D", "20240104", "공모주", "test:준비중", 1L);
//        createData("00000005", "테스트종목E", "20240105", "공모주", "test:알수없음", 0L);
//        createData("00000006", "테스트종목F", "20240106", "공모주", "test:진행중", 6L);
//        createData("00000007", "테스트종목G", "20240107", "공모주", "test:마감", 5L);
//        createData("00000008", "테스트종목H", "20240108", "공모주", "test:준비중", 4L);
//        createData("00000009", "테스트종목I", "20240109", "공모주", "test:알수없음", 3L);
//        createData("00000010", "테스트종목J", "20240110", "공모주", "test:진행중", 2L);
//
//        LocalDate startDate = LocalDate.now().minusDays(7L);
//        LocalDate endDate = LocalDate.now().plusDays(1L);
//
//        List<EventEntity> fetch = jpaQueryFactory.selectFrom(eventEntity)
//            .where(eventEntity.eventSchedule.debutDate.between(startDate, endDate)
//                .or(eventEntity.eventSchedule.refundDate.between(startDate, endDate))
//                .or(eventEntity.eventSchedule.forecastStartDate.between(startDate, endDate))
//                .or(eventEntity.eventSchedule.forecastEndDate.between(startDate, endDate))
//                .or(eventEntity.eventSchedule.subscriptionStartDate.between(startDate, endDate))
//                .or(eventEntity.eventSchedule.subscriptionEndDate.between(startDate, endDate))
//            )
//            .orderBy(eventEntity.createdAt.desc().nullsLast())
//            .offset(1)
//            .limit(3)
//            .fetch();
//
//        Long count = jpaQueryFactory
//            .select(eventEntity.count())
//            .from(eventEntity)
//            .where(eventEntity.eventSchedule.debutDate.between(startDate, endDate)
//                .or(eventEntity.eventSchedule.refundDate.between(startDate, endDate))
//                .or(eventEntity.eventSchedule.forecastStartDate.between(startDate, endDate))
//                .or(eventEntity.eventSchedule.forecastEndDate.between(startDate, endDate))
//                .or(eventEntity.eventSchedule.subscriptionStartDate.between(startDate, endDate))
//                .or(eventEntity.eventSchedule.subscriptionEndDate.between(startDate, endDate))
//            )
//            .fetchOne();
//
//        System.out.println("==================================================================");
//        System.out.println(count);
//        System.out.println(fetch);
//        System.out.println("==================================================================");
//    }
//
//    @Test
//    public void testFindAllByDateRange() {
//        createData("00000001", "테스트종목A", "20240101", "공모주", "test:알수없음", 4L);
//        createData("00000002", "테스트종목B", "20240102", "공모주", "test:진행중", 3L);
//        createData("00000003", "테스트종목C", "20240103", "공모주", "test:마감", 2L);
//        createData("00000004", "테스트종목D", "20240104", "공모주", "test:준비중", 1L);
//        createData("00000005", "테스트종목E", "20240105", "공모주", "test:알수없음", 0L);
//        createData("00000006", "테스트종목F", "20240106", "공모주", "test:진행중", 6L);
//        createData("00000007", "테스트종목G", "20240107", "공모주", "test:마감", 5L);
//        createData("00000008", "테스트종목H", "20240108", "공모주", "test:준비중", 4L);
//        createData("00000009", "테스트종목I", "20240109", "공모주", "test:알수없음", 3L);
//        createData("00000010", "테스트종목J", "20240110", "공모주", "test:진행중", 2L);
//
//        // 테스트에 필요한 데이터와 기대 결과값을 설정합니다.
//        LocalDate startDate = LocalDate.now().minusDays(3L);
//        LocalDate endDate = LocalDate.now();
//        Pageable pageable = PageRequest.of(0, 5);
//
//        // 테스트할 메소드를 호출합니다.
//        Page<EventEntity> result = eventRepositoryImpl.findAllByDateRange(startDate, endDate, pageable);
//
//        // 기대한 결과값과 실제 결과값을 비교합니다.
//        List<EventEntity> fetch = result.getContent();
//        Long count = result.getTotalElements();
//        // 여기서 fetch와 count를 적절하게 검증하는 코드를 작성해주세요.
//
//        // 예시: fetch의 크기가 10이고, count가 100인지 검증하는 코드
//        assertThat(5).isEqualTo(fetch.size());
//        assertThat(6L).isEqualTo(count);
//    }
//
//    public Page<EventEntity> findAllByDateRange(Pageable pageable, LocalDate startDate, LocalDate endDate) {
//        List<EventEntity> fetch = jpaQueryFactory.selectFrom(eventEntity)
//            .where(eventEntity.eventSchedule.debutDate.between(startDate, endDate)
//                .or(eventEntity.eventSchedule.refundDate.between(startDate, endDate))
//                .or(eventEntity.eventSchedule.forecastStartDate.between(startDate, endDate))
//                .or(eventEntity.eventSchedule.forecastEndDate.between(startDate, endDate))
//                .or(eventEntity.eventSchedule.subscriptionStartDate.between(startDate, endDate))
//                .or(eventEntity.eventSchedule.subscriptionEndDate.between(startDate, endDate))
//            )
//            .orderBy(eventEntity.createdAt.desc().nullsLast())
//            .offset(pageable.getOffset())
//            .limit(pageable.getPageSize())
//            .fetch();
//
//        Long count = jpaQueryFactory
//            .select(eventEntity.count())
//            .from(eventEntity)
//            .where(eventEntity.eventSchedule.debutDate.between(startDate, endDate)
//                .or(eventEntity.eventSchedule.refundDate.between(startDate, endDate))
//                .or(eventEntity.eventSchedule.forecastStartDate.between(startDate, endDate))
//                .or(eventEntity.eventSchedule.forecastEndDate.between(startDate, endDate))
//                .or(eventEntity.eventSchedule.subscriptionStartDate.between(startDate, endDate))
//                .or(eventEntity.eventSchedule.subscriptionEndDate.between(startDate, endDate))
//            )
//            .fetchOne();
//
//        return new PageImpl<>(fetch, pageable, count);
//    }
//
//    @Test
//    @DisplayName("이벤트10개_findAll_통과")
//    void findAll() {
//        createData("00000001", "테스트종목A", "20240101", "공모주", "test:알수없음", 4L);
//        createData("00000002", "테스트종목B", "20240102", "공모주", "test:진행중", 3L);
//        createData("00000003", "테스트종목C", "20240103", "공모주", "test:마감", 2L);
//        createData("00000004", "테스트종목D", "20240104", "공모주", "test:준비중", 1L);
//        createData("00000005", "테스트종목E", "20240105", "공모주", "test:알수없음", 0L);
//        createData("00000006", "테스트종목F", "20240106", "공모주", "test:진행중", 6L);
//        createData("00000007", "테스트종목G", "20240107", "공모주", "test:마감", 5L);
//        createData("00000008", "테스트종목H", "20240108", "공모주", "test:준비중", 4L);
//        createData("00000009", "테스트종목I", "20240109", "공모주", "test:알수없음", 3L);
//        createData("00000010", "테스트종목J", "20240110", "공모주", "test:진행중", 2L);
//
//        List<EventEntity> events = eventRepository.findAll();
//
//        assertThat(events).hasSize(10);
//    }
//
//    @Test
//    @DisplayName("상장일순으로정렬")
//    void sort_by_debut() {
//        createData("00000001", "테스트종목A", "20240101", "공모주", "test:알수없음", 4L);
//        createData("00000002", "테스트종목B", "20240102", "공모주", "test:진행중", 3L);
//        createData("00000003", "테스트종목C", "20240103", "공모주", "test:마감", 2L);
//        createData("00000004", "테스트종목D", "20240104", "공모주", "test:준비중", 1L);
//        createData("00000005", "테스트종목E", "20240105", "공모주", "test:알수없음", 0L);
//        createData("00000006", "테스트종목F", "20240106", "공모주", "test:진행중", 6L);
//        createData("00000007", "테스트종목G", "20240107", "공모주", "test:마감", 5L);
//        createData("00000008", "테스트종목H", "20240108", "공모주", "test:준비중", 4L);
//        createData("00000009", "테스트종목I", "20240109", "공모주", "test:알수없음", 3L);
//        createData("00000010", "테스트종목J", "20240110", "공모주", "test:진행중", 2L);
//        List<EventEntity> events = eventRepository.findAll();
//
//        events.sort((o1, o2) -> {
//            LocalDate o1Debut = o1.getEventSchedule().getDebutDate();
//            LocalDate o2Debut = o2.getEventSchedule().getDebutDate();
//
//            if (o1Debut == null) {
//                return -1;
//            }
//            if (o2Debut == null) {
//                return 1;
//            }
//            return -o1Debut.compareTo(o2Debut);
//        });
//
////        events.sort((o1, o2) -> {
////            LocalDate o1Debut = o1.getEventSchedule().getLastSchedule();
////            LocalDate o2Debut = o2.getEventSchedule().getLastSchedule();
////
////            if (o1Debut == null) {
////                return -1;
////            }
////            if (o2Debut == null) {
////                return 1;
////            }
////            return -o1Debut.compareTo(o2Debut);
////        });
//
//        List<String> sortedEvents = events.stream().map(event -> event.getStockId().getCorpName()).collect(Collectors.toList());
//        List<String> expected = List.of("테스트종목E", "테스트종목D", "테스트종목C", "테스트종목J", "테스트종목B", "테스트종목I", "테스트종목A", "테스트종목H", "테스트종목G", "테스트종목F");
//        assertThat(sortedEvents).isEqualTo(expected);
//    }
//
//    @Test
//    @DisplayName("오늘의일정만가져오기")
//    void filter_todaySchedule() {
//        createData("00000001", "테스트종목A", "20240101", "공모주", "test:알수없음", -4L);
//        createData("00000002", "테스트종목B", "20240102", "공모주", "test:진행중", -11L);
//        createData("00000003", "테스트종목C", "20240103", "공모주", "test:마감", -10L);
//        createData("00000004", "테스트종목D", "20240104", "공모주", "test:준비중", 1L);
//        createData("00000005", "테스트종목E", "20240105", "공모주", "test:알수없음", 0L);
//        List<EventEntity> events = eventRepository.findAll();
//
//        events = events.stream()
//            .filter(event -> event.isScheduleInRange(LocalDate.now(), LocalDate.now()))
//            .collect(Collectors.toList());
//
//        List<String> that = events.stream().map(event -> event.getStockId().getCorpName()).toList();
//        List<String> expected = List.of("테스트종목A", "테스트종목B", "테스트종목C", "테스트종목E");
//        assertThat(that).isEqualTo(expected);
//    }
//
//    @Test
//    @DisplayName("최근3일의일정만가져오기")
//    void filter_recent3days_Schedule() {
//        createData("00000001", "테스트종목A", "20240101", "공모주", "test:알수없음", -4L);
//        createData("00000002", "테스트종목B", "20240102", "공모주", "test:진행중", -11L);
//        createData("00000003", "테스트종목C", "20240103", "공모주", "test:마감", -10L);
//        createData("00000004", "테스트종목D", "20240104", "공모주", "test:준비중", 1L);
//        createData("00000005", "테스트종목E", "20240105", "공모주", "test:알수없음", 0L);
//        List<EventEntity> events = eventRepository.findAll();
//
//        events = events.stream()
//            .filter(event -> event.isScheduleInRange(LocalDate.now().minusDays(2L), LocalDate.now()))
//            .collect(Collectors.toList());
//
//        List<String> that = events.stream().map(event -> event.getStockId().getCorpName()).toList();
//        List<String> expected = List.of("테스트종목A", "테스트종목B", "테스트종목C", "테스트종목D", "테스트종목E");
//        assertThat(that).isEqualTo(expected);
//    }
//
//    @Test
//    @DisplayName("3일간상장한녀석만가져오기")
//    void test5() {
//    }
//
////            .forecastStartDate(LocalDate.now().minusDays(day + 11L))
////            .forecastEndDate(LocalDate.now().minusDays(day + 10L))
////            .subscriptionStartDate(LocalDate.now().minusDays(day + 8L))
////            .subscriptionEndDate(LocalDate.now().minusDays(day + 7L))
////            .refundDate(LocalDate.now().minusDays(day + 4L))
////            .debutDate(LocalDate.now().minusDays(day))
//
//    @Test
//    @DisplayName("어떤날짜든최신순으로정렬")
//    void test3() {
//    }
//
//    @Test
//    @DisplayName("공모청약을4일간하는경우")
//    void test4() {
//    }
//
//    // TODO 공모청약일, 환불일, 상장일이 Null인 데이터도 반드시 테스트해야함!!
//
//    private void createData(String dartCode, String corpName, String modifyDate, String eventType, String status, Long day) {
//        StockEntity stock = StockEntity.builder()
//            .dartCode(dartCode)
//            .corpName(corpName)
//            .modifyDate(modifyDate)
//            .build();
//        stockRepository.save(stock);
//
//        EventEntity event = EventEntity.builder()
//            .stockId(stock)
//            .eventType(eventType)
//            .status(status)
//            .createdAt(LocalDateTime.now())
//            .updatedAt(LocalDateTime.now())
//            .build();
//        eventRepository.save(event);
//
//        EventScheduleEntity schedule = EventScheduleEntity.builder()
//            .event(event)
//            .forecastStartDate(LocalDate.now().minusDays(day + 11L))
//            .forecastEndDate(LocalDate.now().minusDays(day + 10L))
//            .subscriptionStartDate(LocalDate.now().minusDays(day + 8L))
//            .subscriptionEndDate(LocalDate.now().minusDays(day + 7L))
//            .refundDate(LocalDate.now().minusDays(day + 4L))
//            .debutDate(LocalDate.now().minusDays(day))
//            .build();
//        eventScheduleRepository.save(schedule);
//
//        event.setEventSchedule(schedule);
//        eventRepository.save(event);
//    }
//}
