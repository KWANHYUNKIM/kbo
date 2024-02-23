package io.security.corespringsecurity.service.impl.board;


import com.google.common.base.Optional;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Like;
import io.security.corespringsecurity.domain.entity.board.ReactionType;
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
        Like existingLike = likeRepository.findByAccountAndBoard(like.getAccount(), like.getBoard());
        if (existingLike != null) {
            // 기존 투표가 있으면 업데이트
            likeRepository.delete(existingLike);
        } else {
            // 새로운 투표 추가
            likeRepository.save(like);
        }
    }

    @Override
    public Like findByBoardAndType(Long boardId) {
        return likeRepository.existsByBoard(boardId);
    }

    @Override
    public List<Like> findByBoard(Long board) {
        return likeRepository.findByBoard(board);
    }

    @Override
    public int getLikeCount(Long boardId) {
        return likeRepository.getLikeCount(boardId);
    }
}
