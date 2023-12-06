package kr.co.dbsg.api.api.stock.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "event_size")
public class EventSize {
    @Id
    @Column(name = "event_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne
    private Event event;

    @Column(name = "total_subscription_stock")
    private Integer totalSubscriptionStock;

    @Column(name = "total_subscription_money")
    private Integer totalSubscriptionMoney;

    @Column(name = "subscription_new_stock")
    private Integer subscriptionNewStock;

    @Column(name = "subscription_old_stock")
    private Integer subscriptionOldStock;
}