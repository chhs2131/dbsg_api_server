package kr.co.dbsg.api.api.auth.service;

import java.time.LocalDateTime;
import kr.co.dbsg.api.api.auth.dto.KakaoToken;
import kr.co.dbsg.api.api.auth.dto.KakaoUser;
import kr.co.dbsg.api.api.auth.dto.UserTokenResponse;
import kr.co.dbsg.api.api.auth.entity.LoginTypeEntity;
import kr.co.dbsg.api.api.auth.entity.MemberPermissionEntity;
import kr.co.dbsg.api.api.auth.repository.KakaoOauthRepository;
import kr.co.dbsg.api.api.auth.repository.LoginTypeRepository;
import kr.co.dbsg.api.api.auth.repository.MemberPermissionRepository;
import kr.co.dbsg.api.api.member.entity.MemberEntity;
import kr.co.dbsg.api.api.member.repository.MemberRepository;
import kr.co.dbsg.api.global.jwt.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private static final String KAKAO_OAUTH_TYPE = "OAUTH_KAKAO";

    private final JwtProvider jwtProvider;
    private final KakaoOauthRepository kakaoOauthRepository;
    private final MemberRepository memberRepository;
    private final LoginTypeRepository loginTypeRepository;
    private final MemberPermissionRepository memberPermissionRepository;

    public UserTokenResponse kakaoLogin(final String authCode) {

        final KakaoToken entity = kakaoOauthRepository.getKakaoToken(authCode);
        final KakaoUser kakaoUser = kakaoOauthRepository.getKakaoUser(entity.getAccess_token());

        // TODO jwt 토큰 암호화 필요
        final MemberEntity memberEntity = getMemberOrCreate(kakaoUser);
        final String jwt = jwtProvider.create(KAKAO_OAUTH_TYPE, memberEntity.getId());

        // TODO RefreshToken 기능 필요
        return new UserTokenResponse(
            memberEntity.getId(),
            jwt,
            "NO_REFRESH_TOKEN",
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

    private MemberEntity getMemberOrCreate(final KakaoUser kakaoUser) {
        return memberRepository.findByLoginTypeNameAndOriginId(KAKAO_OAUTH_TYPE, kakaoUser.getId().toString())
            .orElseGet(() -> createMember(kakaoUser));
    }

    private MemberEntity createMember(final KakaoUser kakaoUser) {
        final LoginTypeEntity loginType = loginTypeRepository.getByName(KAKAO_OAUTH_TYPE);
        final MemberPermissionEntity permission = memberPermissionRepository.getByName("USER");
        final MemberEntity newMember = new MemberEntity(
            "RANDOM_NAME",
            permission,
            loginType,
            kakaoUser.getId().toString()
        );
        return memberRepository.save(newMember);
    }
}
