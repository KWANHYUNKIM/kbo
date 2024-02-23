package io.security.corespringsecurity.domain.dto.board;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
//@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    @NotEmpty(message = "타이틀을 작성해주세요.")
    private String title;
    @NotEmpty(message = "내용을 작성해주세요.")
    private String content;

    private String category;

}
