package kr.co.dbsg.api.api.stock.dto;

import java.time.LocalDate;
import java.util.List;

public class EventDto {
    public record Response(
    int id,
    String type,
    CorporationOverview corporationOverview,
    EventInformation eventInformation,
    FinancialInformation financialInformation
    ) {
    }

    /* 기업 개요 */
    public record CorporationOverview(String name, String code, String type, String marketType, Sector sector) {
    }

    public record Sector(String id, String name) {
    }

    /* 이벤트 정보 */
    public record EventInformation(
        String status,
        EventSchedule schedule,
        EventPrice price,
        List<EventUnderwriters> underwriters,
        List<Shareholders> shareholders
    ) {
    }

    public record EventSchedule(
            LocalDate forecastStart,
            LocalDate forecastEnd,
            LocalDate refund,
            LocalDate debut,
            LocalDate eventCancel  // TODO 존재하지 않을 수 있음
    ) {
    }

    public record EventPrice(  // TODO 아래 값 중 일부만 존재 할 수 있음 (이벤트 타입에 따라서)
            int bandLow,
            int bandHigh,
            int fixed
    ) {
    }

    public record EventUnderwriters(
            int id,
            String name,
            long amount,
            long limit,
            String note
    ) {
    }

    public record Shareholders(
            String name,
            long amount
    ) {
    }

    /* 재정 정보 */
    public record FinancialInformation(
            long revenue,
            long profit,
            long captial
    ) {
    }

    /* 거래 정보 */
    // TODO
    public record TradingInformation(
            String firstDay,
            String trend
    ) {
    }
}
