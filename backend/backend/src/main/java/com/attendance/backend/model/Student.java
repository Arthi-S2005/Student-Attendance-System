package com.attendance.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    private String password;

    private String role = "STUDENT";
}


