package kr.co.dbsg.api.api.stock.domain;

import kr.co.dbsg.api.api.stock.domain.type.EventPrice;
import kr.co.dbsg.api.api.stock.domain.type.EventSchedule;
import kr.co.dbsg.api.api.stock.domain.type.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Event {
    private final long id;
    private final EventStatus eventStatus;
    private final EventSchedule eventSchedule;
    private final EventPrice eventPrice;
    private final Underwriters underwriters;
    private final Shareholders shareholders;
}
