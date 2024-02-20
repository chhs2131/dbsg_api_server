package kr.co.dbsg.api.global.util;

import org.springframework.web.util.HtmlUtils;

public class HtmlCharEntityConverter {
    public static String unescape(String input) {
        String result = input.replaceAll("<[^>]*>", ""); // HTML 태그 제거
        result = result.replaceAll("&nbsp;", " ");  // &nbsp; 전처리
        return HtmlUtils.htmlUnescape(result);
    }

    public static String replace(String input) {
        return unescape(input);
    }

    public static String convert(String input) {
        return unescape(input);
    }
}
