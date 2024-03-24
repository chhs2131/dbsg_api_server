package kr.co.dbsg.api.api.auth.dto;

import java.time.LocalDateTime;

public record UserTokenResponse(
    Long id,
    String accessToken,
    String refreshToken,
    LocalDateTime expiredAccessAt,
    LocalDateTime expiredRefreshAt) {
}
