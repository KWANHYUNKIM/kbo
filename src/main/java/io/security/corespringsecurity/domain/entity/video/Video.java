package io.security.corespringsecurity.domain.entity.video;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name ="video_id")
    private Long id;

    private String filepath;

    private String filename;

    private String date;

    private String title;

    private String playUrl;

    private String keyword;

    private String type; // youtube, kborc(비디오판독)
}
