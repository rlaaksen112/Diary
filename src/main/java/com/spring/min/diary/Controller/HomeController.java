package com.spring.min.diary.Controller;

import com.spring.min.diary.Model.Member;
import com.spring.min.diary.Model.Message;
import com.spring.min.diary.Service.MemberService;
import com.spring.min.diary.Service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class HomeController {
    private final MemberService memberService;
    private final MessageService messageService;

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        if (principal == null) {
            return "index";
        }

        Member memberList = this.memberService.getPrincipalId(principal);

        model.addAttribute("memberList", memberList);
        return "index";
    }

    @GetMapping("/message") //메세지 리스트
    public String messageview(Model model , Principal principal){
        List<Message> messages = this.messageService.getId(principal);

        model.addAttribute("messages",messages);

        return "Member/member_message";
    }
    @GetMapping("/message/detail/{id}") //메세지 상세
    public String messageDetail(@PathVariable("id") Integer id,Model model){
        Message message = this.messageService.getId(id);
        model.addAttribute("message",message);

        return "Member/member_message_detail";
    }

    @GetMapping("/message/form") //메세지 발송
    public String messageForm(Principal principal,String name,Message message){

        return "Member/member_message_form";
    }
    @PostMapping("/message/form") //메세지 발송
    public String messageForm2(Principal principal,String name,Message message){
        this.messageService.create(principal,name,message);
        return "Member/member_message_form";
    }

    // -----------------------------------------------------------------------------


}
