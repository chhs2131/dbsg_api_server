package kr.co.dbsg.api.api.stock.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "event_information")
public class EventInformation {
    @Id
    @Column(name = "event_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne
    private Event event;

    @Column(name = "issuance_reason")
    private String issuanceReason;

    @Column(name = "par_value")
    private Integer parValue;

    @Column(name = "hope_price_high")
    private Integer hopePriceHigh;

    @Column(name = "hope_price_low")
    private Integer hopePriceLow;

    @Column(name = "fixed_price")
    private Integer fixedPrice;

    @Column(name = "chance_rate_of_institutional")
    private Integer chanceRateOfInstitutional;

    @Column(name = "chance_rate_of_even")
    private Integer chanceRateOfEven;

    @Column(name = "chance_rate_of_proportional")
    private Integer chanceRateOfProportional;

    @Column(name = "deposit_percent")
    private Double depositPercent;

    @Column(name = "minimum_subscription")
    private Integer minimumSubscription;
}