package io.security.corespringsecurity.domain.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Table(name = "image")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String filepath;

}
