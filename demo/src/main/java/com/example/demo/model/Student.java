package com.example.demo.model;

// <!!! >        

import jakarta.persistence.Entity;     
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity     // marks this class as a database entity
public class Student {

    @Id     // Every entity must have a primary key and @Id designates the field hat serves this purpose
    @GeneratedValue(strategy = GenerationType.IDENTITY) // specifies the strategy for pimary key values generation in the database. 

    // Common stategies are : 
        // AUTO : selects the most appopriate stategy automatically
        // IDENTITY : The database's auto-incremented featue is used
        // SEQUENCE : Database sequences are utilized 
    private int id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 30, message = "Name must be between 2 to 30 characters ")
    private String name;
    
    @NotBlank(message = "Course cannot be blank")
    @Size(min = 3, max = 30, message = "Course name must have at least 3 characters")
    private String course;

    public Student(){}

    public Student(int id, String name, String course){
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCourse(){
        return course;

    }

    public void setCourse(String course){
        this.course = course;
    }
}
