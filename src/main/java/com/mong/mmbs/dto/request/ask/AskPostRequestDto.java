package com.mong.mmbs.dto.request.ask;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AskPostRequestDto {
  @NotBlank
	private String askWriter;
  @NotBlank
  private String askSort;
  @NotBlank
  private String askTitle;
  @NotBlank
	private String askContent;
}
