package kr.co.dbsg.api.api.event.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Underwriter {
    private final int id;
    private final String name;
    private final long amount;
    private final long limit;
    private final String note;
}
