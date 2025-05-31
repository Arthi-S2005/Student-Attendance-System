package com.attendance.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "students")
@Data  // Lombok annotation generates all getters, setters, toString, equals, hashCode
public class Student {

    @Id
    private String registerNumber;  // primary key

    private String name;
    private String email;
    private String department;
    private String section;
    private int year;
}
