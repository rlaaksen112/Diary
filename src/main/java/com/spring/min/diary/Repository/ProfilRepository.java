package com.spring.min.diary.Repository;

import com.spring.min.diary.Model.Member;
import com.spring.min.diary.Model.Profil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfilRepository extends JpaRepository<Profil, Integer> {
    Optional<Profil> findByMember(Member member);
}
