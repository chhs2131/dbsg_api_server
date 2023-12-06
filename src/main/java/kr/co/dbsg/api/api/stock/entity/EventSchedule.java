package kr.co.dbsg.api.api.stock.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "event_schedule")
public class EventSchedule {
    @Id
    @Column(name = "event_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne
    private Event event;

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
}