package kr.co.dbsg.api.api.stock.domain;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Shareholders {
    private final Map<String, Long> shareholderMap;

    // TODO add and remove logic, 별도로 할당해주지 않으면 기본적으로 빈 맵을 가지고 있게 지정
}
