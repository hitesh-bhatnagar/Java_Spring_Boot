package com.example.demo.service;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;  // an operation offered as an interface that stands alone in the model, with no encapsulated state.

import com.example.demo.exception.StudentNotFoundException;
import java.util.*;

@Service    // marks this as spring-managed bean (a singleton instance automatically created by spring)

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentByID(int id){
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with ID "+ id + "not found"));
    }

    public Student addStudent(Student student){
        return studentRepository.save(student);
    }

    public String updateStudent(int id, Student updatedStudent) {
        return studentRepository.findById(id)
            .map(student -> {
                student.setName(updatedStudent.getName());
                student.setCourse(updatedStudent.getCourse());
                studentRepository.save(student);
                return "Student updated successfully";
            })
            .orElse("Student not found");
    }

    public String deleteStudent(int id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return "Student deleted successfully";
        } else {
            return "Student not found";
        }
    }


}
