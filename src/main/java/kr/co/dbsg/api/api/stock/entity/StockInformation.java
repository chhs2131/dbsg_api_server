package kr.co.dbsg.api.api.stock.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stock_information")
public class StockInformation {
    @Id
    @Column(name = "stock_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "market_type_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MarketType marketType;

    @ManyToOne
    @JoinColumn(name = "sector_type_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private SectorType sectorType;

    @ManyToOne
    @JoinColumn(name = "company_type_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private CompanyType companyType;

    @Column(name = "sale")
    private Integer sale;

    @Column(name = "profit")
    private Integer profit;

    @Column(name = "volume")
    private Integer volume;
}