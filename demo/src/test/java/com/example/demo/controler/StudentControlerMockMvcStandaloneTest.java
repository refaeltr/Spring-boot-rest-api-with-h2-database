package com.example.demo.controler;

import com.example.demo.exceptions.AppExceptionHandler;
import com.example.demo.exceptions.StudentIdMismatchException;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
public class StudentControlerMockMvcStandaloneTest {
    private MockMvc mvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<Student> jsonStudent;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper().findAndRegisterModules());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(studentController)
                .setControllerAdvice(new AppExceptionHandler())
                .build();
    }

    @Test
    public void canRetrieveByIdWhenExists() throws Exception {
        // given
        given(studentService.getStudent(1L))
                .willReturn(Optional.of(new Student("elizabeth", "elizabeth@gmail.com", LocalDate.of(1999, 07, 02), 23)));


        // when
        MockHttpServletResponse response = mvc.perform(
                        get("/lala/api/v1/student/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonStudent.write
                        (new Student("elizabeth", "elizabeth@gmail.com",
                                LocalDate.of(1999, 07, 02), 23)).getJson()
        );
    }

    @Test
    public void canRetrieveByIdWhenDoesNotExist() throws Exception {
        // given
        given(studentService.getStudent(2L))
                .willThrow(new StudentIdMismatchException());

        // when
        MockHttpServletResponse response = mvc.perform(
                        get("/lala/api/v1/student/2")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).isEqualTo("my error: id is not in the database");
    }


    @Test
    public void canCreateANewStudent() throws Exception {
        // when
        MockHttpServletResponse response = mvc.perform(
                post("/lala/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                jsonStudent.write(new Student("elizabeth", "elizabeth@gmail.com",
                                        LocalDate.of(1999, 07, 02), 23)).getJson()
                        )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void canChangeNameWhenExists() throws Exception {
        // given
        given(studentService.updateStudentName(1L,"elizabeth"))
                .willReturn(new Student("elizabeth",
                        "elizabeth@gmail.com",
                        LocalDate.of(1999, 07, 02),
                        23));

        // when
        MockHttpServletResponse response = mvc.perform(
                        put("/lala/api/v1/updateName/1?name=elizabeth")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonStudent.write
                        (new Student("elizabeth", "elizabeth@gmail.com",
                                LocalDate.of(1999, 07, 02), 23)).getJson()
        );
    }

    @Test
    public void canChangeEmailWhenExists() throws Exception {
        // given
        given(studentService.updateStudentEmail(1L,"elizabeth@gmail.com"))
                .willReturn(new Student("elizabeth",
                        "elizabeth@gmail.com",
                        LocalDate.of(1999, 07, 02),
                        23));

        // when
        MockHttpServletResponse response = mvc.perform(
                        put("/lala/api/v1/updateEmail/1?email=elizabeth@gmail.com")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonStudent.write
                        (new Student("elizabeth", "elizabeth@gmail.com",
                                LocalDate.of(1999, 07, 02), 23)).getJson()
        );
    }
}
