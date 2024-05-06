package kr.co.dbsg.api.api.underwriter.repository;

import java.util.List;
import kr.co.dbsg.api.api.underwriter.entity.MemberUndewriterTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberUndewriterTypeRepository extends JpaRepository<MemberUndewriterTypeEntity, Long> {
    List<MemberUndewriterTypeEntity> findByMemberId(@NonNull Long memberId);

    boolean existsByMemberIdAndUnderwriterTypeId(@NonNull Long memberId, @NonNull Long underwriterTypeId);

    MemberUndewriterTypeEntity findByMemberIdAndUnderwriterTypeId(@NonNull Long memberId,
                                                                  @NonNull Long underwriterTypeId);

    long deleteByMemberIdAndUnderwriterTypeId(@NonNull Long memberId, @NonNull Long underwriterTypeId);

}
