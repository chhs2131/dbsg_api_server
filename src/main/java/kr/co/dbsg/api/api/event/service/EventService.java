package kr.co.dbsg.api.api.event.service;

import java.time.LocalDate;
import kr.co.dbsg.api.api.event.domain.Event;
import kr.co.dbsg.api.api.event.entity.EventEntity;
import kr.co.dbsg.api.api.event.exception.EventNotFoundException;
import kr.co.dbsg.api.api.event.repository.EventRepository;
import kr.co.dbsg.api.api.event.repository.EventRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventRepositoryImpl eventRepositoryImpl;

    @Transactional(readOnly = true)
    public Page<Event> getEvents(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return eventRepositoryImpl.findAllByDateRange(startDate, endDate.plusDays(1), pageable)
            .map(EventEntity::toEvent);
    }

    @Transactional(readOnly = true)
    public Event getEvent(long id) {
        return eventRepository.findById(id)
            .orElseThrow(EventNotFoundException::new)
            .toEvent();
    }
}
