package com.spring.min.diary.Repository;

import com.spring.min.diary.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    //UserSecurityService는 사용자를 조회하는 서비스를 필요로 함.
    Optional<Member> findByMemberId(String memberId);
}
