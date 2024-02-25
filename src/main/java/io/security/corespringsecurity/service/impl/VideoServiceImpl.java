package io.security.corespringsecurity.service.impl;


import io.security.corespringsecurity.domain.entity.video.Video;
import io.security.corespringsecurity.repository.crawl.VideoRepository;
import io.security.corespringsecurity.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoRepository videoRepository;
    @Override
    public void save(Video video) {
        videoRepository.save(video);
    }

    @Override
    public Video findByUrl(String videoUrl) {
        return videoRepository.findByUrl(videoUrl);
    }

    @Override
    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    @Override
    public List<Video> findByRecent5() {
        return videoRepository.findTop5ByOrderByIdDesc(PageRequest.of(0, 5));
    }


}
