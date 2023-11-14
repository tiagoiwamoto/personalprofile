package br.com.tiagoiwamoto.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_resumes")
@RequiredArgsConstructor
@Getter
@Setter
public class ResumeEntity implements Serializable {

    private static final long serialVersionUID = -3356991778804276804L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    private String title;
    private String description;
    private String language;
    private String type;
    private String url;
    @Column(length = 9999)
    private String embed;

}
