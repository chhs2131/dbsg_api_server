package kr.co.dbsg.api.global.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RandomNameMakerTest {

    @Test
    public void 닉네임생성_성공() {
        for (int i = 0; i < 20; i++ ) {
            final String generate = RandomNameMaker.generate(8);
            System.out.println(generate);
        }
    }

    @Test
    public void 닉네임생성_실패() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            final String generate = RandomNameMaker.generate(1);
            System.out.println(generate);
        });
    }
}