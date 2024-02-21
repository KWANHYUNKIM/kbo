package io.security.corespringsecurity.service.board;

import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    /** 저장 **/
    void createBoard(Board board);
    /** 전체 **/
    List<Board> findAll();
    /** Query 검색 **/

    /** 디테일 **/
    List<Board> findByboardId(Long boardId);

    Board findByComment(Long boardId);

    List<Board> findByQuery(String query);
    /** 삭제 **/
    void deleteBoard(Long boardId);
//    /** 분류 **/
//    List<Board> getAllSortedByBoard(String sort);

    /**
     * 편집
     **/
    Optional<Board> findById (Long boardId);

    Board editByBoard (Long boardId, Board updatedBoard);

    Page<Board> getList (int page, Category categoryId);

     void incrementViewCount(Long boardId);

    Page<Board> getListTop5(Pageable pageable, List<Category> category);

    Page<Board> getListAll(int page, List<Category> categories);

    Page<Board> getListArticleTop5(Pageable pageable, Category categoryName);
}
