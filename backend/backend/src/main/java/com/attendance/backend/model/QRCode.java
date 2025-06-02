package com.attendance.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "qrcodes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QRCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private LocalDateTime validFrom;

    private LocalDateTime validUntil;

    private String className;
}
