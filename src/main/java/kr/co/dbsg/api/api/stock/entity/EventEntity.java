package kr.co.dbsg.api.api.stock.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import kr.co.dbsg.api.api.stock.domain.Event;
import kr.co.dbsg.api.api.stock.domain.type.EventStatus;
import lombok.ToString;

@Entity
@Table(name = "event")
@ToString
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
}