package com.example.demo.controler;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/Hogwarts")
//@RequiredArgsConstructor generates a constructor with 1 parameter
// for each field that requires special handling.
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    @GetMapping("/api/v1/students")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/api/v1/student/{studentId}")
    public Student getStudentById(@PathVariable("studentId") Long studentId) {
        return studentService.getStudent(studentId);
    }

    @PostMapping("/api/v1/addStudent")
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @PostMapping("/api/v1/addStudents")
    public void addStudents(@RequestBody List<Student> students) {
        studentService.addStudents(students);
    }

    @DeleteMapping(path = "/api/v1/deleteStudent/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudentId(studentId);
    }

    @PutMapping(path = "/api/v1/updateName/{studentId}")
    public Student updateStudentName(@PathVariable("studentId") Long studentId,
                                     @RequestParam String name
    ) {
        return studentService.updateStudentName(studentId, name);
    }

    @PutMapping(path = "/api/v1/updateEmail/{studentId}")
    public Student updateStudentEmail(@PathVariable("studentId") Long studentId,
                                      @RequestParam String email
    ) throws ConstraintViolationException {
        return studentService.updateStudentEmail(studentId, email);
    }


}
