package com.example.demo.service;

import com.example.demo.dao.entities.Category;

import java.util.List;

public interface CategoryManager {

    public Category addCategory(Category product);
    public boolean deleteCategory(Category product);
    public boolean deleteCategoryById(Integer id);
    public Category updateCategory(Category product);
    List<Category> getAllCategory(int page, int size);
    public List<Category> getAllCategory();
    public Category getCategoryById(Integer id);
}
