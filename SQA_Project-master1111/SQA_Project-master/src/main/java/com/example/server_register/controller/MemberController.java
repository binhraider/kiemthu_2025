package com.example.server_register.controller;

import com.example.server_register.model.Member;
import com.example.server_register.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/members")
@RequiredArgsConstructor
@CrossOrigin
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public Member login(@RequestBody Member member){
        return memberService.checkLogin(member);
    }

}
