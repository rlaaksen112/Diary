package com.spring.min.diary.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
public class Liked {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;


    private Integer thisLike;

    @ManyToOne
    @JoinColumn(name = "MEMBER_CODE")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "DBOARD_CODE")
    private Dboard dboard;
}
