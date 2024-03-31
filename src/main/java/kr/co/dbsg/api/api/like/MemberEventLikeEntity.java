package kr.co.dbsg.api.api.like;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member_event_like")
@NoArgsConstructor
public class MemberEventLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @NotNull
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    public MemberEventLikeEntity(final Long memberId, final Long eventId) {
        this.eventId = eventId;
        this.memberId = memberId;
    }
}