package br.com.tiagoiwamoto.core.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_courses")
@RequiredArgsConstructor
@Getter
@Setter
public class CourseEntity implements Serializable {

    private static final long serialVersionUID = -8305116206269371239L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    private String name;
    private String title;
    private String school;
    private Double duration;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "path_of_image")
    private String pathOfImage;
    @Column(name = "path_of_image_thumb")
    private String pathOfImageThumb;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_category_id")
    @JsonBackReference
    private CourseCategoryEntity courseCategory;
}
