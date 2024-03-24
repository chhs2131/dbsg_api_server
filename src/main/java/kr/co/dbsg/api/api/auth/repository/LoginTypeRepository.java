package kr.co.dbsg.api.api.auth.repository;

import kr.co.dbsg.api.api.auth.entity.LoginTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginTypeRepository extends JpaRepository<LoginTypeEntity, Long> {
    LoginTypeEntity getByName(String name);
}
