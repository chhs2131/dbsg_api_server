package kr.co.dbsg.api.global.util;

import org.apache.commons.text.StringEscapeUtils;

public class HtmlCharEntityConverter {
    public static String unescape(String input) {
        String result = input.replaceAll("<[^>]*>", ""); // HTML 태그 제거
        return StringEscapeUtils.unescapeHtml4(result);
    }

    public static String replace(String input) {
        return unescape(input);
    }

    public static String convert(String input) {
        return unescape(input);
    }
}
