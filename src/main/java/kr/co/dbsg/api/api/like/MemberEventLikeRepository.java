package kr.co.dbsg.api.api.like;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberEventLikeRepository extends JpaRepository<MemberEventLikeEntity,Long> {
    Optional<MemberEventLikeEntity> findByMemberIdAndEventId(Long memberId, Long eventId);
}
