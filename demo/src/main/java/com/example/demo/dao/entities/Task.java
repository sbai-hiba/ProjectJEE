package com.example.demo.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private LocalDate dueDate;
    private boolean completed;

    @ManyToOne
    private Category category;
    @Override
    public  String toString(){
        return "Task [id=" + id + ", name=" + name + ", description=" + description + ", dueDate="+dueDate+", completed=" + completed ;
    }

}