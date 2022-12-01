package com.spring.min.diary.Controller;


import com.spring.min.diary.Model.*;
import com.spring.min.diary.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.parameters.P;
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
    private final ProfilService profilService;
    private final DboardService dboardService;

    // -----------------------------------------------------------------------------
    // 회원가입 및 로그인
    @GetMapping("/create")  //회원가입 페이지
    public String MemberCreate(MemberCreateForm memberCreateForm) {

        return "Member/member_create";
    }

    @PostMapping("/create") //회원가입 정보 전송
    public String MemberCreate2(@Valid MemberCreateForm memberCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Member/member_create";
        }
        if (!memberCreateForm.getMemberPw().equals(memberCreateForm.getMemberPw2())) {
            bindingResult.rejectValue("memberPw2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
            return "Member/member_create";
        }

        try {
            this.memberService.create(memberCreateForm.getMemberId(), memberCreateForm.getMemberPw(), memberCreateForm.getMemberName(),
                    memberCreateForm.getMemberBirth(), memberCreateForm.getMemberEmail(), memberCreateForm.getMemberPhone());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "Member/member_create";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "Member/member_create";
        }

        return "redirect:/";
    }

    @GetMapping("/login")   //로그인 페이지
    public String login() {
        return "Member/login_form";
    }



    // -----------------------------------------------------------------------------
    //마이홈
    @GetMapping("/myhome")
    public String myhome(Model model,Principal principal) {
        Text text = this.textService.getmemberId(principal); //메인,데일리 타이틀 및 내용
        model.addAttribute("text",text);

        Board board1 = this.boardService.getfindIdAndTitle("1",principal); //피드 사진
        Board board2 = this.boardService.getfindIdAndTitle("2",principal);
        Board board3 = this.boardService.getfindIdAndTitle("3",principal);
        Board board4 = this.boardService.getfindIdAndTitle("4",principal);
        Board board5 = this.boardService.getfindIdAndTitle("5",principal);
        Board board6 = this.boardService.getfindIdAndTitle("6",principal);
        model.addAttribute("board1", board1);
        model.addAttribute("board2", board2);
        model.addAttribute("board3", board3);
        model.addAttribute("board4", board4);
        model.addAttribute("board5", board5);
        model.addAttribute("board6", board6);

        Profil profil = this.profilService.getAll(principal);
        model.addAttribute("profil",profil);
        return "Member/member_myhome";
    }
    // -----------------------------------------------------------------------------
    //최초 홈페이지 생성
    @GetMapping("/new/edit")
    public String edit(TextCreateForm textCreateForm, Principal principal) {
        if (principal == null) {
            return "redirect:/member/login";
        }

        return "Member/member_newedit";
    }

    @PostMapping("/new/edit")   //문구 등록
    public String editForm(@Valid TextCreateForm textCreateForm, Principal principal, BindingResult bindingResult) {
        if (principal == null) {
            return "redirect:/member/login";
        }
        if (bindingResult.hasErrors()) {
            return "Member/member_newedit";
        }

        this.textService.create(textCreateForm, principal);

        return "redirect:/member/new/board";
    }

    @GetMapping("/new/board")   //피드 등록
    public String newboard(MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4, MultipartFile file5, MultipartFile file6,Principal principal){

        return "Member/member_board2";
    }

    @PostMapping("/new/board")  //피드 등록
    public String newboard2(MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4, MultipartFile file5, MultipartFile file6,Principal principal) throws Exception {

        this.boardService.create2(file1,file2,file3,file4,file5,file6,principal);

        return "redirect:/member/new/profil";
    }

    @GetMapping("/new/profil")  //프로필 등록
    public String newprofil(Principal principal){

        return "Member/member_profil";
    }
    @PostMapping("/new/profil") //프로필 등록
    public String newprofil2(MultipartFile file,String profilName,String profilTalk, Principal principal)throws Exception{

        this.profilService.create(profilName, profilTalk , file , principal);

        return "redirect:/";
    }



    // -----------------------------------------------------------------------------
    // 데일리 보드
    @GetMapping("/boardlist")    //데일리 보드 리스트
    public String boardList(Model model,Principal principal){
        List<Dboard> boardList = this.dboardService.getAllList(principal);
        model.addAttribute("boardList",boardList);

        return "Member/member_boardlist";
    }
    @GetMapping("/board")   //데일리보드 등록 폼
    public String board(Board board){

        return "Member/member_board";
    }
    @PostMapping("/board")
    public String boardForm(Dboard dboard, MultipartFile file,Principal principal) throws  Exception{

        this.dboardService.create(dboard,file,principal);

        return "Member/member_board";
    }
//    @GetMapping("/board/modify/{id}")   //게시물 수정
//    public String modify(@PathVariable("id") Integer id , Principal principal,Dboard dboard){
//        return "Member/member_dboard_modify";
//    }
//    @PostMapping("/board/modify/{id}")
//    public String modify2(@PathVariable("id") Integer id , MultipartFile file,Dboard dboard)throws Exception{
//        this.dboardService.modify(id,file,dboard);
//        return "redirect:/member/boardlist";
//    }

    @GetMapping("/board/delete/{id}")
    public String delete(@PathVariable("id") Integer id,Principal principal){
        this.dboardService.delete(id);
        return "redirect:/member/boardlist";
    }
    // -----------------------------------------------------------------------------
}
