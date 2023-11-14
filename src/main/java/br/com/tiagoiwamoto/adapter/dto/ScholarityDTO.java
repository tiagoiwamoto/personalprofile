package br.com.tiagoiwamoto.adapter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ScholarityDTO implements Serializable {

    private static final long serialVersionUID = 7112740584947910252L;

    private Long id;
    private UUID uuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String schoolName;
    private String courseName;
    private String titleReceivedCourse;
    private Integer duration;
    private LocalDate startDate;
    private LocalDate dateOfConclusion;

}
