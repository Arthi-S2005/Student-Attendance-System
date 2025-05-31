package com.attendance.backend.service;

import com.attendance.backend.model.Student;
import com.attendance.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Save or update a student
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get a student by ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Get student by register number
    public Optional<Student> getStudentByRegisterNumber(String registerNumber) {
        return studentRepository.findByRegisterNumber(registerNumber);
    }

    // Delete student by ID
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
}

