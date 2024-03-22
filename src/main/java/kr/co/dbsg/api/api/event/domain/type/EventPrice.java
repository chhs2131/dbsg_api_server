package kr.co.dbsg.api.api.event.domain.type;

import jakarta.annotation.Nullable;
import org.springframework.util.Assert;

public record EventPrice(
        @Nullable
        Integer bandLow,
        @Nullable
        Integer bandHigh,
        @Nullable
        Integer fixed
) {
    public EventPrice(final Integer bandLow, final Integer bandHigh, final Integer fixed) {
        if (fixed != null) {
            Assert.isTrue(fixed > 0, "확정공모가는 0보다 커야합니다.");
        }
        if (bandLow != null) {
            Assert.isTrue(bandLow > 0, "공모가 하단은 0보다 커야합니다.");
        }
        if (bandHigh != null) {
            Assert.isTrue(bandHigh > 0, "공모가 상단은 0보다 커야합니다.");
        }
        Assert.isTrue(validateBand(bandLow, bandHigh), "공모가 상단이 공모가 하단보다 크거나 같아야 합니다. (현재값: " + bandLow + " ~ " + bandHigh + ")");

        this.bandLow = bandLow;
        this.bandHigh = bandHigh;
        this.fixed = fixed;
    }

    private boolean validateBand(Integer bandLow, Integer bandHigh) {
        if (bandLow == null || bandHigh == null) return true;
        return bandLow <= bandHigh;
    }
}
