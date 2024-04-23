package kr.co.dbsg.api.api.auth.controller;

import kr.co.dbsg.api.api.auth.dto.UserTokenResponse;
import kr.co.dbsg.api.api.auth.service.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final KakaoLoginService authService;

    @GetMapping("/kakao/rest")
    public UserTokenResponse kakaoRestLogin(@RequestParam(value = "code") final String authCode) {
        Assert.notNull(authCode, "AuthCode는 반드시 필요합니다.");

        // TODO 3xx redirect with token
        return authService.loginWebPage(authCode);
    }

    @PostMapping("/kakao/mobile")
    public UserTokenResponse kakaoMobileLogin(@RequestParam(value = "token") final String token) {
        Assert.notNull(token, "AccessToken은 반드시 필요합니다.");

        return authService.login(token);
    }
}
