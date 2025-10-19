package com.example.demo.controller;
import java.util.*;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

import org.springframework.web.bind.annotation.*;


@RestController  // tells spring this class handles HTTP requests
@RequestMapping("/students") // used to map web requests to specific handler classes or methods within those classes. 

public class StudentController {

    // private final StudentRepository studentRepository;
    private final StudentService studentService;

    // constructor based dependency injection
    public StudentController(StudentService studentService, StudentRepository studentRepository){
        this.studentService = studentService;
        // this.studentRepository = studentRepository;
    }

    @GetMapping 
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")

    // @PathVariable annotation would be an endpoint that identifies an entity with a primary key
    public Student getStudentByID(@PathVariable int id){    
        return studentService.getStudentByID(id);
    }

    @PostMapping
    public String addStudent(@RequestBody Student student){
        studentService.addStudent(student);
        return "Student added successfully";
    }

    @PutMapping("/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody Student student){
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        return studentService.deleteStudent(id);
    }

}
