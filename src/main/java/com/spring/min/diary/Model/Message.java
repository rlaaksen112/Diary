package com.spring.min.diary.Model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String messageTitle;

    private String messageContent;

    private String messageWrite;

    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "MEMBER_CODE")
    private Member member;
}
