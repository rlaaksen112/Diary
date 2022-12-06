package com.spring.min.diary.Model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageCode;

    private String messageTitle;

    private String messageContent;

    private String messageWrite;

    @ManyToOne
    @JoinColumn(name = "MEMBER_CODE")
    private Member member;
}
