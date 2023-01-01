package com.example.demo.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Student {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    private LocalDate dob;
    @Transient //The transient keyword is primarily meant for ignoring fields during Java object serialization
    private Integer age;

    public Integer getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

}
