package kr.co.dbsg.api.api.auth.repository;

import kr.co.dbsg.api.api.auth.entity.MemberPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPermissionRepository extends JpaRepository<MemberPermissionEntity, Long> {
    MemberPermissionEntity getByName(String name);
}
