package io.security.corespringsecurity.service.board;

import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Like;
import io.security.corespringsecurity.domain.entity.board.ReactionType;

import java.util.List;

public interface LikeService {

    void save(Like like);

    Like findByBoardAndType (Long board);

    List<Like> findByBoard (Long board);

    int getLikeCount(Long boardId);

}
