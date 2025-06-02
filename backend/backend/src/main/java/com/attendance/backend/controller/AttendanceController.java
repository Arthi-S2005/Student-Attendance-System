package com.attendance.backend.controller;

import com.attendance.backend.model.*;
import com.attendance.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private AttendanceService attendanceService;

    // Mark attendance by scanning QR code + fingerprint + geofence data
    @PostMapping("/mark")
    public ResponseEntity<?> markAttendance(@RequestBody AttendanceRequest request, Authentication authentication) {
        String email = authentication.getName();
        Optional<Student> studentOpt = studentService.findByEmail(email);
        if (studentOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Student not found");
        }
        Student student = studentOpt.get();

        Optional<QRCode> qrCodeOpt = qrCodeService.validateQRCode(request.getQrCode());
        if (qrCodeOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid or expired QR code");
        }
        QRCode qrCode = qrCodeOpt.get();

        // Here you can add fingerprint verification and geofence check logic
        // For now, just accept verifiedFingerprint flag and latitude/longitude

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setQrCode(qrCode);
        attendance.setTimestamp(LocalDateTime.now());
        attendance.setVerifiedFingerprint(request.isVerifiedFingerprint());
        attendance.setLatitude(request.getLatitude());
        attendance.setLongitude(request.getLongitude());

        attendanceService.markAttendance(attendance);

        return ResponseEntity.ok("Attendance marked successfully");
    }

    // Get attendance history for logged-in student
    @GetMapping("/history")
    public ResponseEntity<?> getAttendanceHistory(Authentication authentication) {
        String email = authentication.getName();
        Optional<Student> studentOpt = studentService.findByEmail(email);
        if (studentOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Student not found");
        }
        Student student = studentOpt.get();

        List<Attendance> attendanceList = attendanceService.getAttendanceByStudent(student);
        return ResponseEntity.ok(attendanceList);
    }

    // DTO for attendance marking
    public static class AttendanceRequest {
        private String qrCode;
        private boolean verifiedFingerprint;
        private double latitude;
        private double longitude;

        public String getQrCode() {
            return qrCode;
        }
        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }
        public boolean isVerifiedFingerprint() {
            return verifiedFingerprint;
        }
        public void setVerifiedFingerprint(boolean verifiedFingerprint) {
            this.verifiedFingerprint = verifiedFingerprint;
        }
        public double getLatitude() {
            return latitude;
        }
        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }
        public double getLongitude() {
            return longitude;
        }
        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }
}
