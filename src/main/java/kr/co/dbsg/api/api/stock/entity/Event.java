package kr.co.dbsg.api.api.stock.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Stock stockId;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Column(name = "status", nullable = false)
    private String status;
}