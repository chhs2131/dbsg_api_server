package kr.co.dbsg.api.api.underwriter.controller;

import java.util.List;
import kr.co.dbsg.api.api.member.domain.Member;
import kr.co.dbsg.api.api.underwriter.dto.UnderwriterResponse;
import kr.co.dbsg.api.api.underwriter.service.UnderwriterLikeService;
import kr.co.dbsg.api.api.underwriter.service.UnderwriterService;
import kr.co.dbsg.api.global.resolver.LoginUser;
import kr.co.dbsg.api.global.security.AuthenticationContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/underwriters")
@RequiredArgsConstructor
public class UnderwriterController {
    private final UnderwriterLikeService underwriterLikeService;
    private final UnderwriterService underwriterService;

    // 전체 주간사 목록 조회
    @GetMapping
    public List<UnderwriterResponse> getAll() {
        return underwriterService.getAll()
            .stream()
            .map(like -> new UnderwriterResponse(like.getId(), like.getName()))
            .toList();
    }

    // 내가 좋아요한 주간사 목록 조회
    @LoginUser
    @GetMapping("/my/like")
    public List<UnderwriterResponse> listUpLiked() {
        final Member member = AuthenticationContextHolder.getContext();

        return underwriterLikeService.listUpLiked(member.getId())
            .stream()
            .map(like -> new UnderwriterResponse(like.getUnderwriterType().getId(), like.getUnderwriterType().getName()))
            .toList();
    }

    // 특정 주간사 좋아요하기
    @LoginUser
    @PostMapping("/{id}/like")
    public void likeUnderwriter(@PathVariable(name = "id") Long underwriterId) {
        final Member member = AuthenticationContextHolder.getContext();
        underwriterLikeService.likeUnderwriter(member.getId(), underwriterId);
    }

    // 특정 주간사 좋아요 취소
    @LoginUser
    @DeleteMapping("/{id}/like")
    public void deleteLikeUnderwriter(@PathVariable(name = "id") Long underwriterId) {
        final Member member = AuthenticationContextHolder.getContext();
        underwriterLikeService.deleteLikeUnderwriter(member.getId(), underwriterId);
    }
}
