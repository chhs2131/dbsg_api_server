package kr.co.dbsg.api.api.event.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "underwriter_type_nickname")
public class UnderwriterTypeNicknameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "underwriter_type_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UnderwriterTypeEntity underwriterTypeId;

    @Column(name = "name", nullable = false)
    private String name;
}