package kr.co.dbsg.api.api.auth.controller;

import kr.co.dbsg.api.api.auth.dto.UserTokenResponse;
import kr.co.dbsg.api.api.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/kakao/rest")
    public UserTokenResponse restKakao(@RequestParam(value = "code") String code) {
        Assert.notNull(code, "AuthCode는 반드시 필요합니다.");

        // TODO 3xx redirect with token

        return authService.kakaoLogin(code);
    }
}
