package com.mong.mmbs.dto.response.ask;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AskPatchResponseDto {
    private int askId;
//  작성자 아이디 (참조)
	private String askWriter;
//  문의 카테고리
	private String askSort;
// 	문의 제목
	private String askTitle;
//  문의 내용
	private String askContent;
//  문의 날짜
	private String askDatetime;
//  문의 상태
	private String askStatus;
//  문의 답변
    private String askReply;
}
