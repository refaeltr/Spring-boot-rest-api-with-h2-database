package com.example.demo.service;

import com.example.demo.exceptions.StudentEmailMismatchException;
import com.example.demo.exceptions.StudentIdMismatchException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();

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
        Student student = studentRepository.findById(studentId).orElseThrow(StudentIdMismatchException::new);
        /*
        boolean exist = studentRepository.existsById(studentId);
        if (!exist) {
            throw new IllegalStateException("student with id" + studentId + "does not exist");
        }

         */
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(StudentIdMismatchException::new);
        /*
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalStateException("student with id" + studentId + "does not exist"));

         */
        if (name != null && name.length() > 0
                && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && email.length() > 0
                && !Objects.equals(student.getEmail(), email)) {
            Student student1 = studentRepository.findByEmail(email).orElseThrow(StudentEmailMismatchException::new);
            /*
            Optional<Student> studentOptional = studentRepository.findByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }

             */
            student.setEmail(email);
        }

    }


}
