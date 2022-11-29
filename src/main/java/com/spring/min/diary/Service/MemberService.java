package com.spring.min.diary.Service;

import com.spring.min.diary.Model.DataNotFoundException;
import com.spring.min.diary.Model.Member;
import com.spring.min.diary.Model.MemberCreateForm;
import com.spring.min.diary.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void create(String a, String b, String c, String d, String e, String f) { //회원가입
        Member q = new Member();

        q.setMemberId(a);
        q.setMemberPw(passwordEncoder.encode(b));
        q.setMemberName(c);
        q.setMemberBirth(d);
        q.setMemberEmail(e);
        q.setMemberPhone(f);

        this.memberRepository.save(q);
    }

    public Member getPrincipalId(Principal principal) {
        Optional<Member> a = this.memberRepository.findByMemberId(principal.getName());
        if (a.isPresent()) {
            a.get();
        } else {
            throw new DataNotFoundException("member not found");
        }

        Member member = a.get();

        return member;
    }

}
