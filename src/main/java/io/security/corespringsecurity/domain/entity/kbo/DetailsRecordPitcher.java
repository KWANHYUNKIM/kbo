package io.security.corespringsecurity.domain.entity.kbo;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class DetailsRecordPitcher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private int H;
    private int doubleHit;
    private int tripleHit;
    private int HR;
    private int BB;
    private int HBP;
    private int SO;
    private int WP;
    private int BK;
    private double AVG;


}
