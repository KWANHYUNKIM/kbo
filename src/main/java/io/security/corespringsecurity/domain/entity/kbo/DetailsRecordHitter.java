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

// Todo: 상황별 기록 나중에 구현
public class DetailsRecordHitter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private double AVG;
    private int AB;
    private int H;
    private int doubleHit;
    private int tripleHit;
    private int HR;
    private int RBI;
    private int BB;
    private int HBP;
    private int SO;
    private int GDP;

}
