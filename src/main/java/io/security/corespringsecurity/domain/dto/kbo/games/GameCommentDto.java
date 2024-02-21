package io.security.corespringsecurity.domain.dto.kbo.games;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameCommentDto {
    private String comment;
}
