package com.spring.min.diary.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class Dboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String title;

    private String content;

    private String filename;

    private String filepath;

    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "MEMBER_CODE")
    private Member member;

    @OneToMany(mappedBy = "dboard")
    private List<Liked> likes;
}
