package kr.co.dbsg.api.api.stock.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "stock_information")
@Getter
public class StockInformationEntity {
    @Id
    @Column(name = "stock_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne
    private StockEntity stock;

    @ManyToOne
    @JoinColumn(name = "market_type_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MarketTypeEntity marketType;

    @ManyToOne
    @JoinColumn(name = "sector_type_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private SectorTypeEntity sectorType;

    @ManyToOne
    @JoinColumn(name = "company_type_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private CompanyTypeEntity companyType;

    @Column(name = "sale")
    private Integer sale;

    @Column(name = "profit")
    private Integer profit;

    @Column(name = "volume")
    private Integer volume;
}