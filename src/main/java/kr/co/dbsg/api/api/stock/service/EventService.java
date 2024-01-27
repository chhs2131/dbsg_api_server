package kr.co.dbsg.api.api.stock.service;

import java.time.LocalDate;
import kr.co.dbsg.api.api.stock.domain.Event;
import kr.co.dbsg.api.api.stock.entity.EventEntity;
import kr.co.dbsg.api.api.stock.repository.EventRepository;
import kr.co.dbsg.api.api.stock.repository.EventRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventRepositoryImpl eventRepositoryImpl;

    public Page<Event> getEvents(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return eventRepositoryImpl.findAllByDateRange(startDate, endDate.plusDays(1), pageable)
            .map(EventEntity::toEvent);
    }

    public Event getEvent(int id) {
        return eventRepository.findById(id)
            .orElseThrow(IllegalArgumentException::new)
            .toEvent();
    }
}
