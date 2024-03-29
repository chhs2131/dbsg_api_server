package kr.co.dbsg.api.api.stock.entity;

import jakarta.persistence.*;
import kr.co.dbsg.api.api.stock.domain.CorporationOverview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "stock")
@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "market_code")
    private String marketCode;

    @Column(name = "dart_code", nullable = false)
    private String dartCode;

    @Column(name = "corp_name")
    private String corpName;

    @Column(name = "modify_date")
    private String modifyDate;

    @OneToOne(mappedBy = "stock")
    private StockInformationEntity stockInformation;

    public CorporationOverview toCorporationOverview() {
        return new CorporationOverview(
                id,
                corpName,
                marketCode,
                stockInformation.getCompanyType().toCorporationType(),
                stockInformation.getMarketType().toMarketType(),
                stockInformation.getSectorType().toSector()
        );
    }
}