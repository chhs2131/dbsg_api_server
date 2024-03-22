package kr.co.dbsg.api.api.event.controller;

import jakarta.validation.Valid;
import kr.co.dbsg.api.api.event.dto.EventResonse.resonse;
import kr.co.dbsg.api.api.event.dto.EventRequest;
import kr.co.dbsg.api.api.event.service.EventService;
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
    public ResponseEntity<Page<resonse>> getEvents(@ParameterObject @Valid @ModelAttribute EventRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        return ResponseEntity.ok(
                eventService.getEvents(request.getStartDate(), request.getEndDate(), pageable)
                .map(resonse::from)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<resonse> getEvent(@PathVariable int id) {
        return ResponseEntity.ok(
            resonse.from(
                eventService.getEvent(id)
            )
        );
    }
}
