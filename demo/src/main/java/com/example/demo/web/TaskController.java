package com.example.demo.web;

import com.example.demo.dao.entities.Category;
import com.example.demo.dao.entities.Task;
import com.example.demo.dao.repositories.TaskRepository;
import com.example.demo.service.CategoryManager;
import com.example.demo.service.TaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

@Controller
public class TaskController {

    @Autowired
    private TaskManager taskManager;
    @Autowired
    private TaskRepository taskRepository;
@Autowired
private CategoryManager categoryManager;
    // Get all tasks
    @GetMapping("/tasks")
    public String getAllTasks(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "3") int size,
                              Model model) {
        List<Task> tasks = taskManager.getAllTask(page, size);
        model.addAttribute("tasks", tasks);

        Page<Task> taskPage = taskRepository.findAll(PageRequest.of(page, size));
        model.addAttribute("pageNumbers", IntStream.range(0, taskPage.getTotalPages()).toArray());

        return "tasks";
    }

    // Get task details
    @GetMapping("/task/{id}")
    public String getTaskDetails(@PathVariable Integer id, Model model) {
        Task task = taskManager.getTaskById(id);
        model.addAttribute("task", task);
        return "task_details";
    }

    // Get add task form
    @GetMapping("/tasks/add")
    public String getAddTaskForm(Model model,@RequestParam(name = "id", required = false) Integer id) {
        List<Category> categories = categoryManager.getAllCategory();
        model.addAttribute("task", new Task());
        model.addAttribute("categories", categories);
        return "add_task";
    }

    // Post add task form
    @PostMapping("/tasks/add")
    public String addTask(@ModelAttribute Task task) {
        taskManager.addTask(task);
        return "redirect:/tasks";
    }

    // Delete task by ID
    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Integer id) {
        taskManager.deleteTaskById(id);
        return "redirect:/tasks";
    }

    // Get update task form
    @GetMapping("/tasks/update/{id}")
    public String getUpdateTaskForm(@PathVariable Integer id, Model model) {
        Task task = taskManager.getTaskById(id);
        List<Category> categories=categoryManager.getAllCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("task", task);
        return "update_task";
    }

    // Post update task form
    @PostMapping("/tasks/update/{id}")
    public String updateTask(@PathVariable Integer id, @ModelAttribute Task task,Model model) {
        task.setId(id); // Ensure correct ID is set
        taskManager.updateTask(task);
        return "redirect:/tasks";
    }



}
