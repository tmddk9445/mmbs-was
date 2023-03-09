package com.mong.mmbs.common.util;

import org.springframework.mail.SimpleMailMessage;

import com.mong.mmbs.dto.MailDto;

public class MailMessage extends SimpleMailMessage {
    public MailMessage(MailDto mailDto) {
        super.setTo(mailDto.getToAddress());
        super.setSubject(mailDto.getTitle());
        super.setText(mailDto.getMessage());
        super.setFrom(mailDto.getFromAddress());
        super.setReplyTo(mailDto.getFromAddress());
    }
}
