package kr.co.dbsg.api.api.auth.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KakaoUser {
    private final Long id;
    private final LocalDateTime connected_at;
}
