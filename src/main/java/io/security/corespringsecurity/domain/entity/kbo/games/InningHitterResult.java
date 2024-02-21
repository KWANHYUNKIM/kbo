package io.security.corespringsecurity.domain.entity.kbo.games;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class InningHitterResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int inning;
    private String result;

    public InningHitterResult(int inning, String result) {
        this.inning = inning;
        this.result = result;
    }
}
