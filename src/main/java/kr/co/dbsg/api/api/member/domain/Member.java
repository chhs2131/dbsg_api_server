package kr.co.dbsg.api.api.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Member {
    private final long id;
    private final String name;
}
