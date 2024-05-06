package kr.co.dbsg.api.api.underwriter.service;

import java.util.List;
import kr.co.dbsg.api.api.event.entity.UnderwriterTypeEntity;
import kr.co.dbsg.api.api.underwriter.entity.MemberUndewriterTypeEntity;
import kr.co.dbsg.api.api.underwriter.repository.MemberUndewriterTypeRepository;
import kr.co.dbsg.api.api.underwriter.repository.UnderwriterTypeEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UnderwriterLikeService {
    private final MemberUndewriterTypeRepository underwriterLikeRepository;
    private final UnderwriterTypeEntityRepository underwriterRepository;

    public List<MemberUndewriterTypeEntity> listUpLiked(long userId) {
        return underwriterLikeRepository.findByMemberId(userId);
    }

    @Transactional
    public void likeUnderwriter(long userId, long underwriterId) {
        if (existsLiked(userId, underwriterId)) {
            return;
        }
        final UnderwriterTypeEntity underwriter = underwriterRepository.findById(underwriterId)
            .orElseThrow(IllegalArgumentException::new);

        underwriterLikeRepository.save(new MemberUndewriterTypeEntity(userId, underwriter));
    }

    @Transactional
    public void deleteLikeUnderwriter(long userId, long underwriterId) {
        if (!existsLiked(userId, underwriterId)) {
            return;
        }

        underwriterLikeRepository.deleteByMemberIdAndUnderwriterTypeId(userId, underwriterId);
    }

    private boolean existsLiked(long userId, long underwriterId) {
        return underwriterLikeRepository.existsByMemberIdAndUnderwriterTypeId(userId, underwriterId);
    }
}
