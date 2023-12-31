package io.security.corespringsecurity.service.impl.board;

import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.kbo.Hitter;
import io.security.corespringsecurity.repository.board.BoardRepository;
import io.security.corespringsecurity.service.board.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service("boardService")
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Override
    public List<Board> findByboardId(Long boardId){
        return boardRepository.findByBoardId(boardId);
    }
    @Transactional
    @Override
    public void createBoard (Board board){
        boardRepository.save(board);
    }

    @Override
    public List<Board> findByQuery(String query) {
        return boardRepository.findByQuery(query);
    }
    @Transactional
    @Override
    public void deleteBoard(Long boardId){ boardRepository.deleteBoard(boardId);}

    @Override
    public Optional<Board> findById(Long boardId) {
        return boardRepository.findById(boardId);
    }

    @Transactional
    @Override
    public Board editByBoard(Long boardId, Board updatedBoard) {
        return boardRepository.editByBoard(boardId,updatedBoard);
    }

    @Override
    public Board findByComment(Long boardId){
        return boardRepository.findByComment(boardId);
    }


    public List<Board> findTop5ByOrderByTagCountDesc(Pageable pageable) {
       return boardRepository.findTop5ByOrderByTagCountDesc(pageable);
    }
    public Page<Board> getList (int page){
        Pageable pageable = PageRequest.of(page,30);
        return this.boardRepository.findAll(pageable);
    }

    @Transactional
    public void incrementViewCount(Long boardId) {
        boardRepository.incrementViewCount(boardId);
    }

//    @Override
//
//    public List<Board> getAllSortedByBoard(String sort) {
//        return boardRepository.getAllSortedByBoard(sort);
//    }


}
