package kr.co.dbsg.api.api.event.domain.type;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventSchedule {
    private final LocalDate forecastStart;
    private final LocalDate forecastEnd;
    private final LocalDate subscriptionStartDate;
    private final LocalDate subscriptionEndDate;
    private final LocalDate refund;
    private final LocalDate debut;
    private final LocalDate eventCancelDate;
}
