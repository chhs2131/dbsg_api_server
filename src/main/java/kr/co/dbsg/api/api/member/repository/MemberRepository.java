package kr.co.dbsg.api.api.member.repository;

import java.util.Optional;
import kr.co.dbsg.api.api.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByLoginTypeNameAndOriginId(String loginType, String originId);
}
