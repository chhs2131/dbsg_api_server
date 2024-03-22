package kr.co.dbsg.api.api.event.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kr.co.dbsg.api.api.event.domain.type.EventSchedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "event_schedule")
@ToString(exclude = "event")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
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

    public LocalDate getLastSchedule() {
        List<LocalDate> schedules = new ArrayList<>();
        schedules.add(forecastStartDate);
        schedules.add(forecastEndDate);
        schedules.add(subscriptionStartDate);
        schedules.add(subscriptionEndDate);
        schedules.add(refundDate);
        schedules.add(debutDate);

        return schedules.stream()
            .filter(Objects::isNull)
            .reduce(forecastStartDate, (d1, d2) -> d1.isAfter(d2) ? d1 : d2);
    }
}