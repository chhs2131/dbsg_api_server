package kr.co.dbsg.api.api.event.controller;

import jakarta.validation.Valid;
import kr.co.dbsg.api.api.event.dto.EventDetailResonse.response;
import kr.co.dbsg.api.api.event.dto.EventRequest;
import kr.co.dbsg.api.api.event.dto.EventResponse;
import kr.co.dbsg.api.api.event.service.EventService;
import kr.co.dbsg.api.api.like.LikeService;
import kr.co.dbsg.api.api.member.entity.MemberEntity;
import kr.co.dbsg.api.global.resolver.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/events")
public class EventController {
    private final EventService eventService;
    private final LikeService likeService;

    @GetMapping
    public ResponseEntity<Page<EventResponse>> getEvents(@ParameterObject @Valid @ModelAttribute EventRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        return ResponseEntity.ok(
                eventService.getEvents(request.getStartDate(), request.getEndDate(), pageable)
                .map(EventResponse::from)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<response> getEvent(@PathVariable long id) {
        return ResponseEntity.ok(
            response.from(
                eventService.getEvent(id)
            )
        );
    }

    @PostMapping("/{id}/like")
    public void likeEvent(@PathVariable long id, @LoginUser MemberEntity member) {
        likeService.likeEvent(member.getId(), id);
    }

    @DeleteMapping("/{id}/like")
    public void deleteLikeEvent(@PathVariable long id, @LoginUser MemberEntity member) {
        likeService.deleteLikeEvent(member.getId(), id);
    }
}
