package kr.co.dbsg.api.api.stock.controller;

import kr.co.dbsg.api.api.stock.dto.EventDto.EventResponse;
import kr.co.dbsg.api.api.stock.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/events")
public class EventController {
    private final EventService eventService;

    @GetMapping("")
    public Page<EventResponse> getEvents(Pageable pageable) {
        return eventService.getEvents(pageable)
                .map(EventResponse::from);
    }
}
