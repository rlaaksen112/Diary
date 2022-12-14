package com.spring.min.diary.Repository;

import com.spring.min.diary.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    //UserSecurityService는 사용자를 조회하는 서비스를 필요로 함.
    Optional<Member> findByMemberId(String memberId);


        //limit == 제한 한개만.
        // order by RAND() limit 1 == 정렬시키는데 랜덤으로 정렬 근데 리밋트 하나만
    @Query(value = "SELECT * FROM member order by RAND() limit 1",nativeQuery = true)
    List<Member> findAll();
}
