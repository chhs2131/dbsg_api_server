package kr.co.dbsg.api.api.stock.domain;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Shareholders {
    private final Map<String, Long> shareholders;
}
