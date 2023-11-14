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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_scholarities")
@RequiredArgsConstructor
@Getter
@Setter
public class ScholarityEntity implements Serializable {

    private static final long serialVersionUID = -1142877138632898542L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "school_name")
    private String schoolName;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "title_received_course")
    private String titleReceivedCourse;
    private Integer duration;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "date_of_conclusion")
    private LocalDate dateOfConclusion;

}
