//package com.example.server_register.config.security;
//
//import com.example.server_register.model.Member;
//import com.example.server_register.repository.MemberRepo;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class UserInfoDetailsService implements UserDetailsService {
//
//    private final MemberRepo memberRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Member> member = Optional.ofNullable(memberRepo.findByUsername(username));
//        return member.map(UserInfoDetails::new)
//                .orElseThrow(()-> new UsernameNotFoundException("user not found"));
//    }
//}
