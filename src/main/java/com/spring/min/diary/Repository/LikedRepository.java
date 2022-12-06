package com.spring.min.diary.Repository;

import com.spring.min.diary.Model.Liked;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedRepository extends JpaRepository<Liked,Integer> {
}
