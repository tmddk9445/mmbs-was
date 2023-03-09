package com.mong.mmbs.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mong.mmbs.dto.MailDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MailService {

  @Autowired
  JavaMailSender mailSender;

  /** 이메일 생성 **/
  public MailDto createMail(String tmpPassword, String memberEmail) {

    MailDto mailDto = null;

    mailDto = new MailDto(tmpPassword, memberEmail);

    log.info("메일 생성 완료");

    return mailDto;
  }

  /** 이메일 전송 **/
  public void sendMail(MailDto mailDto) {
    // MailMessage mailMessage = new MailMessage(mailDto);
    // mailSender.send(mailMessage);
  }

}
