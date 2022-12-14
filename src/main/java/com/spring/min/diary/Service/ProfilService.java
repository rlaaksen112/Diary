package com.spring.min.diary.Service;

import com.spring.min.diary.Model.DataNotFoundException;
import com.spring.min.diary.Model.Member;
import com.spring.min.diary.Model.Profil;
import com.spring.min.diary.Repository.MemberRepository;
import com.spring.min.diary.Repository.ProfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProfilService {
    private final ProfilRepository profilRepository;
    private final MemberRepository memberRepository;

    public void create(String a , String b , MultipartFile file, Principal principal)throws Exception{
        Profil q = new Profil();
        Optional<Member> _member = this.memberRepository.findByMemberId(principal.getName());
        if(_member.isPresent()){
            _member.get();
        }else {
            throw new DataNotFoundException("member not found");
        }
        Member member = _member.get();

        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";   //경로 지정
        UUID uuid = UUID.randomUUID();      //익명 생성

        String fileName = uuid + "_" + file.getOriginalFilename();  //익명+파일이름 자동으로 연결 해서 생성

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        q.setMember(member);
        q.setProfilName(a);
        q.setProfilTalk(b);
        q.setFilename(fileName);
        q.setFilepath("/files/" + fileName);

        this.profilRepository.save(q);
    }

    public Profil getAll(Principal principal){
        Optional<Member> _member = this.memberRepository.findByMemberId(principal.getName());
        if(_member.isPresent()){
            _member.get();
        }else {
            throw new DataNotFoundException("member not found");
        }
        Member member = _member.get();
        Optional<Profil> _profil = this.profilRepository.findByMember(member);
        if(_profil.isPresent()){
            _profil.get();
        }else{
            throw new DataNotFoundException("profil not found");
        }
        Profil profil = _profil.get();
        return profil;
    }

    public void modify(Profil profil , MultipartFile file, Principal principal)throws Exception{

        Optional<Member> _member = this.memberRepository.findByMemberId(principal.getName());
        if(_member.isPresent()){
            _member.get();
        }else {
            throw new DataNotFoundException("member not found");
        }
        Member member = _member.get();

        Optional<Profil> profil1 = this.profilRepository.findByMember(member);
        if(profil1.isPresent()){
            profil1.get();
        }else{
            throw new DataNotFoundException("Profil not found");
        }
        Profil q = profil1.get();
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";   //경로 지정
        UUID uuid = UUID.randomUUID();      //익명 생성

        String fileName = uuid + "_" + file.getOriginalFilename();  //익명+파일이름 자동으로 연결 해서 생성

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        q.setProfilName(profil.getProfilName());
        q.setProfilTalk(profil.getProfilTalk());
        q.setFilename(fileName);
        q.setFilepath("/files/" + fileName);

        this.profilRepository.save(q);
    }



}
