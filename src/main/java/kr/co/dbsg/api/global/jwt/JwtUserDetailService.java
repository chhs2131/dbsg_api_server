package kr.co.dbsg.api.global.jwt;

import kr.co.dbsg.api.api.member.domain.Member;
import kr.co.dbsg.api.api.member.exception.MemberNotFoundException;
import kr.co.dbsg.api.api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailService {
    private final MemberService memberService;

    public Member loadUserById(long userId) {
        try {
            return memberService.findMember(userId);
        } catch (MemberNotFoundException e) {
            throw new AuthenticationException(e.getMessage(), e.getCause());
        }
    }
}
