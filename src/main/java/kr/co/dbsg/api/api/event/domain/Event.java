package kr.co.dbsg.api.api.event.domain;

import java.time.LocalDateTime;
import kr.co.dbsg.api.api.stock.domain.CorporationOverview;
import kr.co.dbsg.api.api.event.domain.type.EventPrice;
import kr.co.dbsg.api.api.event.domain.type.EventStatus;
import lombok.Getter;

@Getter
public class Event {
    private final long id;
    private final String type;
    private final CorporationOverview corporationOverview;
    private final EventSchedule schedule;
    private final EventPrice priceInformation;
    private final Underwriters underwriters;
    private final Shareholders shareholders;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Event(final long id, final String type, final CorporationOverview corporationOverview,
                 final EventSchedule schedule,
                 final EventPrice priceInformation, final Underwriters underwriters, final Shareholders shareholders,
                 final LocalDateTime createdAt,
                 final LocalDateTime updatedAt) {
        this.id = id;
        this.type = type;
        this.corporationOverview = corporationOverview;
        this.schedule = schedule;
        this.priceInformation = priceInformation;
        this.underwriters = underwriters;
        this.shareholders = shareholders;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public EventStatus getStatus() {
        if (schedule == null) {
            return null;
        }
        return schedule.getStatus();
    }
}
