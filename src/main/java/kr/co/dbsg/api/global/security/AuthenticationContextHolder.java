package kr.co.dbsg.api.global.security;

import kr.co.dbsg.api.api.member.domain.Member;

public class AuthenticationContextHolder {
    private static final ThreadLocal<Member> context = new ThreadLocal<>();

    private AuthenticationContextHolder() {}

    public static Member getContext() {
        return context.get();
    }

    public static void setContext(Member member) {
        context.set(member);
    }

    public static void clear() {
        context.remove();
    }
}
