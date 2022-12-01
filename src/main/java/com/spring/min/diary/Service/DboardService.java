package com.spring.min.diary.Service;

import com.spring.min.diary.Model.DataNotFoundException;
import com.spring.min.diary.Model.Dboard;
import com.spring.min.diary.Model.Member;
import com.spring.min.diary.Repository.DboardRepository;
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
public class DboardService {
    private final MemberRepository memberRepository;
    private final DboardRepository dboardRepository;

    public void create(Dboard dboard, MultipartFile file, Principal principal) throws Exception {
        Dboard q = new Dboard();

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

        q.setTitle(dboard.getTitle());
        q.setContent(dboard.getContent());
        q.setCreateDate(LocalDateTime.now());
        q.setFilename(fileName);
        q.setFilepath("/files/" + fileName);
        q.setMember(member);
        this.dboardRepository.save(q);
    }

    public List<Dboard> getAllList(Principal principal){
        Optional<Member> _member = this.memberRepository.findByMemberId(principal.getName());
        if(_member.isPresent()){
            _member.get();
        }else {
            throw new DataNotFoundException("Member not found");
        }
        Member member = _member.get();
        List<Dboard> dboards = this.dboardRepository.findByMember(member);

        return dboards;
    }

    public void delete(Integer id){
        Optional<Dboard> _dboard = this.dboardRepository.findById(id);
        if(_dboard.isPresent()){
            _dboard.get();
        }else{
            throw new DataNotFoundException("dboard not found");
        }
        Dboard dboard = _dboard.get();
        this.dboardRepository.delete(dboard);
    }

    public void modify(Integer id, MultipartFile file,Dboard dboard) throws Exception {

        Optional<Dboard> _dboard = this.dboardRepository.findById(id);
        if(_dboard.isPresent()){
            _dboard.get();
        }else{
            throw new DataNotFoundException("dboard not found");
        }
        Dboard q = _dboard.get();
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";   //경로 지정

        UUID uuid = UUID.randomUUID();      //익명 생성

        String fileName = uuid + "_" + file.getOriginalFilename();  //익명+파일이름 자동으로 연결 해서 생성

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        q.setTitle(dboard.getTitle());
        q.setContent(dboard.getContent());
        q.setCreateDate(LocalDateTime.now());
        q.setFilename(fileName);
        q.setFilepath("/files/" + fileName);
        this.dboardRepository.save(q);
    }
}
