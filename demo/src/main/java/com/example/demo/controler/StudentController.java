package com.example.demo.controler;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/lala")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/api/v1/students")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/api/v1/student/{studentId}")
    public Student getStudentById(@PathVariable("studentId") Long studentId) {
        return studentService.getStudent(studentId).get();
    }


    @PostMapping("/api/v1/student")
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @PostMapping("/api/v1/students")
    public void addStudents(@RequestBody List<Student> students) {
        studentService.addStudents(students);
    }

    @DeleteMapping(path = "/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudentId(studentId);
    }

    @PutMapping(path = "/api/v1/updateName/{studentId}")
    public Student updateStudentName(@PathVariable("studentId") Long studentId,
                                     @RequestParam(required = true) String name
    ) {
        return studentService.updateStudentName(studentId, name);
    }

    @PutMapping(path = "/api/v1/updateEmail/{studentId}")
    public Student updateStudentEmail(@PathVariable("studentId") Long studentId,
                                      @RequestParam(required = true) String email
    ) throws ConstraintViolationException {
        return studentService.updateStudentEmail(studentId, email);
    }


}
