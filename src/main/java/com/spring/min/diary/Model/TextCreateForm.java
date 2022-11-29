package com.spring.min.diary.Model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TextCreateForm {

    @Size(min = 1,max = 30, message = "메인 제목은 1~30자 이내 이어야 합니다.")
    @NotEmpty(message="메인 타이틀")
    private String mainTitle;

    @Size(min = 1,max = 300, message = "메인 내용은 1~300자 이내 이어야 합니다.")
    @NotEmpty(message="메인 텍스트")
    private String mainContents;

    @Size(min = 1,max = 30, message = "데일리 제목은 1~30자 이내 이어야 합니다.")
    @NotEmpty(message="데일리 타이틀")
    private String dailyTitle;

    @Size(min = 1,max = 300, message = "데일리 내용은 1~300자 이내 이어야 합니다.")
    @NotEmpty(message="데일리 내용")
    private String dailyContents;
}
