package com.mong.mmbs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {
    
    private String toAddress; // 받는 이메일 주소
    private String title; // 이메일 제목
    private String message; // 이메일 내용
    private String fromAddress; // 보내는 이메일 주소

    private static final String EMAIL_TITLE = "몽몽책방 임시 비밀번호 안내 이메일입니다.";
    private static final String EMAIL_CONTENT = "안녕하세요.몽몽책방 임시 비밀번호 안내 메일입니다. "
        + "\n" + "회원님의 임시 비밀번호는 아래와 같습니다. 로그인 후 반드시 비밀번호를 변경해주세요." + "\n";
    private static final String FROM_ADDRESS = "helpringproject@gmail.com";

    public MailDto(String tmpPassword, String memberEmail) {

        this.toAddress = memberEmail;
        this.title = EMAIL_TITLE;
        this.message = EMAIL_CONTENT + tmpPassword;
        this.fromAddress = FROM_ADDRESS;

    }

}