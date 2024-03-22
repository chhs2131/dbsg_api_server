package kr.co.dbsg.api.api.event.entity;

import jakarta.persistence.*;
import kr.co.dbsg.api.api.event.domain.type.EventPrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "event_information")
@ToString(exclude = "event")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventInformationEntity {
    @Id
    @Column(name = "event_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne
    private EventEntity event;

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

    public EventPrice toEventPrice() {
        return new EventPrice(
                hopePriceLow,
                hopePriceHigh,
                fixedPrice
        );
    }
}