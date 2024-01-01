package kr.co.dbsg.api.api.stock.service;

import java.time.LocalDate;
import kr.co.dbsg.api.api.stock.domain.Event;
import kr.co.dbsg.api.api.stock.entity.EventEntity;
import kr.co.dbsg.api.api.stock.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public Page<Event> getEvents(Pageable pageable, LocalDate startDate, LocalDate endDate) {
        return eventRepository.findAllByCreatedAtBetween(startDate.atStartOfDay(), endDate.plusDays(1).atStartOfDay(), pageable).map(EventEntity::toEvent);
    }

    public Event getEvent(int id) {
        return eventRepository.findById(id)
            .orElseThrow(IllegalArgumentException::new)
            .toEvent();
    }
}
