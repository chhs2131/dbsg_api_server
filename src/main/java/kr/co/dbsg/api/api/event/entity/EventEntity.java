package kr.co.dbsg.api.api.event.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import kr.co.dbsg.api.api.event.domain.Event;
import kr.co.dbsg.api.api.event.domain.type.EventStatus;
import kr.co.dbsg.api.api.stock.entity.StockEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "event")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private StockEntity stockId;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Deprecated(since = "2024-01-26")
    @Column(name = "status", nullable = false)
    private String status;

    @OneToOne(mappedBy = "event")
    private EventScheduleEntity eventSchedule;

    @OneToOne(mappedBy = "event")
    private EventInformationEntity eventInformation;

    @OneToOne(mappedBy = "event")
    private EventSizeEntity eventSize;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Event toEvent() {
        return new Event(
                id.longValue(),
                eventType,
                stockId.toCorporationOverview(),
                EventStatus.ALL,  //TODO
                eventSchedule.toEventSchedule(),
                eventInformation.toEventPrice(),
                eventSize.toUnderwriters(),
                null,
                createdAt,
                updatedAt
        );
    }

    public boolean isScheduleInRange(LocalDate startDate, LocalDate endDate) {
        EventScheduleEntity schedule = eventSchedule;

        //TODO EventSchedule 에게 물어보기
        for (LocalDate now=startDate; now.isBefore(endDate.plusDays(1L)); now = now.plusDays(1L)) {
            if (now.isAfter(schedule.getForecastStartDate().minusDays(1L)) && now.isBefore(
                schedule.getForecastEndDate().plusDays(1L))) {
                return true;
            }
            if (now.isAfter(schedule.getSubscriptionStartDate().minusDays(1L)) && now.isBefore(
                schedule.getSubscriptionEndDate().plusDays(1L))) {
                return true;
            }
            if (now.isEqual(schedule.getRefundDate())) {
                return true;
            }
            if (now.isEqual(schedule.getDebutDate())) {
                return true;
            }
        }
        return false;
    }
}