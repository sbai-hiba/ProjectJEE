package com.example.demo.service;

import com.example.demo.dao.entities.Category;
import com.example.demo.dao.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CategoryManager {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {

        try{
            return  categoryRepository.save(category);
        }catch (Exception exception){
            System.out.println(exception.getMessage()); //Logger
            return null;
        }

    }

    @Override
    public boolean deleteCategory(Category category) {
        try {
            categoryRepository.delete(category);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteCategoryById(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public Category updateCategory(Category category) {
        try {
            return categoryRepository.save(category);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public List<Category> getAllCategory(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage.getContent();
    }
    @Override
    public List<Category> getAllCategory() {
        return  categoryRepository.findAll();

    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
