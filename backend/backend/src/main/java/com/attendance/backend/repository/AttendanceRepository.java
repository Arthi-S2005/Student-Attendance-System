package com.attendance.backend.repository;

import com.attendance.backend.model.Attendance;
import com.attendance.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudent(Student student);
}
