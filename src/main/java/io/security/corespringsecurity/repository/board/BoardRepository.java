package io.security.corespringsecurity.repository.board;

import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board,Long>  {
    List<Board> findAll();

    @Query("SELECT b FROM Board b WHERE b.title LIKE %:query%")
    List<Board> findByQuery(@Param("query") String query);
    @Modifying
    @Query("DELETE FROM Board b WHERE b.id = :boardId")
    void deleteBoard(@Param("boardId") Long boardId);

    Optional<Board> findById(Long boardId);

    @Query("SELECT b FROM Board b WHERE b.id = :boardId")
    List<Board> findByBoardId(Long boardId);
    @Query("SELECT b FROM Board b ORDER BY b.createdDate DESC")
    Page<Board> findAll(Pageable pageable);

    @Query("SELECT b FROM Board b WHERE b.id = :boardId")
    Board findByComment(Long boardId);
    @Query("SELECT b FROM Board b ORDER BY b.createdDate DESC")
    List<Board> findTop5ByOrderByTagCountDesc(Pageable pageable);
    // 수정 기능 추가
    default Board editByBoard(Long boardId, Board updatedBoard) {
        Optional<Board> existingBoardOptional = findById(boardId);
        if (existingBoardOptional.isPresent()) {
            Board existingBoard = existingBoardOptional.get();

            // 수정할 내용 업데이트
            existingBoard.setTitle(updatedBoard.getTitle());
            existingBoard.setContent(updatedBoard.getContent());
            // ... 다른 필드들도 업데이트

            // 수정된 엔터티 저장
            return save(existingBoard);
        }
        return null; // 해당 ID에 해당하는 게시물이 없을 경우
    }
    @Query("SELECT b FROM Board b WHERE b.id = :id")
    Board findByBoard(@Param("id") Long id);
    default void incrementViewCount(Long boardId) {
        Board board = findByBoard(boardId);
        if (board != null) {
            board.setViewCount(board.getViewCount() + 1);
            save(board);
        }
    }
    @Query("SELECT b FROM Board b WHERE b.category.categoryName IN :categoryNames ORDER BY b.createdDate DESC")
    Page<Board> findByCategoryNamesOrderByCreatedDateDesc(Pageable pageable, @Param("categoryNames") List<String> categoryNames);

    @Query("SELECT b FROM Board b WHERE b.category IN :categories ORDER BY b.createdDate DESC")
    Page<Board> getListAll(@Param("categories") List<Category> categories, Pageable pageable);

    @Query("SELECT b FROM Board b WHERE b.category = :category ORDER BY b.createdDate DESC")
    Page<Board> getList(@Param("category") Category category, Pageable pageable);}
