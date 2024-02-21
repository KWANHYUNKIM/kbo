package io.security.corespringsecurity.service.impl.board;

import io.security.corespringsecurity.domain.entity.board.Category;
import io.security.corespringsecurity.repository.board.CategoryRepository;
import io.security.corespringsecurity.service.board.BookmarkService;
import io.security.corespringsecurity.service.board.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Category findByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    @Override
    public List<Category> findByCategoryNames(List<String> categoryNames) {
        return categoryRepository.findByCategoryNames(categoryNames);
    }
}
