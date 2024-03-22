package kr.co.dbsg.api.api.event.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import kr.co.dbsg.api.api.event.domain.type.EventStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventScheduleTest {
    @Test
    @DisplayName("스케줄 일정 규칙 위반 - 예외")
    void abnormal_schedule_exception() {

    }

    @Test
    @DisplayName("일정에 따라 상태정보 주는 테스트 - 정상")
    void normal_schedule_status_return() {
        final EventSchedule eventSchedule = new EventSchedule(
            LocalDate.now().minusDays(10),
            LocalDate.now().minusDays(8),
            LocalDate.now().minusDays(6),
            LocalDate.now().minusDays(4),
            LocalDate.now().minusDays(2),
            LocalDate.now(),
            null
        );

        final EventStatus status = eventSchedule.getStatus();
        assertThat(status).isEqualTo(EventStatus.NOW_DEBUT);
    }

    @Test
    @DisplayName("취소 일정이 존재하는 경우 - 정상")
    public void test_eventCancel_not_null() {
        EventSchedule eventSchedule = new EventSchedule(
            LocalDate.now().minusDays(10),
            LocalDate.now().minusDays(8),
            LocalDate.now().minusDays(6),
            LocalDate.now().minusDays(4),
            LocalDate.now().minusDays(2),
            LocalDate.now(),
            LocalDate.now().plusDays(1)
        );

        EventStatus status = eventSchedule.getStatus();
        assertEquals(EventStatus.CANCEL, status);
    }

    @Test
    @DisplayName("모든 스케줄이 null인 경우 - 정상")
    public void test_all_dates_null() {
        EventSchedule eventSchedule = new EventSchedule(
            null,
            null,
            null,
            null,
            null,
            null,
            null
        );

        EventStatus status = eventSchedule.getStatus();
        assertEquals(EventStatus.DRAFTING_REPORT, status);
    }
}
