package kr.co.dbsg.api.api.event.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import kr.co.dbsg.api.api.stock.domain.CorporationOverview;
import kr.co.dbsg.api.api.event.domain.Event;
import kr.co.dbsg.api.api.event.domain.Shareholders;
import kr.co.dbsg.api.api.event.domain.Underwriters;
import kr.co.dbsg.api.api.event.domain.type.EventPrice;
import kr.co.dbsg.api.api.event.domain.EventSchedule;
import kr.co.dbsg.api.api.event.domain.type.EventStatus;
import lombok.Builder;
import org.springframework.util.Assert;

public class EventDetailResonse {
    @Builder
    public record response(
        long id,
        String type,
        CorporationOverviewDto corporationOverview,
        EventInformationDto eventInformation,
        FinancialInformationDto financialInformation,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        public static response from(Event event) {
            Assert.notNull(event, "500 SERVER ERROR");

            return response.builder()
                .id(event.getId())
                .type(event.getType())
                .corporationOverview(CorporationOverviewDto.from(event.getCorporationOverview()))
                .eventInformation(EventInformationDto.from(
                    event.getStatus(),
                    event.getSchedule(),
                    event.getPriceInformation(),
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
    private record CorporationOverviewDto(String name, String code, String corporationType, String marketType, SectorDto sector) {
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

    private record SectorDto(String id, String name) {
    }

    /* 이벤트 정보 */
    private record EventInformationDto(
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
                    eventStatus != null ? eventStatus.toString() : "",
                    EventScheduleDto.from(eventSchedule),
                    EventPriceDto.from(eventPrice),
                    EventUnderwriterDto.from(underwriters),
                    ShareholderDto.from(shareholders)
            );
        }
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
            if (schedule == null) {
                return null;
            }

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

    private record EventPriceDto(
                                  Integer bandLow,
                                  Integer bandHigh,
                                  Integer fixed
    ) {
        public static EventPriceDto from(EventPrice eventPrice) {
            if (eventPrice == null) {
                return null;
            }

            return new EventPriceDto(
                    eventPrice.bandLow(),
                    eventPrice.bandHigh(),
                    eventPrice.fixed()
            );
        }
    }

    private record EventUnderwriterDto(
            Long id,
            String name,
            long amount,
            long limit,
            String note
    ) {
        public static List<EventUnderwriterDto> from(Underwriters underwriters) {
            if (underwriters == null) {
                return null;
            }

            return underwriters.getUnderwriterList()
                    .stream()
                    .map(underwriter -> new EventUnderwriterDto(
                            underwriter.getId(),
                            underwriter.getName(),
                            underwriter.getAmount(),
                            underwriter.getLimit(),
                            underwriter.getNote()
                    ))
                    .sorted((a1, a2) -> -Math.toIntExact(a1.amount - a2.amount))
                    .toList();
        }
    }

    private record ShareholderDto(
            String name,
            long amount
    ) {
        public static List<ShareholderDto> from(Shareholders shareholders) {
            if (shareholders == null) {
                return null;
            }

            final Map<String, Long> shareholdersMap = shareholders.getShareholderMap();

            return shareholdersMap.entrySet()
                    .stream()
                    .map(entry -> new ShareholderDto(entry.getKey(), entry.getValue()))
                    .toList();
        }
    }

    /* 재정 정보 */
    private record FinancialInformationDto(
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
