package com.attendance.backend.controller;

import com.attendance.backend.model.Student;
import com.attendance.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Get student by ID
    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // Create new student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    // Update existing student
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Optional<Student> existing = studentService.getStudentById(id);
        if (existing.isPresent()) {
            Student student = existing.get();
            student.setName(updatedStudent.getName());
            student.setEmail(updatedStudent.getEmail());
            student.setRegisterNumber(updatedStudent.getRegisterNumber());
            student.setDepartment(updatedStudent.getDepartment());
            student.setSection(updatedStudent.getSection());
            student.setYear(updatedStudent.getYear());
            return studentService.saveStudent(student);
        } else {
            return null;
        }
    }

    // Delete student
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }
}
