package kr.co.dbsg.api.api.event.entity;

import jakarta.persistence.*;
import kr.co.dbsg.api.api.event.domain.type.Underwriter;
import lombok.ToString;

@Entity
@Table(name = "event_size_underwriter")
@ToString(exclude = "eventSize")
public class EventSizeUnderwriterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_size_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private EventSizeEntity eventSize;

    @ManyToOne
    @JoinColumn(name = "underwriter_type_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UnderwriterTypeEntity underwriterTypeId;

    @Column(name = "allocated_stock_min")
    private Integer allocatedStockMin;

    @Column(name = "allocated_stock_max")
    private Integer allocatedStockMax;

    @Column(name = "allocated_stock_fixed")
    private Integer allocatedStockFixed;

    @Column(name = "allocated_target_type")
    private String allocatedTargetType;

    public Underwriter toUnderwriter() {
        return new Underwriter(
                id,
                underwriterTypeId.getName(),
                allocatedStockFixed,
                0,
                ""
        );
    }
}