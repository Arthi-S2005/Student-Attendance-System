package com.attendance.backend.repository;

import com.attendance.backend.model.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.Optional;

public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
    Optional<QRCode> findByCodeAndValidFromBeforeAndValidUntilAfter(String code, LocalDateTime now1, LocalDateTime now2);
}
