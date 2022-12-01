package com.spring.min.diary.Service;

import com.spring.min.diary.Model.DataNotFoundException;
import com.spring.min.diary.Model.Member;
import com.spring.min.diary.Model.Text;
import com.spring.min.diary.Model.TextCreateForm;
import com.spring.min.diary.Repository.MemberRepository;
import com.spring.min.diary.Repository.TextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TextService {
    private final TextRepository textRepository;
    private final MemberRepository memberRepository;

    public void create(TextCreateForm textCreateForm , Principal principal){
        Text q = new Text();

       Optional<Member> qq = this.memberRepository.findByMemberId(principal.getName());
       if(qq.isPresent())
        qq.get();
       else {
           throw new DataNotFoundException("member not found");
       }
        q.setMainTitle(textCreateForm.getMainTitle());
        q.setMainContents(textCreateForm.getMainContents());
        q.setDailyTitle(textCreateForm.getDailyTitle());
        q.setDailyContents(textCreateForm.getDailyContents());
        q.setMember(qq.get());

        this.textRepository.save(q);
    }


    public Text getmemberId(String memberId){
        Optional<Member> _member = this.memberRepository.findByMemberId(memberId);
        if(_member.isPresent()){
            _member.get();
        }else {throw new DataNotFoundException("member not found");
        }
        Member member = _member.get();
        Optional<Text> text = this.textRepository.findByMember(member);
        if(text.isPresent()){
            text.get();
        }else{
            throw new DataNotFoundException("text not found");
        }

        return text.get();
    }
}
