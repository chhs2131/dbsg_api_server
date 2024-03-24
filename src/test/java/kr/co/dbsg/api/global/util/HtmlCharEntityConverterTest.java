package kr.co.dbsg.api.global.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HtmlCharEntityConverterTest {
    @Test
    void HTML문자가_정상적으로_제거되나() {
        String html = "안녕하세요&nbsp;&nbsp;바삭입니다.<br>오늘도&nbsp;좋은&nbsp;하루&nbsp;되세요! &quot;<p>행복하세요!</p>&quot; &#39;네, 행복합니다!&#39; &lt;사랑합니다&gt; &amp; 감사합니다.";

        String result = HtmlCharEntityConverter.unescape(html);

        String expected = "안녕하세요  바삭입니다.오늘도 좋은 하루 되세요! \"행복하세요!\" '네, 행복합니다!' <사랑합니다> & 감사합니다.";
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
