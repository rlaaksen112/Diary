package com.spring.min.diary.Model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class MemberCreateForm {

    @Size(min = 1,max = 30, message = "Id는 1 ~ 30자 이여야 합니다!")
    @NotEmpty(message="사용자 아이디")
    private String memberId;

    @Size(min = 1,max = 30, message = "비밀번호는 1 ~ 30자 이여야 합니다!")
    @NotEmpty(message="패스워드")
    private String memberPw;

    @Size(min = 1,max = 30, message = "비밀번호는 1 ~ 30자 이여야 합니다!")
    @NotEmpty(message="패스워드 확인")
    private String memberPw2;

    @Size(min = 1,max = 30, message = "이름은 1 ~ 30자 이여야 합니다!")
    @NotEmpty(message="사용자 이름")
    private String memberName;

    @Size(min = 1,max = 30, message = "핸드폰 번호는 1 ~ 30자 이여야 합니다!")
    @NotEmpty(message="사용자 핸드폰 번호")
    private String memberPhone;

    @Size(min = 1,max = 70, message = "이메일은 1 ~ 30자 이여야 합니다!")
    @Email
    @NotEmpty(message="사용자 이메일")
    private String memberEmail;

    @Size(min = 1,max = 30, message = "생년월일은 1 ~ 30자 이여야 합니다!")
    @NotEmpty(message="사용자 생년월일")
    private String memberBirth;

}



