package kr.co.dbsg.api.api.like;

import kr.co.dbsg.api.api.event.exception.EventNotFoundException;
import kr.co.dbsg.api.api.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final MemberEventLikeRepository memberEventLikeRepository;
    private final EventRepository eventRepository;

    @Transactional
    public MemberEventLikeEntity likeEvent(long memberId, long eventId) {
        verifyEvent(eventId);

        final MemberEventLikeEntity likeEvent = memberEventLikeRepository.findByMemberIdAndEventId(memberId,
                eventId)
            .orElse(new MemberEventLikeEntity(memberId, eventId));

        return memberEventLikeRepository.save(likeEvent);
    }

    @Transactional
    public void deleteLikeEvent(long memberId, long eventId) {
        verifyEvent(eventId);

        memberEventLikeRepository.findByMemberIdAndEventId(memberId, eventId)
            .ifPresent(memberEventLikeRepository::delete);
    }

    @Transactional(readOnly = true)
    protected void verifyEvent(long eventId) {
        eventRepository.findById(eventId)
            .orElseThrow(EventNotFoundException::new);
    }
}
