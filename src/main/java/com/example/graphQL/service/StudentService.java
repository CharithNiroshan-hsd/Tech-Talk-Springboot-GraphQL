package com.example.graphQL.service;

import com.example.graphQL.constant.Gender;
import com.example.graphQL.entity.Student;
import com.example.graphQL.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student createNewStudent(String name, String age, Gender gender) {
        Student student = new Student(name, Integer.parseInt(age), gender);
        return studentRepository.save(student);
    }

    public Optional<Student> findStudentById(String id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> findStudentByIdAndUpdate(String id, String name, String age, Gender gender) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            if (name != null) {
                student.get().setName(name);
            }
            if (age != null) {
                student.get().setAge(Integer.parseInt(age));
            }
            if (gender != null) {
                student.get().setGender(gender);
            }
            studentRepository.save(student.get());
        }
        return student;
    }

    public void findStudentByIdAndDelete(String id) {
        studentRepository.deleteById(id);
    }
}
