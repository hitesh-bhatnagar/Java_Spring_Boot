package com.example.demo.controller;
import java.util.*;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import com.example.demo.dto.StudentDTO;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "http://localhost:3000")

@RestController  // tells spring this class handles HTTP requests
@RequestMapping("/students") // used to map web requests to specific handler classes or methods within those classes. 

public class StudentController {

    // private final StudentRepository studentRepository;
    private final StudentService studentService;

    // constructor based dependency injection
    @Autowired  // marks a constructor, setter method property or configuration method to be autowired. meaning spring will automatically inject the required beans(dependencies) at runtime using its dependecy injection mechanism. 
    public StudentController(StudentService studentService, StudentRepository studentRepository){
        this.studentService = studentService;
    }

    @PostMapping
    public  ResponseEntity<StudentDTO> addStudent(@Valid @RequestBody StudentDTO studentDTO ){
        return ResponseEntity.ok(studentService.addStudent(studentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable int id, @Valid @RequestBody StudentDTO studentDTO){
        return ResponseEntity.ok(studentService.updateStudent(id, studentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable int id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/course/{course}")
    public ReponseEntity<List<StudentDTO>> getStudentByCourse(@ParthVariable String course){
        List<StudentDTO> students = studentService.getStudentByCourse(course);

        return ResponseEntity.ok(students);
    }

    GetMapping("/search")
    public ResponseEntity<List<StudentDTO>> searchStudentByName(@RequestParam String prefix){
        // @RequestParam is used to extract query parameters from the URL in a GET request
        List<StudentDTO> students = studentService.searchByNamePrefix(prefix);
        
        return ResponseEntity.ok(students);
    }

    @GetMapping("/count/{course}")
    public ResponseEntity<Long> countStudentByCourse(@PathVariable String course){
        long count = studentService.countByCourse(course);
        return ResponseEntity.ok(count);
    }

}
/*
            <!!!    Below code handles CRUD, validation and exception handling but not profesional and for large scale backend projects>
            
            Prob_1->  in addStudent we are directly using Student entity which is also your database table. (means db struct = API struct)
            
            Prob_2 -> The controller is returning Entities directly better way is to use StudentDTO (Data Transfer Obj)
            

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
*/


