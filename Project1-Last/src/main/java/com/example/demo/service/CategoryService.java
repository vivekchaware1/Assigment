package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dao.CategoryDao;
import com.example.demo.entity.Category;

@Service
public class CategoryService {

	private final CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }
    
    public Category createCategory(Category category) {
        return categoryDao.save(category);
    }
    
    public Optional<Category> getCategoryById(Long id) {
        return categoryDao.findById(id);
    }
    
    public Category updateCategory(Category category) {
        return categoryDao.save(category);
    }
    
    public void deleteCategory(Long id) {
        categoryDao.deleteById(id);
    }
}
