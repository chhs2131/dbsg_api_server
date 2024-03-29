package kr.co.dbsg.api.api.event.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "event_cancel")
public class EventCancelEntity {
    @Id
    @Column(name = "event_id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne
    private EventEntity event;

    @Column(name = "cancel_date")
    private LocalDate cancelDate;

    @Column(name = "cancel_reason")
    private String cancelReason;
}