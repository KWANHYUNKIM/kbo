package io.security.corespringsecurity.domain.dto.kbo.games;

import io.security.corespringsecurity.domain.entity.kbo.Teams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamsDto {
    private long id;
    private String teamName;

    private String filename;
    private String filepath;

    public Teams toEntity(){
        return Teams.builder()
                .teamName(this.teamName)
                .filename(this.filename)
                .filepath(this.filepath)
                .build();
    }

}
