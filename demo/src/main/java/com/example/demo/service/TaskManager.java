package com.example.demo.service;

import com.example.demo.dao.entities.Task;

import java.util.List;

public interface TaskManager {

    public Task addTask(Task task);
    public boolean deleteTask(Task task);
    public boolean deleteTaskById(Integer id);
    public Task updateTask(Task task);
    List<Task> getAllTask(int page, int size);

    public Task getTaskById(Integer id);
}
