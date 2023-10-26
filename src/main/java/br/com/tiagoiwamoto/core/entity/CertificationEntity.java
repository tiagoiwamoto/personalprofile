package br.com.tiagoiwamoto.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_certifications")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class CertificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String name;
    @Column(name = "earn_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate earnDate;
    @Column(name = "validate_url")
    private String validateUrl;
    @Column(name = "path_of_image")
    private String pathOfImage;
    @Column(name = "path_of_image_thumb")
    private String pathOfImageThumb;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
