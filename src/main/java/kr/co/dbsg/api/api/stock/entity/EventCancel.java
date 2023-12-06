package kr.co.dbsg.api.api.stock.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "event_cancel")
public class EventCancel {
    @Id
    @Column(name = "event_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne
    private Event event;

    @Column(name = "cancel_date")
    private LocalDate cancelDate;

    @Column(name = "cancel_reason")
    private String cancelReason;
}