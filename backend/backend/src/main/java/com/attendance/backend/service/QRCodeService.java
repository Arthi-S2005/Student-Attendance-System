package com.attendance.backend.service;

import com.attendance.backend.model.QRCode;
import com.attendance.backend.repository.QRCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class QRCodeService {

    @Autowired
    private QRCodeRepository qrCodeRepository;

    public Optional<QRCode> validateQRCode(String code) {
        LocalDateTime now = LocalDateTime.now();
        return qrCodeRepository.findByCodeAndValidFromBeforeAndValidUntilAfter(code, now, now);
    }

    public QRCode saveQRCode(QRCode qrCode) {
        return qrCodeRepository.save(qrCode);
    }
}
