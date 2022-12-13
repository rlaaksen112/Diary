package com.spring.min.diary.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberCode;

    @Column(length = 30, unique = true)
    private String memberId;

    @Column(length = 60)
    private String memberPw;

    @Column(length = 30)
    private String memberName;

    @Column(length = 30, unique = true)
    private String memberPhone;

    @Column(length = 320, unique = true)
    private String memberEmail;

    @Column(length = 30, unique = true)
    private String memberBirth;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Board> boards;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Text> texts;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Profil> profils;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Liked> likes;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Message> messages;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Follow> follows;
}
