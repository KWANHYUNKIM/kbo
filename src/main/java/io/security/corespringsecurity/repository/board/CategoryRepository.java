package io.security.corespringsecurity.repository.board;

import io.security.corespringsecurity.domain.entity.board.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.categoryName = :categoryName")
    Category findByCategoryName(@Param("categoryName") String categoryName);


    @Query("SELECT c FROM Category c WHERE c.categoryName IN :categoryNames")
    List<Category> findByCategoryNames(@Param("categoryNames") List<String> categoryNames);
}
