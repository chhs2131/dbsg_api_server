package kr.co.dbsg.api.api.auth.repository;

import kr.co.dbsg.api.api.auth.dto.KakaoToken;
import kr.co.dbsg.api.api.auth.dto.KakaoUser;
import kr.co.dbsg.api.api.auth.exception.OauthClientException;
import kr.co.dbsg.api.global.config.properties.KakaoApiProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class KakaoOauthRepository {
    private final KakaoApiProperty kakaoApiProperty;

    @Value("${dbsgRedirectUrl}")
    private String dbsgRedirectUrl;

    public KakaoToken getKakaoToken(final String authCode) {
        String apiKey = kakaoApiProperty.restApiKey();
        String requestUri = kakaoApiProperty.baseUrl() + "/oauth/token";
        String redirectUri = dbsgRedirectUrl + "/v1/auth/kakao/rest";

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", apiKey);
        map.add("redirect_uri", redirectUri);
        map.add("code", authCode);

        return RestClient.create().post()
            .uri(requestUri)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(map)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                throw new OauthClientException(response.getStatusText() + " => " + new String(response.getBody().readAllBytes()));
            })
            .toEntity(KakaoToken.class)
            .getBody();
    }

    public KakaoUser getKakaoUser(final String accessToken) {
        String requestUri = "https://kapi.kakao.com/v2/user/me";

        final ResponseEntity<KakaoUser> user = RestClient.create().get()
            .uri(requestUri)
            .header("Authorization", "Bearer " + accessToken)
            .retrieve()
            .toEntity(KakaoUser.class);

        return user.getBody();
    }
}
