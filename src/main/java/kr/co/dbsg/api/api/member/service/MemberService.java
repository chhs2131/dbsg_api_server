package kr.co.dbsg.api.api.member.service;

import kr.co.dbsg.api.api.member.domain.Member;
import kr.co.dbsg.api.api.member.entity.MemberEntity;
import kr.co.dbsg.api.api.member.exception.MemberNotFoundException;
import kr.co.dbsg.api.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findMember(long userId) {
        final MemberEntity memberEntity = memberRepository.findById(userId)
            .orElseThrow(MemberNotFoundException::new);

        return new Member(memberEntity.getId(), memberEntity.getName());
    }
}
