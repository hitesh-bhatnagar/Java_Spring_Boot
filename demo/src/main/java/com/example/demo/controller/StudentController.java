package com.example.demo.controller;
import java.util.*;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

@CrossOrigin(origins = "http://localhost:3060")

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
    public ResponseEntity<Student> getStudentByID(@PathVariable int id){    
        Student student = studentService.getStudentByID(id);
        return ResponseEntity.ok(student);
    }


    @PostMapping
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student student, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }

        Student message = studentService.addStudent(student);
        return ResponseEntity.ok(message);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id, @Valid @RequestBody Student student, BindingResult result){
        if(result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
            );
            
            return ResponseEntity.badRequest().body(errors);

        }

        String message = studentService.updateStudent(id, student);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        return studentService.deleteStudent(id);
    }

}
