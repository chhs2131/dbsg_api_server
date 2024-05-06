package kr.co.dbsg.api.api.underwriter.repository;

import kr.co.dbsg.api.api.event.entity.UnderwriterTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnderwriterTypeEntityRepository extends JpaRepository<UnderwriterTypeEntity, Long> {
}