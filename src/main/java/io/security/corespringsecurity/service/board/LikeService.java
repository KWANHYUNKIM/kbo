package io.security.corespringsecurity.service.board;

import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Like;

import java.util.List;

public interface LikeService {

    void save(Like like);

    Like existsByBoard (Long board);

    List<Like> findByLikeAndUnlike (Long board);

}
