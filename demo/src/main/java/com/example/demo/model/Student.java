package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity     // marks this class as a database entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name, course;

    public Student(){}

    public Student(int id, String name, String course){
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public int getID(){
        return id;
    }
    public void setID(int id){
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
