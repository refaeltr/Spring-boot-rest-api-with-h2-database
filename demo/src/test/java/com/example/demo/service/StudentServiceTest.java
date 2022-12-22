package com.example.demo.service;

import com.example.demo.exceptions.StudentEmailMismatchException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class StudentServiceTest {


    @Mock
    private StudentRepository studentRepository;
    private AutoCloseable autoCloseable;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new StudentService(studentRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getStudents() {
        //when
        underTest.getStudents();

        //then
        verify(studentRepository).findAll();


    }

    @Test
    void canAddStudent() {
        //given
        Student student = new Student(
                "inigo",
                "inigoMontoya1973@gmail.com",
                LocalDate.of(1973, 9, 9),
                2022 - 1973
        );

        //when
        underTest.addStudent(student);

        //then
        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        //given
        Student student = new Student(
                "inigo",
                "inigoMontoya1973@gmail.com",
                LocalDate.of(1973, 9, 9),
                2022 - 1973
        );

        given(studentRepository.findByEmail(anyString()))
                .willReturn(Optional.of(student));
        //when
        //then
        assertThatThrownBy(() -> underTest.addStudent(student))
                .isInstanceOf(StudentEmailMismatchException.class);
        //.hasMessageContaining("my error: this email is already taken");

        verify(studentRepository, never()).save(any());


    }

    @Disabled
    @Test
    void addStudents() {
    }


    @Test
    void canDeleteStudentId() {
        //given
        Student student1 = new Student(
                "inigo",
                "inigoMontoya1973@gmail.com",
                LocalDate.of(1973, 9, 9),
                2022 - 1973
        );
        given(studentRepository.existsById(student1.getId()))
                .willReturn(true);

        underTest.deleteStudentId(student1.getId());
         //when
        //then
        ArgumentCaptor<Long> idArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);

        verify(studentRepository).deleteById(idArgumentCaptor.capture());
        Long capturedStudentId = idArgumentCaptor.getValue();
        assertThat(capturedStudentId).isEqualTo(student1.getId());

    }

    @Disabled
    @Test
    void updateStudent() {
    }
}