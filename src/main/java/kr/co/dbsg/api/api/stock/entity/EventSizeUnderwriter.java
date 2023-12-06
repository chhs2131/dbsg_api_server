package kr.co.dbsg.api.api.stock.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "event_size_underwriter")
public class EventSizeUnderwriter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "event_size_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private EventSize eventSize;

    @ManyToOne
    @JoinColumn(name = "underwriter_type_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UnderwriterType underwriterTypeId;

    @Column(name = "allocated_stock_min")
    private Integer allocatedStockMin;

    @Column(name = "allocated_stock_max")
    private Integer allocatedStockMax;

    @Column(name = "allocated_stock_fixed")
    private Integer allocatedStockFixed;

    @Column(name = "allocated_target_type")
    private String allocatedTargetType;
}