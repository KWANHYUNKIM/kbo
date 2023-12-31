package io.security.corespringsecurity.domain.entity.kbo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Getter
@AllArgsConstructor
@Entity
@ToString
public class KboSchedule {
    @Id
    @GeneratedValue
    @Column(name ="schedule_id")
    private long id;

}
