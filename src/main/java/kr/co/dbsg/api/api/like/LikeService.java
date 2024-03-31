package kr.co.dbsg.api.api.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final MemberEventLikeRepository memberEventLikeRepository;

    @Transactional
    public MemberEventLikeEntity likeEvent(long memberId, long eventId) {


        final MemberEventLikeEntity likeEvent = memberEventLikeRepository.findByMemberIdAndEventId(memberId,
                eventId)
            .orElse(new MemberEventLikeEntity(memberId, eventId));

        return memberEventLikeRepository.save(likeEvent);
    }

    @Transactional
    public void deleteLikeEvent(long memberId, long eventId) {
        memberEventLikeRepository.findByMemberIdAndEventId(memberId, eventId)
            .ifPresent(memberEventLikeRepository::delete);
    }
}
