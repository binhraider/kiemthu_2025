package com.example.server_register.service;

import com.example.server_register.model.Member;

public interface MemberService {

    Member checkLogin(Member member);
}
