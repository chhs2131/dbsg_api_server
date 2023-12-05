package kr.co.dbsg.api.api.stock.domain;

import java.util.List;
import kr.co.dbsg.api.api.stock.domain.type.Underwriter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Underwriters {
    private final List<Underwriter> underwriterList;

    // TODO add and remove logic, 별도로 할당해주지 않으면 기본적으로 빈 리스트를 가지고 있도록 지정
}
