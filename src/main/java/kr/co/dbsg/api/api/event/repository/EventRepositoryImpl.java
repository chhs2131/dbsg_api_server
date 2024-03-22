package kr.co.dbsg.api.api.event.repository;

import static kr.co.dbsg.api.api.event.entity.QEventEntity.eventEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import kr.co.dbsg.api.api.event.entity.EventEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl {
    private final JPAQueryFactory jpaQueryFactory;

    public Page<EventEntity> findAllByDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        List<EventEntity> fetch = jpaQueryFactory.selectFrom(eventEntity)
            .where(eventEntity.eventSchedule.debutDate.between(startDate, endDate)
                .or(eventEntity.eventSchedule.refundDate.between(startDate, endDate))
                .or(eventEntity.eventSchedule.forecastStartDate.between(startDate, endDate))
                .or(eventEntity.eventSchedule.forecastEndDate.between(startDate, endDate))
                .or(eventEntity.eventSchedule.subscriptionStartDate.between(startDate, endDate))
                .or(eventEntity.eventSchedule.subscriptionEndDate.between(startDate, endDate))
            )
            .orderBy(eventEntity.createdAt.desc().nullsLast())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Optional<Long> count = Optional.ofNullable(jpaQueryFactory
            .select(eventEntity.count())
            .from(eventEntity)
            .where(eventEntity.eventSchedule.debutDate.between(startDate, endDate)
                .or(eventEntity.eventSchedule.refundDate.between(startDate, endDate))
                .or(eventEntity.eventSchedule.forecastStartDate.between(startDate, endDate))
                .or(eventEntity.eventSchedule.forecastEndDate.between(startDate, endDate))
                .or(eventEntity.eventSchedule.subscriptionStartDate.between(startDate, endDate))
                .or(eventEntity.eventSchedule.subscriptionEndDate.between(startDate, endDate))
            )
            .fetchOne());

        return new PageImpl<>(fetch, pageable, count.orElse(0L));
    }
}
