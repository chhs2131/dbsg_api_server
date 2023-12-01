package kr.co.dbsg.api.api.stock.domain.type;

public record EventPrice(
        int bandLow,
        int bandHigh,
        int fixed
) {
}
