package io.security.corespringsecurity.service.board;

import io.security.corespringsecurity.domain.entity.board.Category;

import java.util.List;

public interface CategoryService {

    Category findByCategoryName(String categoryName);

    List<Category> findByCategoryNames(List<String> categoryNames);

}
