package io.security.corespringsecurity.controller.entertainment;


import io.security.corespringsecurity.domain.entity.video.Video;
import io.security.corespringsecurity.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class VideoController {
    @Autowired
    private VideoService videoService;

    @GetMapping(value = "/youtube/detail")
    public String youtubeDetails(Model model){
        List<Video> videoList = videoService.findAll();
        model.addAttribute("videoList",videoList);
        return "entertainment/youtubeDetails";
    }

    @GetMapping(value = "/instgram/detail")
    public String instagramDetails(Model model){
        return "";
    }

}
