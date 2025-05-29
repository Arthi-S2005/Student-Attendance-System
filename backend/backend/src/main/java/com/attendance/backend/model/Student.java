package com.attendance.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity                      // This tells Spring this class = table
@Data                        // Lombok: adds getters, setters, toString, etc.
@NoArgsConstructor           // Lombok: makes a no-arg constructor
@AllArgsConstructor          // Lombok: makes a full constructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;          // Primary key

    private String name;
    private String rollNumber;
    private String department;
}
