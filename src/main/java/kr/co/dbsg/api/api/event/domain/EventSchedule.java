package kr.co.dbsg.api.api.event.domain;

import java.time.LocalDate;
import kr.co.dbsg.api.api.event.domain.type.EventStatus;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class EventSchedule {
    private final LocalDate forecastStart;
    private final LocalDate forecastEnd;
    private final LocalDate subscriptionStart;
    private final LocalDate subscriptionEnd;
    private final LocalDate refund;
    private final LocalDate debut;
    private final LocalDate eventCancel;

    public EventSchedule(final LocalDate forecastStart, final LocalDate forecastEnd, final LocalDate subscriptionStart,
                         final LocalDate subscriptionEnd, final LocalDate refund, final LocalDate debut,
                         final LocalDate eventCancel) {
        // TODO 일정에 대해 더 상세한 검증 필요 (모든 일정에 대해 도메인 조건이 검증되지 못함)
        // forecastStart가 forecastEnd보다 앞일 수 없다.
        Assert.isTrue(forecastStart == null || forecastEnd == null || !forecastStart.isAfter(forecastEnd),
            "forecastStart must be before or equal to forecastEnd");

        // subscriptionStart가 subscriptionEnd보다 앞일 수 없다.
        Assert.isTrue(subscriptionStart == null || subscriptionEnd == null || !subscriptionStart.isAfter(subscriptionEnd),
            "subscriptionStart must be before or equal to subscriptionEnd");

        // forecastEnd가 subscriptionStart보다 앞일 수 없다.
        Assert.isTrue(forecastEnd == null || subscriptionStart == null || !forecastEnd.isAfter(subscriptionStart),
            "forecastEnd must be before or equal to subscriptionStart");

        // subscriptionEnd가 refund보다 앞일 수 없다.
        Assert.isTrue(subscriptionEnd == null || refund == null || !subscriptionEnd.isAfter(refund),
            "subscriptionEnd must be before or equal to refund");

        // refund가 debut보다 앞일 수 없다.
        Assert.isTrue(refund == null || debut == null || !refund.isAfter(debut),
            "refund must be before or equal to debut");

        this.forecastStart = forecastStart;
        this.forecastEnd = forecastEnd;
        this.subscriptionStart = subscriptionStart;
        this.subscriptionEnd = subscriptionEnd;
        this.refund = refund;
        this.debut = debut;
        this.eventCancel = eventCancel;
    }

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
