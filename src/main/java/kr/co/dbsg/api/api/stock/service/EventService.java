package kr.co.dbsg.api.api.stock.service;

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

    public Page<Event> getEvents(Pageable pageable) {
        return eventRepository.findAll(pageable).map(EventEntity::toEvent);
    }
}
