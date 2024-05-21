package com.example.demo.web;

import com.example.demo.dao.entities.Category;
import com.example.demo.dao.entities.Task;
import com.example.demo.dao.repositories.CategoryRepository;
import com.example.demo.service.CategoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

@Controller
public class CategoryController {

    @Autowired
    private CategoryManager categoryManager;
    @Autowired
    private CategoryRepository categoryRepository;

    // Get all categories
    @GetMapping("/categories")
    public String getAllCategories(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "3") int size,
                                   Model model) {
        List<Category> categories = categoryManager.getAllCategory(page, size);
        model.addAttribute("categories", categories);

        Page<Category> categoryPage = categoryRepository.findAll(PageRequest.of(page, size));
        model.addAttribute("pageNumbers", IntStream.range(0, categoryPage.getTotalPages()).toArray());

        return "categories";
    }

    // Get add category form
    @GetMapping("/categories/add")
    public String getAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "add_category";
    }

    @PostMapping("/category/add")
    public String addCategory(@ModelAttribute Category category) {
        categoryManager.addCategory(category);
        // Redirect to the list of categories after adding the new category
        return "redirect:/categories";
    }

    // Get edit category form
    @GetMapping("/categories/edit/{id}")
    public String getEditCategoryForm(@PathVariable Integer id, Model model) {
        Category category = categoryManager.getCategoryById(id);
        model.addAttribute("category", category);
        return "edit_category";
    }

    // Post edit category form
    @PostMapping("/categories/edit/{id}")
    public String editCategory(@PathVariable Integer id, @ModelAttribute Category category) {
        category.setId(id); // Ensure correct ID is set
        categoryManager.updateCategory(category);
        return "redirect:/categories";
    }

    // Delete category
    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        categoryManager.deleteCategoryById(id);
        return "redirect:/categories";
    }

    // Get tasks by category
    @GetMapping("/categories/tasks/{id}")
    public String getTasksByCategory(@PathVariable Integer id, Model model) {
        Category category = categoryManager.getCategoryById(id);
        if (category != null) {
            List<Task> tasks = category.getTasks();
            model.addAttribute("tasks", tasks);
            model.addAttribute("categoryName", category.getName());
        }
        return "category_tasks"; // Assurez-vous que le nom de la vue est correct
    }




}
