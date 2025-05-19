package com.example.server_register.service.impl;

import com.example.server_register.commons.exception.ErrorMessageConstant;
import com.example.server_register.commons.exception.InvalidInputException;
import com.example.server_register.commons.suberror.ApiMessageError;
import com.example.server_register.model.Member;
import com.example.server_register.repository.MemberRepo;
import com.example.server_register.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepo memberRepo;

    @Override
    public Member checkLogin(Member member) {

        validateUsername(member.getUsername());
        validatePassword(member.getPassword());

        return memberRepo.checkAccount(member);
    }

    private void validatePassword(String password) {
        if(ObjectUtils.isEmpty(password)){
            throw new InvalidInputException(ErrorMessageConstant.BAD_REQUEST,
                    new ApiMessageError("Mật khẩu không được để trống!"));
        }
    }

    private void validateUsername(String username) {
        if(ObjectUtils.isEmpty(username)){
            throw new InvalidInputException(ErrorMessageConstant.BAD_REQUEST,
                    new ApiMessageError("Tài khoản không được để trống!"));
        }
    }
}
