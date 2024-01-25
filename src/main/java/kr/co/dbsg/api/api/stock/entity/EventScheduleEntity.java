package kr.co.dbsg.api.api.stock.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import kr.co.dbsg.api.api.stock.domain.type.EventSchedule;
import lombok.ToString;

@Entity
@Table(name = "event_schedule")
@ToString(exclude = "event")
public class EventScheduleEntity {
    @Id
    @Column(name = "event_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne
    private EventEntity event;

    @Column(name = "forecast_start_date")
    private LocalDate forecastStartDate;

    @Column(name = "forecast_end_date")
    private LocalDate forecastEndDate;

    @Column(name = "subscription_start_date")
    private LocalDate subscriptionStartDate;

    @Column(name = "subscription_end_date")
    private LocalDate subscriptionEndDate;

    @Column(name = "refund_date")
    private LocalDate refundDate;

    @Column(name = "debut_date")
    private LocalDate debutDate;

    public EventSchedule toEventSchedule() {
        return new EventSchedule(
                forecastStartDate,
                forecastEndDate,
                subscriptionStartDate,
                subscriptionEndDate,
                refundDate,
                debutDate,
                null
        );
    }
}