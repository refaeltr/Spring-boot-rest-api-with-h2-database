package com.example.demo.service;

import com.example.demo.exceptions.StudentEmailMismatchException;
import com.example.demo.exceptions.StudentIdMismatchException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudent(Long id) {
        return studentRepository.findById(id);
    }

    public void addStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new StudentEmailMismatchException();
        }
        studentRepository.save(student);

    }

    public void addStudents(List<Student> students) {
        for (Student st : students) {
            addStudent(st);
        }
    }

    public void deleteStudentId(Long studentId) {
        try {
            studentRepository.deleteById(studentId);
        } catch (EmptyResultDataAccessException e) {
            throw new StudentIdMismatchException();
        }
    }


    public Student updateStudentEmail(Long studentId, String email) throws StudentIdMismatchException, ConstraintViolationException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(StudentIdMismatchException::new);

        if (email != null && email.length() > 0
                && !Objects.equals(student.getEmail(), email)) {
            student.setEmail(email);
            studentRepository.save(student);
        }
        return student;
    }

    public Student updateStudentName(Long studentId, String name) throws StudentIdMismatchException, RuntimeException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(StudentIdMismatchException::new);

        if (name != null && name.length() > 0
                && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        try {
            studentRepository.save(student);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return student;
    }
}
