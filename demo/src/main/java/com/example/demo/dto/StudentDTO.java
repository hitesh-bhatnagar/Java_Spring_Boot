package com.example.demo.dto;

/*                <!!  Why use DTOs  !!>>
 * We don't expose database entities directly 
 * We can filter or rename fields for API responses
 * We can add computed values like fullName or grade
 */

public class StudentDTO {
    private int id;
    private String name;
    private String course;

    public StudentDTO(){}

    public StudentDTO(int id, String name, String course){
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public int getId(){
        return id;
    }
    public void setId(int id) {
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


