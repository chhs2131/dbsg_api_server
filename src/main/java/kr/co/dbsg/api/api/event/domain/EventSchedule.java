package kr.co.dbsg.api.api.event.domain;

import java.time.LocalDate;
import kr.co.dbsg.api.api.event.domain.type.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventSchedule {
    private final LocalDate forecastStart;
    private final LocalDate forecastEnd;
    private final LocalDate subscriptionStart;
    private final LocalDate subscriptionEnd;
    private final LocalDate refund;
    private final LocalDate debut;
    private final LocalDate eventCancel;

    public EventStatus getStatus() {
        LocalDate now = LocalDate.now();
        if (eventCancel != null) {
            return EventStatus.CANCEL;
        }
        if (debut != null && now.isAfter(debut)) {
            return EventStatus.AFTER_DEBUT;
        }
        if (debut != null && now.isEqual(debut)) {
            return EventStatus.NOW_DEBUT;
        }
        if (refund != null && now.isAfter(refund)) {
            return EventStatus.BEFORE_DEBUT;
        }
        if (refund != null && now.isEqual(refund)) {
            return EventStatus.NOW_REFUND;
        }
        if (subscriptionEnd != null && now.isAfter(subscriptionEnd)) {
            return EventStatus.BEFORE_REFUND;
        }
        if (subscriptionStart != null && now.isAfter(subscriptionStart.minusDays(1))) {
            return EventStatus.NOW_SUBSCRIPTION;
        }
        if (forecastEnd != null && now.isAfter(forecastEnd)) {
            return EventStatus.BEFORE_SUBSCRIPTION;
        }
        if (forecastStart != null && now.isAfter(forecastStart.minusDays(1))) {
            return EventStatus.NOW_FORECAST;
        }
        if (forecastStart != null) {
            return EventStatus.BEFORE_FORECAST;
        }

        return EventStatus.DRAFTING_REPORT;
    }
}
