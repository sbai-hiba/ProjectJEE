package com.example.demo.service;

import com.example.demo.dao.entities.Task;
import com.example.demo.dao.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.task.TaskSchedulerBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements TaskManager {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskSchedulerBuilder taskSchedulerBuilder;

    @Override
    public Task addTask(Task task) {

        try{
            return  taskRepository.save(task);
        }catch (Exception exception){
            System.out.println(exception.getMessage()); //Logger
            return null;
        }

    }

    @Override
    public boolean deleteTask(Task task) {
        try {
            taskRepository.delete(task);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteTaskById(Integer id) {
        try {
            taskRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public Task updateTask(Task task) {
        try {
            return taskRepository.save(task);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public List<Task> getAllTask(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Task> taskPage = taskRepository.findAll(pageable);
        return taskPage.getContent();
    }

    @Override
    public Task getTaskById(Integer id) {
        return taskRepository.findById(id).get();
    }
}

