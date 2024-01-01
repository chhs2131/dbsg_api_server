package kr.co.dbsg.api.api.stock.repository;

import java.time.LocalDateTime;
import java.util.List;
import kr.co.dbsg.api.api.stock.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Integer> {
    List<EventEntity> findAll();
    Page<EventEntity> findAll(Pageable pageable);
    Page<EventEntity> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
}
