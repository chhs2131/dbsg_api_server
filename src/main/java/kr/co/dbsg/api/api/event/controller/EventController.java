package kr.co.dbsg.api.api.event.controller;

import jakarta.validation.Valid;
import kr.co.dbsg.api.api.event.dto.EventDetailResonse.response;
import kr.co.dbsg.api.api.event.dto.EventRequest;
import kr.co.dbsg.api.api.event.dto.EventResponse;
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
    public ResponseEntity<Page<EventResponse>> getEvents(@ParameterObject @Valid @ModelAttribute EventRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        return ResponseEntity.ok(
                eventService.getEvents(request.getStartDate(), request.getEndDate(), pageable)
                .map((a) -> {
                    final EventResponse from = EventResponse.from(a);
                    System.out.println(from);

                    return from;
                })
//                .map(EventResponse::from)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<response> getEvent(@PathVariable int id) {
        return ResponseEntity.ok(
            response.from(
                eventService.getEvent(id)
            )
        );
    }
}
