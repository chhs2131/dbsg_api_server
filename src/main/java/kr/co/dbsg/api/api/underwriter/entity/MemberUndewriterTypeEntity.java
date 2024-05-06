package kr.co.dbsg.api.api.underwriter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "member_undewriter_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUndewriterTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "underwriter_type_id", referencedColumnName = "id")
    private UnderwriterTypeEntity underwriterType;

    public MemberUndewriterTypeEntity(Long memberId, UnderwriterTypeEntity underwriterType) {
        this.memberId = memberId;
        this.underwriterType = underwriterType;
    }
}
