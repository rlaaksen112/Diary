package com.spring.min.diary.Model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Profil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer profilCode;

    @Column(length = 30 )
    private String profilName;

    @Column(length = 100)
    private String profilTalk;

    private String filename;

    private String filepath;

    @ManyToOne
    @JoinColumn(name = "MEMBER_CODE")
    private Member member;

}
