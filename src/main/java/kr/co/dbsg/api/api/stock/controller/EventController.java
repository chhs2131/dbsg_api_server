package kr.co.dbsg.api.api.stock.controller;

import java.time.LocalDate;
import kr.co.dbsg.api.api.stock.dto.EventDto.EventResponse;
import kr.co.dbsg.api.api.stock.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/events")
public class EventController {
    private final EventService eventService;

    @GetMapping("")
    public ResponseEntity<Page<EventResponse>> getEvents(
        @RequestParam(name = "page", defaultValue = "0", required = false) int page,
        @RequestParam(name = "size", defaultValue = "10", required = false) int size,
        @RequestParam(name = "startDate", defaultValue = "#{T(java.time.LocalDateTime).now().minusDays(7)}", required = false) LocalDate startDate,
        @RequestParam(name = "endDate", defaultValue = "#{T(java.time.LocalDateTime).now()}", required = false) LocalDate endDate
    ) {
        // TODO validation

        // logic
        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(
                eventService.getEvents(startDate, endDate, pageable)
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
