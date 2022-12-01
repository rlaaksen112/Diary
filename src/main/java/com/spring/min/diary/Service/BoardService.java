package com.spring.min.diary.Service;


import com.spring.min.diary.Model.Board;
import com.spring.min.diary.Model.DataNotFoundException;
import com.spring.min.diary.Model.Member;
import com.spring.min.diary.Repository.BoardRepository;
import com.spring.min.diary.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public void create(Board board, MultipartFile file, Principal principal) throws Exception {
        Board q = new Board();
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";   //경로 지정

        UUID uuid = UUID.randomUUID();      //익명 생성

        String fileName = uuid + "_" + file.getOriginalFilename();  //익명+파일이름 자동으로 연결 해서 생성

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        Optional<Member> _member = this.memberRepository.findByMemberId(principal.getName());
        if (_member.isPresent()) {
            _member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }

        Member member = _member.get();

        q.setTitle(board.getTitle());
        q.setContent(board.getContent());
        q.setCreateDate(LocalDateTime.now());
        q.setFilename(fileName);
        q.setFilepath("/files/" + fileName);
        q.setMember(member);
        this.boardRepository.save(q);
    }

    public void create2(MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4, MultipartFile file5, MultipartFile file6, Principal principal) throws Exception {
        Optional<Member> _member = this.memberRepository.findByMemberId(principal.getName());
        if (_member.isPresent()) {
            _member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }
        Member member = _member.get();

        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";
        UUID uuid = UUID.randomUUID();      //익명 생성

        Board q1 = new Board();
        Board q2 = new Board();
        Board q3 = new Board();
        Board q4 = new Board();
        Board q5 = new Board();
        Board q6 = new Board();

        String fileName1 = uuid + "_" + file1.getOriginalFilename();  //file1
        File saveFile1 = new File(projectPath, fileName1);
        file1.transferTo(saveFile1);

        q1.setTitle("1");
        q1.setContent("1");
        q1.setCreateDate(LocalDateTime.now());
        q1.setFilename(fileName1);
        q1.setFilepath("/files/" + fileName1);
        q1.setMember(member);
        this.boardRepository.save(q1);

        String fileName2 = uuid + "_" + file2.getOriginalFilename();  //file1
        File saveFile2 = new File(projectPath, fileName2);
        file2.transferTo(saveFile2);

        q2.setTitle("2");
        q2.setContent("2");
        q2.setCreateDate(LocalDateTime.now());
        q2.setFilename(fileName2);
        q2.setFilepath("/files/" + fileName2);
        q2.setMember(member);
        this.boardRepository.save(q2);

        String fileName3 = uuid + "_" + file3.getOriginalFilename();  //file1
        File saveFile3 = new File(projectPath, fileName3);
        file3.transferTo(saveFile3);

        q3.setTitle("3");
        q3.setContent("3");
        q3.setCreateDate(LocalDateTime.now());
        q3.setFilename(fileName3);
        q3.setFilepath("/files/" + fileName3);
        q3.setMember(member);
        this.boardRepository.save(q3);

        String fileName4 = uuid + "_" + file4.getOriginalFilename();  //file1
        File saveFile4 = new File(projectPath, fileName4);
        file4.transferTo(saveFile4);

        q4.setTitle("4");
        q4.setContent("4");
        q4.setCreateDate(LocalDateTime.now());
        q4.setFilename(fileName4);
        q4.setFilepath("/files/" + fileName4);
        q4.setMember(member);
        this.boardRepository.save(q4);

        String fileName5 = uuid + "_" + file5.getOriginalFilename();  //file1
        File saveFile5 = new File(projectPath, fileName5);
        file5.transferTo(saveFile5);

        q5.setTitle("5");
        q5.setContent("5");
        q5.setCreateDate(LocalDateTime.now());
        q5.setFilename(fileName5);
        q5.setFilepath("/files/" + fileName5);
        q5.setMember(member);
        this.boardRepository.save(q5);

        String fileName6 = uuid + "_" + file6.getOriginalFilename();  //file1
        File saveFile6 = new File(projectPath, fileName6);
        file6.transferTo(saveFile6);

        q6.setTitle("6");
        q6.setContent("6");
        q6.setCreateDate(LocalDateTime.now());
        q6.setFilename(fileName6);
        q6.setFilepath("/files/" + fileName6);
        q6.setMember(member);
        this.boardRepository.save(q6);
    }


public Board getfindIdAndTitle(String a,Principal principal) {      //피드 1~6
    Optional<Member> _member = this.memberRepository.findByMemberId(principal.getName());
    if(_member.isPresent()){
        _member.get();
    }else{
        throw new DataNotFoundException("member not found");
    }
    Member member = _member.get();

    Optional<Board> q = this.boardRepository.findByTitleAndMember(a,member);
    System.out.println("@@@@@@@@@"+ q.toString());
    Board board = q.get();
    return board;
}

    public List<Board> getAllList(String memberId) {
        Optional<Member> _member = this.memberRepository.findByMemberId(memberId);
        if (_member.isPresent()) {
            _member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }
        Member member = _member.get();
        List<Board> q = this.boardRepository.findByMemberLike(member);
        if (q == null) {
            throw new DataNotFoundException("board not found");
        }
        return q;
    }


}
