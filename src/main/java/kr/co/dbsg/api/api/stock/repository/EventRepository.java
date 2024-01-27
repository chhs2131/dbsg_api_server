package kr.co.dbsg.api.api.stock.repository;

import kr.co.dbsg.api.api.stock.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Integer>, JpaSpecificationExecutor<EventEntity> {
}
