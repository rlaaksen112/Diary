package com.spring.min.diary.Model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Text {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer textCode;

    @Column(length = 30)
    private String mainTitle;

    @Column(length = 300)
    private String mainContents;

    @Column(length = 30)
    private String dailyTitle;

    @Column(length = 300)
    private String dailyContents;

    @ManyToOne
    @JoinColumn(name = "MEMBER_CODE")
    private Member member;

}
