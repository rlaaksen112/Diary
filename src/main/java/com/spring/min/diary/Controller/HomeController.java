package com.spring.min.diary.Controller;

import com.spring.min.diary.Model.Member;
import com.spring.min.diary.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@RequiredArgsConstructor
@Controller
public class HomeController {
    private final MemberService memberService;

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        if (principal == null) {
            return "index";
        }

        Member memberList = this.memberService.getPrincipalId(principal);

        model.addAttribute("memberList", memberList);
        return "index";
    }
    // -----------------------------------------------------------------------------


}
