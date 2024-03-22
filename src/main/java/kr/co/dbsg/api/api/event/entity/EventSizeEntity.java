package kr.co.dbsg.api.api.event.entity;

import jakarta.persistence.*;
import java.util.List;
import kr.co.dbsg.api.api.event.domain.Underwriters;
import kr.co.dbsg.api.api.event.domain.type.Underwriter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "event_size")
@ToString(exclude = "event")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventSizeEntity {
    @Id
    @Column(name = "event_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne
    private EventEntity event;

    @OneToMany(mappedBy = "eventSize")
    private List<EventSizeUnderwriterEntity> eventSizeUnderwriter;

    @Column(name = "total_subscription_stock")
    private Integer totalSubscriptionStock;

    @Column(name = "total_subscription_money")
    private Integer totalSubscriptionMoney;

    @Column(name = "subscription_new_stock")
    private Integer subscriptionNewStock;

    @Column(name = "subscription_old_stock")
    private Integer subscriptionOldStock;

    public Underwriters toUnderwriters() {
        List<Underwriter> underwriters = eventSizeUnderwriter.stream()
                .map(EventSizeUnderwriterEntity::toUnderwriter)
                .toList();

        return new Underwriters(underwriters);
    }
}