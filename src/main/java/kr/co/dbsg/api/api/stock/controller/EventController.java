package kr.co.dbsg.api.api.stock.controller;

import jakarta.validation.Valid;
import kr.co.dbsg.api.api.stock.dto.EventDto.EventResponse;
import kr.co.dbsg.api.api.stock.dto.EventRequestDto;
import kr.co.dbsg.api.api.stock.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/events")
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<Page<EventResponse>> getEvents(@ParameterObject @Valid @ModelAttribute EventRequestDto request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        return ResponseEntity.ok(
                eventService.getEvents(request.getStartDate(), request.getEndDate(), pageable)
                .map(EventResponse::from)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable int id) {
        return ResponseEntity.ok(
            EventResponse.from(
                eventService.getEvent(id)
            )
        );
    }
}
