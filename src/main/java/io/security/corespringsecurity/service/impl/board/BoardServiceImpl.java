package io.security.corespringsecurity.service.impl.board;

import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Category;
import io.security.corespringsecurity.repository.board.BoardRepository;
import io.security.corespringsecurity.service.board.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Board> findByboardId(Long boardId) {
        try {
            return boardRepository.findByBoardId(boardId);

        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
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

    public Page<Board> getList (int page, Category categoryId){
        Pageable pageable = PageRequest.of(page, 30);

        if (categoryId != null) {
            return this.boardRepository.getList(categoryId, pageable);
        } else {
            return this.boardRepository.findAll(pageable);
        }
    }

    @Transactional
    public void incrementViewCount(Long boardId) {
        boardRepository.incrementViewCount(boardId);
    }

    @Override
    public Page<Board> getListTop5(Pageable pageable, List<Category> categories) {
        List<String> categoryNames = categories.stream()
                .map(Category::getCategoryName)
                .collect(Collectors.toList());

        return boardRepository.findByCategoryNamesOrderByCreatedDateDesc(pageable, categoryNames);
    }

    @Override
    public Page<Board> getListAll(int page, List<Category> categories) {
        Pageable pageable = PageRequest.of(page, 30);

        if (categories != null && !categories.isEmpty()) {
            return boardRepository.getListAll(categories, pageable);
        } else {
            return boardRepository.findAll(pageable);
        }
    }

    @Override
    public Page<Board> getListArticleTop5(Pageable pageable, Category categoryName) {
        return boardRepository.getList(categoryName,pageable);
    }

//    @Override
//
//    public List<Board> getAllSortedByBoard(String sort) {
//        return boardRepository.getAllSortedByBoard(sort);
//    }


}
