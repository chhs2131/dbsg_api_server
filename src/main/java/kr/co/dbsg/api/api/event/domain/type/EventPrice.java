package kr.co.dbsg.api.api.event.domain.type;

public record EventPrice(
        int bandLow,
        int bandHigh,
        int fixed
) {
}
