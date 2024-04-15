package kr.co.dbsg.api.api.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/members")
public class MemberController {
    @GetMapping("/me")
    public void me() {
    }

    @GetMapping("/{id}")
    public void getMember() {
    }
}
