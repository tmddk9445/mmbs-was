package com.mong.mmbs.dto.request.ask;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AskPatchRequestDto {

    private int askId;
    @NotBlank
    private String askWriter;

    @NotNull
    private int askSort;
    @NotBlank
    private String askTitle;
    @NotBlank
    private String askContent;
}
