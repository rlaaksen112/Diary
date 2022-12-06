package com.spring.min.diary.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String follower; //팔로워

    private String following; //팔로잉

    @ManyToOne
    @JoinColumn(name = "MEMBER_CODE")
    private Member member;
}
