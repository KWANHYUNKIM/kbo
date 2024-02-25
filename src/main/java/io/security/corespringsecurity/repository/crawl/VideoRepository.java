package io.security.corespringsecurity.repository.crawl;

import io.security.corespringsecurity.domain.entity.video.Video;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video,Long> {

    @Query("SELECT v FROM Video v WHERE v.playUrl = :videoUrl")
    Video findByUrl(@Param("videoUrl") String videoUrl);

    @Query("SELECT v FROM Video v ORDER BY v.id DESC")
    List<Video> findTop5ByOrderByIdDesc(Pageable pageable);
}
