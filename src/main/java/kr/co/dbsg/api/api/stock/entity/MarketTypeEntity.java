package kr.co.dbsg.api.api.stock.entity;

import jakarta.persistence.*;
import kr.co.dbsg.api.api.stock.domain.type.MarketType;

@Entity
@Table(name = "market_type")
public class MarketTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    public MarketType toMarketType() {
        return MarketType.ETC;  //TODO MarketType ConvertCode 작성
    }
}