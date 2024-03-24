package kr.co.dbsg.api.api.event.domain.type;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventPriceTest {
    @Test
    @DisplayName("모든 공모가는 0보다 커야합니다.")
    void every_value_more_than_zero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new EventPrice(0, 10, 30);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new EventPrice(10, 0, 30);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new EventPrice(10, 10, 0);
        });
    }

    @Test
    @DisplayName("모든 가격값이 null일 수 있습니다")
    void all_properties_nullable() {
        final EventPrice eventPrice = new EventPrice(null, null, null);

        assertThat(eventPrice.fixed()).isNull();
    }

    @Test
    @DisplayName("정상적으로 생성된 경우")
    public void test_initialize_with_valid_values() {
        EventPrice eventPrice = new EventPrice(10, 20, 30);

        assertEquals(10, eventPrice.bandLow());
        assertEquals(20, eventPrice.bandHigh());
        assertEquals(30, eventPrice.fixed());
    }

    @Test
    @DisplayName("공모가 상단은 하단보다 작을 수 없습니다")
    public void test_band_high_less_than_band_low() {
        assertThrows(IllegalArgumentException.class, () -> {
            new EventPrice(20, 10, 30);
        });
    }
}