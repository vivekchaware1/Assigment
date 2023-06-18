package com.example.demo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	 private final CategoryService categoryService;

	    public CategoryController(CategoryService categoryService) {
	        this.categoryService = categoryService;
	    }

	    @GetMapping
	    public List<Category> getAllCategories() {
	        return categoryService.getAllCategories();
	    }
	    
	    @PostMapping
	    public Category createCategory(@RequestBody Category category) {
	        return categoryService.createCategory(category);
	    }
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) {
	        java.util.Optional<Category> category = categoryService.getCategoryById(id);
	        return category.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category updatedCategory) {
	    	java.util.Optional<Category> category = categoryService.getCategoryById(id);
	        
	        if (category.isPresent()) {
	            Category existingCategory = category.get();
	            existingCategory.setName(updatedCategory.getName());
	            
	            Category savedCategory = categoryService.updateCategory(existingCategory);
	            return ResponseEntity.ok(savedCategory);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
	    	java.util.Optional<Category> category = categoryService.getCategoryById(id);
	        
	        if (category.isPresent()) {
	            categoryService.deleteCategory(id);
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

}
