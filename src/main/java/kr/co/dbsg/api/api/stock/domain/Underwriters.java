package kr.co.dbsg.api.api.stock.domain;

import java.util.List;
import kr.co.dbsg.api.api.stock.domain.type.Underwriter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Underwriters {
    private final List<Underwriter> underwriters;
}
