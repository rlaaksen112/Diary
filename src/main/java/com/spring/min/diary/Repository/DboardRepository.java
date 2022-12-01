package com.spring.min.diary.Repository;

import com.spring.min.diary.Model.Dboard;
import com.spring.min.diary.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DboardRepository extends JpaRepository<Dboard,Integer> {

    List<Dboard> findByMember(Member member);

}
