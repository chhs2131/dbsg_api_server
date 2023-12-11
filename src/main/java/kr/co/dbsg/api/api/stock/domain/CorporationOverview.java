package kr.co.dbsg.api.api.stock.domain;

import kr.co.dbsg.api.api.stock.domain.type.CorporationType;
import kr.co.dbsg.api.api.stock.domain.type.MarketType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CorporationOverview {
    private final int id;
    private final String name;
    private final String code;
    private final CorporationType corporationType;
    private final MarketType marketType;
    private final Sector sector;
}
