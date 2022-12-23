package com.example.demo.repository;

import com.example.demo.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//----------- not really needed, for learning purposes ------------------------


@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentEmailExist() {
        //given
        String name = "inigo";
        String email = "inigoMontoya1973@gmail.com";
        LocalDate dob = LocalDate.of(1973, 9, 9);
        Student student = new Student(
                name,
                email,
                dob,
                2022 - 1973
        );
        underTest.save(student);

        //when
        boolean expected = underTest.findByEmail(email).isPresent();

        //then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfStudentEmailDoesNotExist() {
        //given
        String email = "inigoMontoya1973@gmail.com";
        //when
        boolean expected = underTest.findByEmail(email).isPresent();

        //then
        assertThat(expected).isFalse();
    }
}