package kr.co.dbsg.api.api.event.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import kr.co.dbsg.api.api.event.domain.Event;
import kr.co.dbsg.api.api.event.domain.EventSchedule;
import kr.co.dbsg.api.api.event.domain.Underwriters;
import kr.co.dbsg.api.api.event.domain.type.EventPrice;
import kr.co.dbsg.api.api.stock.entity.StockEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "event")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private StockEntity stockId;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @OneToOne(mappedBy = "event")
    @Nullable
    private EventScheduleEntity eventSchedule;

    @OneToOne(mappedBy = "event")
    @Nullable
    private EventInformationEntity eventInformation;

    @OneToOne(mappedBy = "event")
    @Nullable
    private EventSizeEntity eventSize;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Event toEvent() {
        EventSchedule schedule = eventSchedule != null ? eventSchedule.toEventSchedule() : null;
        EventPrice price = eventInformation != null ? eventInformation.toEventPrice() : null;
        Underwriters underwriters = eventSize != null ? eventSize.toUnderwriters() : null;

        return new Event(
                id,
                eventType,
                stockId.toCorporationOverview(),
                schedule,
                price,
                underwriters,
                null,
                createdAt,
                updatedAt
        );
    }
}