package com.mong.mmbs.dto.request.auth;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindPasswordRequestDto {
  @NotBlank
  private String userId;
  @NotBlank
  private String userName;
  @NotBlank
  private String userEmail;
}
