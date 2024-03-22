package kr.co.dbsg.api.api.event.dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventRequest {
    @Min(0)
    @Parameter(description = "조회할 페이지 번호 (기본값: 0)")
    private int page = 0;

    @Min(1)
    @Max(100)
    @Parameter(description = "한 번에 표시할 검색 결과 개수 (기본값: 10, 최댓값: 100)")
    private int size = 10;

    @Parameter(description = "일정 조회 시작 일시 (형식: YYYY-MM-DD, 기본값: 오늘로부터 7일전)")
    private LocalDate startDate = LocalDate.now().minusDays(7);

    @Parameter(description = "일정 조회 종료 일시 (형식: YYYY-MM-DD, 기본값: 오늘)")
    private LocalDate endDate = LocalDate.now();
}
