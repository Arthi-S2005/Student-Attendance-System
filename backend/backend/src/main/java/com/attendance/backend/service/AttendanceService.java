package com.attendance.backend.service;

import com.attendance.backend.model.Attendance;
import com.attendance.backend.model.Student;
import com.attendance.backend.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance markAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendanceByStudent(Student student) {
        return attendanceRepository.findByStudent(student);
    }
}
