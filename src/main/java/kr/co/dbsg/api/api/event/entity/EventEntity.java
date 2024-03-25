package kr.co.dbsg.api.api.event.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import kr.co.dbsg.api.api.event.domain.Event;
import kr.co.dbsg.api.api.stock.entity.StockEntity;
import lombok.AccessLevel;
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
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private StockEntity stockId;

    @Column(name = "event_type", nullable = false)
    private String eventType;

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
                eventSchedule.toEventSchedule(),
                eventInformation.toEventPrice(),
                eventSize.toUnderwriters(),
                null,
                createdAt,
                updatedAt
        );
    }
}