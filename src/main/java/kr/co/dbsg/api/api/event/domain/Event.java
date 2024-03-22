package kr.co.dbsg.api.api.event.domain;

import java.time.LocalDateTime;
import kr.co.dbsg.api.api.stock.domain.CorporationOverview;
import kr.co.dbsg.api.api.event.domain.type.EventPrice;
import kr.co.dbsg.api.api.event.domain.type.EventSchedule;
import kr.co.dbsg.api.api.event.domain.type.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Event {
    private final long id;
    private final String type;
    private final CorporationOverview corporationOverview;
    private final EventStatus eventStatus;
    private final EventSchedule eventSchedule;
    private final EventPrice eventPrice;
    private final Underwriters underwriters;
    private final Shareholders shareholders;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
