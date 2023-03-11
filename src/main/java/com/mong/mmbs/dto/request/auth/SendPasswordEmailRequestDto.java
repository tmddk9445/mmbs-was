package com.mong.mmbs.dto.request.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SendPasswordEmailRequestDto {

  private String userEmail;
  
}
