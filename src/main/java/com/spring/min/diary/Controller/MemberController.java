package com.spring.min.diary.Controller;


import com.spring.min.diary.Model.*;
import com.spring.min.diary.Service.BoardService;
import com.spring.min.diary.Service.MemberService;
import com.spring.min.diary.Service.TextService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {
    private final MemberService memberService;
    private final TextService textService;
    private final BoardService boardService;


    @GetMapping("/create")  //회원가입 페이지
    public String MemberCreate(MemberCreateForm memberCreateForm) {

        return "member_create";
    }

    @PostMapping("/create") //회원가입 정보 전송
    public String MemberCreate2(@Valid MemberCreateForm memberCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member_create";
        }
        if (!memberCreateForm.getMemberPw().equals(memberCreateForm.getMemberPw2())) {
            bindingResult.rejectValue("memberPw2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
            return "member_create";
        }

        try {
            this.memberService.create(memberCreateForm.getMemberId(), memberCreateForm.getMemberPw(), memberCreateForm.getMemberName(),
                    memberCreateForm.getMemberBirth(), memberCreateForm.getMemberEmail(), memberCreateForm.getMemberPhone());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "member_create";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "member_create";
        }

        return "redirect:/";
    }
    // -----------------------------------------------------------------------------
    @GetMapping("/login")   //로그인 페이지
    public String login() {
        return "login_form";
    }

    // -----------------------------------------------------------------------------
    @GetMapping("/myhome/{memberId}")   //마이홈
    public String myhome(@PathVariable("memberId") String memberId,Model model,Principal principal) {
        Text text = this.textService.getmemberId(memberId); //메인,데일리 타이틀 및 내용
        model.addAttribute("text",text);

        Member memberList = this.memberService.getPrincipalId(principal); //네비바 정보
        model.addAttribute("memberList",memberList);

        Board board1 = this.boardService.getfindIdAndTitle("1",memberId); //피드 사진
        Board board2 = this.boardService.getfindIdAndTitle("2",memberId);
        Board board3 = this.boardService.getfindIdAndTitle("3",memberId);
        Board board4 = this.boardService.getfindIdAndTitle("4",memberId);
        Board board5 = this.boardService.getfindIdAndTitle("5",memberId);
        Board board6 = this.boardService.getfindIdAndTitle("6",memberId);
        model.addAttribute("board1", board1);
        model.addAttribute("board2", board2);
        model.addAttribute("board3", board3);
        model.addAttribute("board4", board4);
        model.addAttribute("board5", board5);
        model.addAttribute("board6", board6);
        return "member_myhome";
    }
    // -----------------------------------------------------------------------------
    @GetMapping("/new/edit")        //최초 홈페이지 생성
    public String edit(TextCreateForm textCreateForm, Principal principal) {
        if (principal == null) {
            return "redirect:/member/login";
        }

        return "member_newedit";
    }

    @PostMapping("/new/edit")
    public String editForm(@Valid TextCreateForm textCreateForm, Principal principal, BindingResult bindingResult) {
        if (principal == null) {
            return "redirect:/member/login";
        }
        if (bindingResult.hasErrors()) {
            return "member_newedit";
        }

        this.textService.create(textCreateForm, principal);

        return "redirect:/member/new/board";
    }

    @GetMapping("/new/board")
    public String newboard(MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4, MultipartFile file5, MultipartFile file6,Principal principal){

        return "member_board2";
    }

    @PostMapping("/new/board")
    public String newboard2(MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4, MultipartFile file5, MultipartFile file6,Principal principal) throws Exception {

        this.boardService.create2(file1,file2,file3,file4,file5,file6,principal);

        return "redirect:/";
    }
    // -----------------------------------------------------------------------------
    @GetMapping("/boardlist/{memberId}")    //게시물 리스트
    public String boardList(@PathVariable("memberId") String memberId,Model model){
        List<Board> boardList = this.boardService.getAllList(memberId);
        model.addAttribute("boardList",boardList);

        return "member_boardlist";
    }
    // -----------------------------------------------------------------------------
    @GetMapping("/board")
    public String board(Board board){

        return "member_board";
    }
    @PostMapping("/board")
    public String boardForm(Board board, MultipartFile file,Principal principal) throws  Exception{

        this.boardService.create(board,file,principal);

        return "member_board";
    }
    // -----------------------------------------------------------------------------
}
