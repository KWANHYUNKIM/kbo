package io.security.corespringsecurity.service.impl.board;


import com.google.common.base.Optional;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Like;
import io.security.corespringsecurity.repository.board.LikeRepository;
import io.security.corespringsecurity.service.board.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public void save(Like like) {
        // 좋아요를 누른 기록이 없을 때만 저장
            likeRepository.save(like);

    }

    @Override
    public Like existsByBoard(Long boardId) {
        return likeRepository.existsByBoard(boardId);
    }

    @Override
    public List<Like> findByLikeAndUnlike(Long board) {
        return likeRepository.findByLikeAndUnlike(board);
    }
}
