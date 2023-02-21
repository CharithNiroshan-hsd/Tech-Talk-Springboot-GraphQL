package com.example.graphQL.controller;

import com.example.graphQL.entity.Student;
import com.example.graphQL.input.StudentInput;
import com.example.graphQL.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @QueryMapping(name = "getStudents")
    public List<Student> getStudents() {
        return studentService.findAllStudents();
    }

    @MutationMapping(name = "addStudent")
    public Student saveStudent(@Argument StudentInput studentInput) {
        return studentService.createNewStudent(studentInput.getName(), studentInput.getAge(), studentInput.getGender());
    }

    @QueryMapping(name = "getStudentById")
    public Student getStudentById(@Argument String id) {
        Optional<Student> student = studentService.findStudentById(id);
        return student.orElse(null);
    }

    @MutationMapping(name = "updateStudent")
    public Student updateStudentById(@Argument String id, @Argument StudentInput studentUpdateInput) {
        Optional<Student> student = studentService.findStudentByIdAndUpdate(id, studentUpdateInput.getName(),
                studentUpdateInput.getAge(), studentUpdateInput.getGender());
        return student.orElse(null);
    }

    @MutationMapping(name = "deleteStudent")
    public String deleteStudentById(@Argument String id) {
        studentService.findStudentByIdAndDelete(id);
        return "Student for given id deleted successfully";
    }
}
