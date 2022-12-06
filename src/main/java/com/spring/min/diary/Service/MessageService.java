package com.spring.min.diary.Service;

import com.spring.min.diary.Model.DataNotFoundException;
import com.spring.min.diary.Model.Member;
import com.spring.min.diary.Model.Message;
import com.spring.min.diary.Repository.MemberRepository;
import com.spring.min.diary.Repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;

    public List<Message> getId(Principal principal){
        Optional<Member> _member = this.memberRepository.findByMemberId(principal.getName());
        if(_member.isPresent()){
            _member.get();
        }else{
            throw new DataNotFoundException("Member Not Found");
        }
        Member member = _member.get();

        List<Message> messages = this.messageRepository.findByMember(member);
        return messages;
    }

    public void create(Principal principal,String name,Message message){
        Optional<Member> _member = this.memberRepository.findByMemberId(name);
        if(_member.isPresent()){
            _member.get();
        }else{
            throw new DataNotFoundException("Member not found");
        }
        Member member = _member.get();

        Message q = new Message();
        q.setMember(member);
        q.setMessageWrite(principal.getName());
        q.setMessageTitle(message.getMessageTitle());
        q.setMessageContent(message.getMessageContent());
        q.setCreateDate(LocalDateTime.now());
        this.messageRepository.save(q);
    }

    public Message getId(Integer id){
        Optional<Message> _message = this.messageRepository.findById(id);
        if(_message.isPresent()){
            _message.get();
        }else{
            throw new DataNotFoundException("Message not found");
        }
        Message message = _message.get();
        return message;
    }

}
