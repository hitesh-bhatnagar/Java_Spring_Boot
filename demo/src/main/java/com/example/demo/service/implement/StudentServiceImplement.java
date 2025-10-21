package com.example.demo.service.implement;

import com.example.demo.dto.StudentDTO;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.*;

@Service    // marks this as spring-managed bean (a singleton instance automatically created by spring)
public class StudentServiceImplement implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    
    @Autowired
    public StudentServiceImplement(StudentRepository studentRepository, ModelMapper modelMapper){
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

/*
<!!    converToDTO() and ConvertToEntity() methodshandle data transformation this keeps controllers and entities clean.  !!>
< Later can be replaced by ModelMapper ( a mapping lib) >

            This method is bollerplate and repetitive and not industry standards 

        private StudentDTO convertToDTO(Student student){       
        return new StudentDTO(student.getId(), student.getName(), student.getCourse());
        }

        private Student convertToEntity(StudentDTO dto){
        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setCourse(dto.getCourse());

        return student;
        }

*/

    @Override
    public StudentDTO addStudent(StudentDTO studentDTO){
        Student student = modelMapper.map(studentDTO, Student.class);
        Student saved = studentRepository.save(student);
        return modelMapper.map(saved, StudentDTO.class);
    }

    @Override
    public StudentDTO updateStudent(int id, StudentDTO studentDTO){
        Student existing = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found with id: "+ id));

        existing.setName(studentDTO.getName());
        existing.setCourse(studentDTO.getCourse());
        Student updated = studentRepository.save(existing);

        return modelMapper.map(updated, StudentDTO.class);
    }

    @Override
    public void deleteStudent(int id){

        if(!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student not found with id: "+ id);
        }

        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO getStudentById(int id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found with id: "+ id));

        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public List<StudentDTO> getAllStudents(){
        return studentRepository.findAll()
            .stream()
            .map(student -> modelMapper.map(student, StudentDTO.class))
            .collect(Collectors.toList());
    }

    @Override public List<StudentDTO> getStudentByCourse(String course){
        return studentRepository.findByCourse(course)
            .stream()
            .map(student -> modelMapper.map(student, StudentDTO.class))
            .collect(Collectors.toList());
    }

    
}

