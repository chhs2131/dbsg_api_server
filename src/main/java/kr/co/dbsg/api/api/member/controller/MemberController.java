package kr.co.dbsg.api.api.member.controller;

import kr.co.dbsg.api.api.member.domain.Member;
import kr.co.dbsg.api.api.member.dto.MemberResponse;
import kr.co.dbsg.api.api.member.service.MemberService;
import kr.co.dbsg.api.global.resolver.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public MemberResponse me(@LoginUser Member member) {
        return new MemberResponse(member.getId(), member.getName());
    }

    @GetMapping("/{id}")
    public MemberResponse getMember(@PathVariable(name = "id") long id) {
        Assert.isTrue(0 < id, "ID값은 0보다 커야합니다.");

        final Member member = memberService.findMember(id);
        return new MemberResponse(member.getId(), member.getName());
    }
}
