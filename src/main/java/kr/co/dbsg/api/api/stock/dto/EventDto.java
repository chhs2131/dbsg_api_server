package kr.co.dbsg.api.api.stock.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import kr.co.dbsg.api.api.stock.domain.CorporationOverview;
import kr.co.dbsg.api.api.stock.domain.Event;
import kr.co.dbsg.api.api.stock.domain.Shareholders;
import kr.co.dbsg.api.api.stock.domain.Underwriters;
import kr.co.dbsg.api.api.stock.domain.type.EventPrice;
import kr.co.dbsg.api.api.stock.domain.type.EventSchedule;
import kr.co.dbsg.api.api.stock.domain.type.EventStatus;
import lombok.Builder;

public class EventDto {
    @Builder
    public record EventResponse(
        long id,
        String type,
        CorporationOverviewDto corporationOverview,
        EventInformationDto eventInformation,
        FinancialInformationDto financialInformation,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        public static EventResponse from(Event event) {
            return EventResponse.builder()
                .id(event.getId())
                .type(event.getType())
                .corporationOverview(CorporationOverviewDto.from(event.getCorporationOverview()))
                .eventInformation(EventInformationDto.from(
                    event.getEventStatus(),
                    event.getEventSchedule(),
                    event.getEventPrice(),
                    event.getUnderwriters(),
                    event.getShareholders()
                ))
                .financialInformation(null)
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getUpdatedAt())
                .build();
        }
    }

    /* 기업 개요 */
    public record CorporationOverviewDto(String name, String code, String corporationType, String marketType, SectorDto sectorDto) {
        public static CorporationOverviewDto from(CorporationOverview corporationOverview) {
            return new CorporationOverviewDto(
                    corporationOverview.getName(),
                    corporationOverview.getCode(),
                    corporationOverview.getCorporationType().name(),
                    corporationOverview.getMarketType().name(),
                    new SectorDto(corporationOverview.getSector().getId(), corporationOverview.getSector().getName())
            );
        }
    }

    public record SectorDto(String id, String name) {
    }

    /* 이벤트 정보 */
    public record EventInformationDto(
        String status,
        EventScheduleDto schedule,
        EventPriceDto price,
        List<EventUnderwriterDto> underwriters,
        List<ShareholderDto> shareholders
    ) {
        public static EventInformationDto from(
                EventStatus eventStatus,
                EventSchedule eventSchedule,
                EventPrice eventPrice,
                Underwriters underwriters,
                Shareholders shareholders
        ) {
            return new EventInformationDto(
                    eventStatus.name(),
                    EventScheduleDto.from(eventSchedule),
                    EventPriceDto.from(eventPrice),
                    EventUnderwriterDto.from(underwriters),
                    null
            );
        }
    }

    public record EventScheduleDto(
            LocalDate forecastStart,
            LocalDate forecastEnd,
            LocalDate subscriptionStart,
            LocalDate subscriptionEnd,
            LocalDate refund,
            LocalDate debut,
            LocalDate eventCancel  // TODO 존재하지 않을 수 있음
    ) {
        public static EventScheduleDto from(EventSchedule schedule) {
            return new EventScheduleDto(
                    schedule.getForecastStart(),
                    schedule.getForecastEnd(),
                    schedule.getSubscriptionStartDate(),
                    schedule.getSubscriptionEndDate(),
                    schedule.getRefund(),
                    schedule.getDebut(),
                    schedule.getEventCancelDate()
            );
        }
    }

    public record EventPriceDto(  // TODO 아래 값 중 일부만 존재 할 수 있음 (이벤트 타입에 따라서)
                                  int bandLow,
                                  int bandHigh,
                                  int fixed
    ) {
        public static EventPriceDto from(EventPrice eventPrice) {
            return new EventPriceDto(
                    eventPrice.bandLow(),
                    eventPrice.bandHigh(),
                    eventPrice.fixed()
            );
        }
    }

    public record EventUnderwriterDto(
            int id,
            String name,
            long amount,
            long limit,
            String note
    ) {
        public static List<EventUnderwriterDto> from(Underwriters underwriters) {
            return underwriters.getUnderwriterList()
                    .stream()
                    .map(underwriter -> new EventUnderwriterDto(
                            underwriter.getId(),
                            underwriter.getName(),
                            underwriter.getAmount(),
                            underwriter.getLimit(),
                            underwriter.getNote()
                    ))
                    .toList();
        }
    }

    public record ShareholderDto(
            String name,
            long amount
    ) {
        public static List<ShareholderDto> from(Shareholders shareholders) {
            final Map<String, Long> shareholdersMap = shareholders.getShareholderMap();

            return shareholdersMap.entrySet()
                    .stream()
                    .map(entry -> new ShareholderDto(entry.getKey(), entry.getValue()))
                    .toList();
        }
    }

    /* 재정 정보 */
    public record FinancialInformationDto(
            long revenue,
            long profit,
            long capital
    ) {
    }

    /* 거래 정보 */
    // TODO
    public record TradingInformationDto(
            String firstDay,
            String trend
    ) {
    }
}
