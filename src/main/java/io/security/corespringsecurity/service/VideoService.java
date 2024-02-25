package io.security.corespringsecurity.service;

import io.security.corespringsecurity.domain.entity.video.Video;

import java.util.List;

public interface VideoService {

    void save(Video video);

    Video findByUrl(String videoUrl);

    List<Video> findAll();

    List<Video> findByRecent5();
}
