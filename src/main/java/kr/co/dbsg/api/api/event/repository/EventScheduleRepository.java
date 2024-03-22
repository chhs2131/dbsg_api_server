package kr.co.dbsg.api.api.event.repository;

import kr.co.dbsg.api.api.event.entity.EventScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventScheduleRepository extends JpaRepository<EventScheduleEntity, Integer> {
}
