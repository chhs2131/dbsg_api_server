package kr.co.dbsg.api.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KakaoToken {
    private final String token_type;
    private final String access_token;
    private final int expires_in;
    private final String refresh_token;
    private final int refresh_token_expires_in;
    private final String scope;
}
