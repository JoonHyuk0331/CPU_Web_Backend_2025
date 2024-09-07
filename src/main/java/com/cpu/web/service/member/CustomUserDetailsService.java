package com.cpu.web.service.member;

import com.cpu.web.dto.member.CustomMember;
import com.cpu.web.entity.member.Member;
import com.cpu.web.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> member = memberRepository.findByUsername(username);

        if(member.isEmpty()){
            throw new UsernameNotFoundException("존재하지 않는 유저입니다: " + username);
        }

        return new CustomMember(member.get());
    }
}