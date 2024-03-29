package kr.co.dbsg.api.api.event.dto;

import java.time.LocalDate;
import java.util.List;
import kr.co.dbsg.api.api.event.domain.Event;
import kr.co.dbsg.api.api.event.domain.EventSchedule;
import kr.co.dbsg.api.api.event.domain.Underwriters;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class EventResponse {
    long id;
    String type;
    String corporationName;
    String status;
    String marketType;
    EventScheduleDto eventSchedule;
    List<EventUnderwriterDto> underwriters;

    public static EventResponse from(Event event) {
        return EventResponse.builder()
            .id(event.getId())
            .type(event.getType())
            .corporationName(event.getCorporationOverview().getName())
            .status(event.getSchedule().getStatus().toString())
            .marketType(event.getCorporationOverview().getMarketType().toString())
            .eventSchedule(EventScheduleDto.from(event.getSchedule()))
            .underwriters(EventUnderwriterDto.from(event.getUnderwriters()))
            .build();
    }

    private record EventScheduleDto(
        LocalDate forecastStart,
        LocalDate forecastEnd,
        LocalDate subscriptionStart,
        LocalDate subscriptionEnd,
        LocalDate refund,
        LocalDate debut,
        LocalDate eventCancel
    ) {
        public static EventScheduleDto from(EventSchedule schedule) {
            return new EventScheduleDto(
                schedule.getForecastStart(),
                schedule.getForecastEnd(),
                schedule.getSubscriptionStart(),
                schedule.getSubscriptionEnd(),
                schedule.getRefund(),
                schedule.getDebut(),
                schedule.getEventCancel()
            );
        }
    }

    private record EventUnderwriterDto(
        Long id,
        String name,
        long amount
    ) {
        public static List<EventUnderwriterDto> from(Underwriters underwriters) {
            return underwriters.getUnderwriterList()
                .stream()
                .map(underwriter -> new EventUnderwriterDto(
                    underwriter.getId(),
                    underwriter.getName(),
                    underwriter.getAmount()
                ))
                .sorted((a1, a2) -> -Math.toIntExact(a1.amount - a2.amount))
                .toList();
        }
    }
}
